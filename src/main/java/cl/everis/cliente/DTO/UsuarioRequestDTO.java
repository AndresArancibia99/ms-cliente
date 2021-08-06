package cl.everis.cliente.DTO;


import cl.everis.cliente.model.Contacto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO implements Serializable {

    @NotBlank
    private String rut;

    private String uuid;
    @NotBlank
    private String nombre;
    @Email(message = "El email ingresado no es valido")
    @NotBlank
    private String email;
    @NotNull

    //@Pattern(regexp = "^(?=.*\\d).{6,15}$", message = "contraseña invalida")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$", message = "debe contener almenos una mayuscula, letras minusculas, dos numeros y 5 caracteres")
    private String pwd;

    private boolean estado;

    private String token;

    private List<Contacto> contactos;
}
