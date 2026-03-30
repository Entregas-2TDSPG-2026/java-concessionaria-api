package br.com.fiap.controllers;

import br.com.fiap.models.enums.VehicleType;
import br.com.fiap.models.Vehicle;
import br.com.fiap.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycles")
@CrossOrigin(origins = "*")
public class MotorcycleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public List<Vehicle> getAll() {
        return service.getAllVehicle().stream()
                .filter(v -> v.getType() == VehicleType.MOTORCYCLE)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        var vehicle = service.getVehicleById(id);
        if (vehicle.getType() != VehicleType.MOTORCYCLE) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }
}