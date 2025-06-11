package br.unicap.si.poo.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.unicap.si.poo.project.demo.model.UserModel;
import br.unicap.si.poo.project.demo.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getAllUser() {
        return userService.findAll();
    }

    @GetMapping({"/{id}"})
    public UserModel getUserById(@PathVariable Long id) {
       try {
            return userService.findById(id);
       } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
       } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
       }
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        try {
            return userService.save(user);
        } catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping("/{userId}/favorite/{vehicleId}")
    public UserModel addFavorite(@PathVariable Long userId, @PathVariable Long vehicleId) {
        try {
            return userService.addFavoriteVehicle(userId, vehicleId);
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        } 
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        try {
            return userService.update(id, user);
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); 
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
