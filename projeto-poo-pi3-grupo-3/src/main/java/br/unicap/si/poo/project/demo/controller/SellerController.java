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
import br.unicap.si.poo.project.demo.Exception.ModelNotFoundException;
import br.unicap.si.poo.project.demo.Exception.NullOrEmptyFieldsException;
import br.unicap.si.poo.project.demo.model.SellerModel;
import br.unicap.si.poo.project.demo.service.SellerService;

@RestController
@RequestMapping("/sellers")

public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public List<SellerModel> getAll() {
        return sellerService.findAll();
    }

    @GetMapping({"/{id}"})
    public SellerModel getSellerById(@PathVariable Long id) {
        try {
            return sellerService.findById(id);
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping
    public SellerModel createSeller(@RequestBody SellerModel seller) {
        try {
            return sellerService.save(seller);
        } catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());   
        }
    }

    @PutMapping("/{id}")
    public SellerModel updateUser(@PathVariable Long id, @RequestBody SellerModel seller) {
        try {
            return sellerService.update(id, seller);
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());   
        } catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());   
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());   
        }
    }

    @DeleteMapping("/{id}") 
    public SellerModel deleteUser(@PathVariable Long id) {
        try {
           return sellerService.deleteSeller(id);
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage()); 
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());   
        }     
    }
}
