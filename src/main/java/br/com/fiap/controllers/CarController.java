package br.com.fiap.controllers;

import br.com.fiap.models.enums.VehicleType;
import br.com.fiap.models.Vehicle;
import br.com.fiap.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public List<Vehicle> getAll() {
        return service.getAllVehicle().stream()
                .filter(v -> v.getType() == VehicleType.CAR)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        var vehicle = service.getVehicleById(id);
        if (vehicle.getType() != VehicleType.CAR) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }
}