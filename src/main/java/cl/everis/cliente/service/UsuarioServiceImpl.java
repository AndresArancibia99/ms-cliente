package cl.everis.cliente.service;

import cl.everis.cliente.DTO.ContactoResponseDTO;
import cl.everis.cliente.DTO.UsuarioRequestDTO;
import cl.everis.cliente.DTO.UsuarioResponseDTO;
import cl.everis.cliente.exception.ErrorException;
import cl.everis.cliente.model.Contacto;
import cl.everis.cliente.model.Usuario;
import cl.everis.cliente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioResponseDTO> findAllUser() {
        List<Usuario> listUser = usuarioRepository.findAll();

        // Optional, user = 0, no content

        List<UsuarioResponseDTO> listUserResponseDTO = new ArrayList<>();

        listUser.forEach(usuario -> {
                    List<ContactoResponseDTO> ListaContactoResponseDTOS = usuario.getContactos()
                            .stream()
                            .map(contacto -> ContactoResponseDTO.builder()
                                    .codigoPais(contacto.getCodigoPais())
                                    .codigoCiudad(contacto.getCodigoPais())
                                    .numero(contacto.getNumero())
                                    .build())
                            .collect(Collectors.toList());

                    listUserResponseDTO.add(UsuarioResponseDTO.builder()
                            .uuid(usuario.getUuid())
                            .rut(usuario.getRut())
                            .nombre(usuario.getNombre())
                            .email(usuario.getEmail())
                            .estado(usuario.isEstado())
                            .fechaCreacion(usuario.getFechaCreacion())
                            .fechaActualizacion(usuario.getFechaActualizacion())
                            .contactos(ListaContactoResponseDTOS)
                            .build());
                }
        );
        return listUserResponseDTO;
    }


    @Override
    @Transactional
    public void insertUser(UsuarioRequestDTO usuarioRequestDTO) {

        LocalDateTime fecha = LocalDateTime.now();
        Optional<Usuario> oUsuario = usuarioRepository.findById(usuarioRequestDTO.getRut());

        List<Contacto> listContactos = new ArrayList<>();

        if (usuarioRequestDTO.getContactos() == null) {
            usuarioRequestDTO.setContactos(listContactos);
        } else {
            for (int i = 0; i < usuarioRequestDTO.getContactos().size(); i++) {
                Contacto contacto = Contacto.builder()
                        .usuario(Usuario.builder().rut(usuarioRequestDTO.getRut()).build())
                        .numero(usuarioRequestDTO.getContactos().get(i).getNumero())
                        .codigoCiudad(usuarioRequestDTO.getContactos().get(i).getCodigoCiudad())
                        .codigoPais(usuarioRequestDTO.getContactos().get(i).getCodigoPais())
                        .build();
                listContactos.add(contacto);
            }
        }


        Usuario usuario = Usuario.builder()
                .rut(usuarioRequestDTO.getRut())
                .uuid(UUID.randomUUID().toString())
                .token(UUID.randomUUID().toString())
                .email(usuarioRequestDTO.getEmail())
                .nombre(usuarioRequestDTO.getNombre())
                .pwd(usuarioRequestDTO.getPwd())
                .estado(true)
                .fechaCreacion(fecha)
                .fechaActualizacion(fecha)
                .contactos(listContactos)
                .build();

        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void updateUser(UsuarioRequestDTO usuarioRequestDTO) {
        LocalDateTime fecha = LocalDateTime.now();
        List<Contacto> listContactos = new ArrayList<>();

        for (int i = 0; i < usuarioRequestDTO.getContactos().size(); i++) {
            Contacto contacto = Contacto.builder()
                    .usuario(Usuario.builder().rut(usuarioRequestDTO.getRut()).build())
                    .numero(usuarioRequestDTO.getContactos().get(i).getNumero())
                    .codigoPais(usuarioRequestDTO.getContactos().get(i).getCodigoPais())
                    .codigoCiudad(usuarioRequestDTO.getContactos().get(i).getCodigoCiudad())
                    .build();
            listContactos.add(contacto);
        }

        Usuario usuario = Usuario.builder()
                .uuid(UUID.randomUUID().toString())
                .rut(usuarioRequestDTO.getRut())
                .pwd(usuarioRequestDTO.getPwd())
                .nombre(usuarioRequestDTO.getNombre())
                .contactos(usuarioRequestDTO.getContactos())
                .email(usuarioRequestDTO.getEmail())
                .fechaCreacion(fecha)
                .fechaActualizacion(fecha)
                .build();
        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioResponseDTO obtenerUsuario(String id) {
        Optional<Usuario> oUsuario = usuarioRepository.findById(id);

        if (!oUsuario.isPresent()){
            throw new ErrorException(HttpStatus.NOT_FOUND, "No se encuentra el registro");
        }

        Usuario usuario = oUsuario.get();
        List <ContactoResponseDTO> contactosu = usuario.getContactos()
                .stream()
                .map(contacto -> ContactoResponseDTO.builder()
                .numero(contacto.getNumero())
                .codigoPais(contacto.getCodigoPais())
                .codigoCiudad(contacto.getCodigoCiudad())
                .build())
                .collect(Collectors.toList());

        return UsuarioResponseDTO.builder()
                    .rut(oUsuario.get().getRut())
                    .uuid(oUsuario.get().getUuid())
                    .nombre(oUsuario.get().getNombre())
                    .email(oUsuario.get().getEmail())
                    .estado(oUsuario.get().isEstado())
                    .fechaActualizacion(oUsuario.get().getFechaActualizacion())
                    .fechaCreacion(oUsuario.get().getFechaCreacion())
                    .estado(oUsuario.get().isEstado())
                    .contactos(contactosu)
                    .build();

    }
}
