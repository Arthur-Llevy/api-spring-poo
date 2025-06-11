package br.unicap.si.poo.project.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.unicap.si.poo.project.demo.dto.SaleHistoryRequestDTO;
import br.unicap.si.poo.project.demo.model.SaleHistoryModel;
import br.unicap.si.poo.project.demo.service.SaleHistoryService;

@RestController
@RequestMapping("/saleHistory")
public class SaleHistoryController {

    @Autowired
    private SaleHistoryService saleHistoryService;

    @GetMapping
    public List<SaleHistoryModel> getAll() {
        return saleHistoryService.findAll();
    }

    @GetMapping("/{id}")
    public SaleHistoryModel getAllSalesFromHistory(@PathVariable Long id) {
        try {
            return saleHistoryService.findById(id);
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping("/insertIntoHistory/{sellerId}/{vehicleId}")
    public SaleHistoryModel createSaleToHistory(@RequestBody SaleHistoryModel saleHistory, @PathVariable Long sellerId, @PathVariable Long vehicleId) {
        try {
            return saleHistoryService.save(saleHistory, sellerId, vehicleId);
        } catch (NullOrEmptyFieldsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public SaleHistoryModel updateSaleFromHistory(@PathVariable Long id, @RequestBody SaleHistoryRequestDTO saleHistoryModel) {
        try {
            SaleHistoryModel result = saleHistoryService.update(id, saleHistoryModel);
            return result;
        } catch (InvalidIdParameter exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (ModelNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}