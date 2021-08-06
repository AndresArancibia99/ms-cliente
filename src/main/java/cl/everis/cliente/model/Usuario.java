package cl.everis.cliente.model;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "rut")
    private String rut;

    @Column(name= "uuid")
    private String uuid;

    @Column(name="token")
    private String token;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "pwd", length = 40)
    private String pwd;

    @Column(name = "email", length=50)
    //@Email(message = "El email ingresado no es valido")
    private String email;

    @OneToMany
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    private List<Contacto> contactos;

    @Column(name= "estado", length = 50)
    private boolean estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;


    String regex = "z.*t?";


}
