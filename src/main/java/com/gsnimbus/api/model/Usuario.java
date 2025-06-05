package com.gsnimbus.api.model;


import jakarta.persistence.*;
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
    @Column(name = "nm_email")
    private String email;
    @Column(name = "nm_senha")
    private String password;
}
