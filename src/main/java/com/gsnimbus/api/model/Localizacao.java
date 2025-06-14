package com.gsnimbus.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_localizacao")
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacao")
    private Long id;

    @Digits(integer = 3, fraction = 5)
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(precision = 8, scale = 5, name = "nr_longitude")
    private BigDecimal longitude;

    @Digits(integer = 2, fraction = 5)
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @Column(precision = 7, scale = 5, name = "nr_latitude")
    private BigDecimal latitude;
}
