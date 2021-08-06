package cl.everis.cliente.service;

import cl.everis.cliente.DTO.UsuarioRequestDTO;
import cl.everis.cliente.DTO.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {


    List<UsuarioResponseDTO> findAllUser();
    void insertUser(UsuarioRequestDTO usuarioRequestDTO);
    void updateUser(UsuarioRequestDTO usuarioRequestDTO);
    UsuarioResponseDTO obtenerUsuario(String id);
}
