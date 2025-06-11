package br.unicap.si.poo.project.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import br.unicap.si.poo.project.demo.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unicap.si.poo.project.demo.Exception.InvalidIdParameter;
import br.unicap.si.poo.project.demo.Exception.ModelNotFoundException;
import br.unicap.si.poo.project.demo.Exception.NullOrEmptyFieldsException;
import br.unicap.si.poo.project.demo.dto.SaleHistoryRequestDTO;
import br.unicap.si.poo.project.demo.model.SaleHistoryModel;
import br.unicap.si.poo.project.demo.model.SellerModel;
import br.unicap.si.poo.project.demo.model.VehicleModel;
import br.unicap.si.poo.project.demo.repository.SaleHistoryRepository;
import br.unicap.si.poo.project.demo.repository.SellerRepository;

@Service
public class SaleHistoryService {
    @Autowired
    private SaleHistoryRepository saleHistoryRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<SaleHistoryModel> findAll() {
        return saleHistoryRepository.findAll();
    }

    public SaleHistoryModel findById(Long id) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }

        Optional<SaleHistoryModel> result = saleHistoryRepository.findById(id);
        if (result.isPresent()) {
            SaleHistoryModel saleHistory = result.get();
            return saleHistory;
        }

        throw new ModelNotFoundException("saleHistory");
    }

    public SaleHistoryModel save(SaleHistoryModel saleHistory, Long sellerId, Long vehicleId) {
        if (sellerId < 0 || vehicleId < 0) {
            throw new InvalidIdParameter();
        }

        Optional<SellerModel> seller = sellerRepository.findById(sellerId);
        Optional<VehicleModel> vehicle = vehicleRepository.findById(vehicleId);

        if (seller.isEmpty()) {
            throw new ModelNotFoundException("seller");
        }

        if (vehicle.isEmpty()) {
            throw new ModelNotFoundException("vehicle");
        }

        SellerModel sellerToGetId = seller.get();
        VehicleModel vehicleToGetId = vehicle.get();

        ArrayList<String> nullFields = new ArrayList<>();

        if (saleHistory.getCity() == null || saleHistory.getCity().isEmpty()) {
            nullFields.add("city");
        }

        if (saleHistory.getState() == null || saleHistory.getState().isEmpty()) {
            nullFields.add("state");
        }

        saleHistory.setSeller(sellerToGetId);
        saleHistory.setVehicle(vehicleToGetId);
       
        if (!nullFields.isEmpty()) {
            String errorMessage = String.join(", ", nullFields);
            throw new NullOrEmptyFieldsException(errorMessage);
        }

        return saleHistoryRepository.save(saleHistory);
    }


   public SaleHistoryModel update(Long id, SaleHistoryRequestDTO saleHistoryDTO) {
        Optional<SaleHistoryModel> registerExists = saleHistoryRepository.findById(id);
        Optional<SellerModel> seller = Optional.empty(); 
        Optional<VehicleModel> vehicle = Optional.empty();

        if (registerExists.isEmpty()) {
            throw new ModelNotFoundException("sale history");
        }

        if (saleHistoryDTO.getCity() != null && !saleHistoryDTO.getCity().isEmpty()) {
            registerExists.get().setCity(saleHistoryDTO.getCity());
        }

        if (saleHistoryDTO.getState() != null && !saleHistoryDTO.getState().isEmpty()) {
            registerExists.get().setState(saleHistoryDTO.getState());
        }

        if (saleHistoryDTO.getSellerId() != null) {
            if (saleHistoryDTO.getSellerId() < 0) {
                throw new InvalidIdParameter();
            }

            seller = sellerRepository.findById(saleHistoryDTO.getSellerId());
            if (seller.isEmpty()) {
                throw new ModelNotFoundException("seller");
            }
        }

        if (saleHistoryDTO.getVehicleId() != null) {
            if (saleHistoryDTO.getVehicleId() < 0) {
                throw new InvalidIdParameter();
            }

            vehicle = vehicleRepository.findById(saleHistoryDTO.getVehicleId());
            if (vehicle.isEmpty()) {
                throw new ModelNotFoundException("vehicle");
            }
        }

        if (vehicle.isPresent()) {
            registerExists.get().setVehicle(vehicle.get());
        }

        if (seller.isPresent()) {
            registerExists.get().setSeller(seller.get());
        }

        return saleHistoryRepository.save(registerExists.get());
    }
}
