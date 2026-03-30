package br.com.fiap.services;

import br.com.fiap.models.Vehicle;
import br.com.fiap.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> getAllVehicle(){
        return repository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle){
        return repository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteVehicle(Long id){
        repository.deleteById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        getVehicleById(id);
        vehicle.setId(id);
        return repository.save(vehicle);
    }
}