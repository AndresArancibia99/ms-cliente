package cl.everis.cliente.DTO;


import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactoResponseDTO implements Serializable {

    private String numero;

    private String codigoCiudad;

    private String codigoPais;
}
