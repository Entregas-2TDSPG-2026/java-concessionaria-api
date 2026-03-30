package br.com.fiap.services;

import br.com.fiap.dtos.SaleRequest;
import br.com.fiap.models.enums.VehicleStatus;
import br.com.fiap.models.Sale;
import br.com.fiap.repositories.SaleRepository;
import br.com.fiap.repositories.VehicleRepository;
import br.com.fiap.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    public Sale getById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Sale create(SaleRequest request) {
        var vehicle = vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não existe"));

        if (vehicle.getStatus() != VehicleStatus.DISPONIVEL) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Vehicle is not available for sale");
        }

        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        vehicle.setStatus(VehicleStatus.VENDIDO);
        vehicleRepository.save(vehicle);

        var sale = new Sale();
        sale.setVehicle(vehicle);
        sale.setCustomer(customer);
        sale.setFinalPrice(request.finalPrice());

        return saleRepository.save(sale);
    }

    public void delete(Long id) {
        var sale = getById(id);
        sale.getVehicle().setStatus(VehicleStatus.DISPONIVEL);
        vehicleRepository.save(sale.getVehicle());
        saleRepository.deleteById(id);
    }
}