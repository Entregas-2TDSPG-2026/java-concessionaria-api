package br.com.fiap.models;

import br.com.fiap.models.enums.CarBrand;
import br.com.fiap.models.enums.MotorcycleBrand;
import br.com.fiap.models.enums.VehicleColor;
import br.com.fiap.models.enums.VehicleStatus;
import br.com.fiap.models.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String model;
    private String version;
    private Integer vehicleYear;
    private BigDecimal price;
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    private VehicleColor color;

    private String customColor;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Enumerated(EnumType.STRING)
    private CarBrand carBrand;

    @Enumerated(EnumType.STRING)
    private MotorcycleBrand motorcycleBrand;
}
