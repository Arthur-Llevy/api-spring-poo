package br.unicap.si.poo.project.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unicap.si.poo.project.demo.Exception.InvalidIdParameter;
import br.unicap.si.poo.project.demo.Exception.ModelNotFoundException;
import br.unicap.si.poo.project.demo.Exception.NullOrEmptyFieldsException;
import br.unicap.si.poo.project.demo.model.SellerModel;
import br.unicap.si.poo.project.demo.repository.SellerRepository;
import br.unicap.si.poo.project.demo.utils.VerifyNullAndEmptyFields;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerModel> findAll() {
        return sellerRepository.findAll();
    }

    public SellerModel findById(Long id) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }
        
        Optional<SellerModel> result = sellerRepository.findById(id);
        if (result.isPresent()) {
            SellerModel seller = result.get();
            return seller;
        }

        throw new ModelNotFoundException("seller");
    }

    public SellerModel save(SellerModel seller) {
        VerifyNullAndEmptyFields<SellerModel> verifyNullFields = new VerifyNullAndEmptyFields<>();
        ArrayList<String> nullFields = verifyNullFields.verify(seller);

        if (!nullFields.isEmpty()) {
            String errorMessage = String.join(", ", nullFields);
            throw new NullOrEmptyFieldsException(errorMessage);
        }

        return sellerRepository.save(seller);
    }


    public SellerModel update(Long id, SellerModel sellerUpdate) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }

        Optional<SellerModel> existingSellerOpt = sellerRepository.findById(id);
        if (existingSellerOpt.isEmpty()) {
            throw new ModelNotFoundException("seller");
        }
        
        StringBuilder errorStack = new StringBuilder();
        SellerModel existingSeller = existingSellerOpt.get();
        
        if (sellerUpdate.getName() != null) {
            if (!sellerUpdate.getName().isEmpty()) {
                existingSeller.setName(sellerUpdate.getName());
            } else {
                errorStack.append(" name");
            }
        }
        if (sellerUpdate.getEmail() != null) {
            if (!sellerUpdate.getEmail().isEmpty()) {
                existingSeller.setEmail(sellerUpdate.getEmail());
            } else {
                errorStack.append(" email");
            }
        }
        if (sellerUpdate.getPhoneNumber() != null) {
            if (!sellerUpdate.getPhoneNumber().isEmpty()) {
                existingSeller.setPhoneNumber(sellerUpdate.getPhoneNumber());
            } else {
                errorStack.append(" phoneNumber");
            }
        }
        if (sellerUpdate.getCpf() != null) {
            if (!sellerUpdate.getCpf().isEmpty()) {
                existingSeller.setCpf(sellerUpdate.getCpf());
            } else {
                errorStack.append(" cpf");
            }
        }
        if (sellerUpdate.getBirthDate() != null) {
            if (!sellerUpdate.getBirthDate().isEmpty()) {
                existingSeller.setBirthDate(sellerUpdate.getBirthDate());
            } else {
                errorStack.append(" birthDate");
            }
        }
        if (sellerUpdate.getPassword() != null) {
            if (!sellerUpdate.getPassword().isEmpty()) {
                existingSeller.setPassword(sellerUpdate.getPassword());
            } else {
                errorStack.append(" password");
            }
        }

        if (errorStack.length() > 0) {
            throw new NullOrEmptyFieldsException(errorStack.toString());
        }

        return sellerRepository.save(existingSeller);
    }

    public SellerModel deleteSeller(Long id) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }

        Optional<SellerModel> sellerToDelete = sellerRepository.findById(id);

        if (sellerToDelete.isPresent()) {
            SellerModel seller = sellerToDelete.get();
            sellerRepository.deleteById(id);
            return seller;
        } 

        throw new ModelNotFoundException("seller");
    }
}
