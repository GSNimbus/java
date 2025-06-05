package com.gsnimbus.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nm_usuario")
    private String username;
    @Column(name = "nm_email", unique = true, nullable = false)
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @Column(name = "nm_senha")
    private String password;
}
