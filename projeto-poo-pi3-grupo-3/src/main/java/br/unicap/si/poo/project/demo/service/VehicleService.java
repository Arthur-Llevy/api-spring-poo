package br.unicap.si.poo.project.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unicap.si.poo.project.demo.model.VehicleModel;
import br.unicap.si.poo.project.demo.repository.VehicleRepository;
import br.unicap.si.poo.project.demo.utils.VerifyNullAndEmptyFields;
import br.unicap.si.poo.project.demo.Exception.InvalidIdParameter;
import br.unicap.si.poo.project.demo.Exception.InvalidNumberField;
import br.unicap.si.poo.project.demo.Exception.ModelNotFoundException;
import br.unicap.si.poo.project.demo.Exception.NullOrEmptyFieldsException;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleModel> findAll() {
        return vehicleRepository.findAll();
    }

    public VehicleModel findById(Long id) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }
        return vehicleRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Vehicle"));
    }

   public VehicleModel save(VehicleModel vehicle) {
       VerifyNullAndEmptyFields<VehicleModel> verifyNullFields = new VerifyNullAndEmptyFields<>();
       ArrayList<String> nullFields = verifyNullFields.verify(vehicle);
       
        if (!nullFields.isEmpty()) {
            String errorMessage = String.join(", ", nullFields);
            throw new NullOrEmptyFieldsException(errorMessage);
        }

       if (vehicle.getPrice() < 0)  {
           throw new InvalidNumberField("price");
       }

        return vehicleRepository.save(vehicle);
    }

    public VehicleModel update(Long id, VehicleModel updatedVehicle) {
        Optional<VehicleModel> existingVehicleOpt = vehicleRepository.findById(id);

        if (existingVehicleOpt.isEmpty()) {
            throw new ModelNotFoundException("vehicle");
        }
        
        StringBuilder errorStack = new StringBuilder();
        VehicleModel existingVehicle = existingVehicleOpt.get();
        
        if (updatedVehicle.getVehicleType() != null) {
            if (!updatedVehicle.getVehicleType().isEmpty()) {
                existingVehicle.setVehicleType(updatedVehicle.getVehicleType());
            } else {
                errorStack.append(" type");
            }
        }
        if (updatedVehicle.getModel() != null) {
            if (!updatedVehicle.getModel().isEmpty()) {
                existingVehicle.setModel(updatedVehicle.getModel());
            } else {
                errorStack.append(" model");
            }
        }
        
        if (updatedVehicle.getBrand() != null) {
            existingVehicle.setBrand(updatedVehicle.getBrand());
            if (!updatedVehicle.getBrand().isEmpty()) {
                existingVehicle.setBrand(updatedVehicle.getBrand());
            } else {
                errorStack.append(" brand");
            }
        }
        if (updatedVehicle.getPrice() != null) {
            existingVehicle.setPrice(updatedVehicle.getPrice());

            if (updatedVehicle.getPrice() < 0) {
                throw new InvalidNumberField("price");
            }
        }
        if (updatedVehicle.getFuelType() != null) {
            if (!updatedVehicle.getFuelType().isEmpty()) {
                existingVehicle.setFuelType(updatedVehicle.getFuelType());
            } else {
                errorStack.append(" fuelType");
            }
        }
        if (updatedVehicle.getTransmissionType() != null) {
            if (!updatedVehicle.getTransmissionType().isEmpty()) {
                existingVehicle.setTransmissionType(updatedVehicle.getTransmissionType());
            } else {
                errorStack.append(" transmissionType");
            }
        }
        if (updatedVehicle.getPhoto() != null) {
            if (!updatedVehicle.getPhoto().isEmpty()) {
                existingVehicle.setPhoto(updatedVehicle.getPhoto());
            } else {
                errorStack.append(" photo");
            }
        }
        if (updatedVehicle.getColor() != null) {
            if (!updatedVehicle.getColor().isEmpty()) {
                existingVehicle.setColor(updatedVehicle.getColor());
            } else {
                errorStack.append(" color");
            }
        }

        if (errorStack.length() > 0) {
            throw new NullOrEmptyFieldsException(errorStack.toString());
        }

        return vehicleRepository.save(existingVehicle);
    }

    public VehicleModel deleteVehicle(Long id) {
        Optional<VehicleModel> vehicleToDelete = vehicleRepository.findById(id);

        if (!vehicleToDelete.isPresent()) {
            throw new ModelNotFoundException("vehicle");
        }

        VehicleModel vehicle = vehicleToDelete.get();

        vehicleRepository.deleteById(id);
        return vehicle;
    }
}
