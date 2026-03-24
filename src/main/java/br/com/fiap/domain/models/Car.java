package br.com.fiap.domain.models;

import br.com.fiap.domain.models.enums.VehicleColor;
import br.com.fiap.domain.models.enums.VehicleBrand;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String version;
    private Integer year;
    private BigDecimal price;
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    private VehicleColor color;

    private String customColor;

    @Enumerated(EnumType.STRING)
    private VehicleBrand brand;
}