package cl.everis.cliente.DTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO implements Serializable {

    private String uuid;
    private String rut;
    private String nombre;
    private String email;
    private LocalDateTime fechaCreacion;
    private boolean estado;
    private LocalDateTime fechaActualizacion;
    private List<ContactoResponseDTO> contactos;
}
