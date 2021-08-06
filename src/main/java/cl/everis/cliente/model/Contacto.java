package cl.everis.cliente.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacto;

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

    @ManyToOne
    @JoinColumn(name="rut")
    private Usuario usuario;

}
