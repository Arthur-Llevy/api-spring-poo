package br.unicap.si.poo.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.unicap.si.poo.project.demo.Exception.InvalidIdParameter;
import br.unicap.si.poo.project.demo.Exception.InvalidNumberField;
import br.unicap.si.poo.project.demo.Exception.ModelNotFoundException;
import br.unicap.si.poo.project.demo.Exception.NullOrEmptyFieldsException;
import br.unicap.si.poo.project.demo.model.VehicleModel;
import br.unicap.si.poo.project.demo.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<VehicleModel> getAllVehicles() {
        return vehicleService.findAll();
    }

   @GetMapping({"/{id}"})
    public VehicleModel getVehicleById(@PathVariable(required = true) Long id) {
        try {
            VehicleModel result = vehicleService.findById(id);
            return result;
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());  
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());  
        } 
    }

    @PostMapping
    public VehicleModel createVehicle(@RequestBody VehicleModel vehicle) {
        try {
            return vehicleService.save(vehicle);
        } catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());    
        } catch (InvalidNumberField exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());    
        }
    }

    @PutMapping("/{id}")
    public VehicleModel updateVehicle(@PathVariable Long id, @RequestBody VehicleModel vehicle) {
        if (id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be valid");    
        }

        try {
            VehicleModel result = vehicleService.update(id, vehicle);
            return result;
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }  catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (InvalidNumberField exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/{id}") 
    public VehicleModel deleteVehicle(@PathVariable Long id) {
        if (id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be valid");    
        }

        try {
            VehicleModel result = vehicleService.deleteVehicle(id);
            return result;  
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());  
        }
    }
}
