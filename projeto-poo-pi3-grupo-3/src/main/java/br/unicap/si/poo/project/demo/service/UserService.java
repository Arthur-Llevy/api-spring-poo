package br.unicap.si.poo.project.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unicap.si.poo.project.demo.Exception.InvalidIdParameter;
import br.unicap.si.poo.project.demo.Exception.ModelNotFoundException;
import br.unicap.si.poo.project.demo.Exception.NullOrEmptyFieldsException;
import br.unicap.si.poo.project.demo.model.UserModel;
import br.unicap.si.poo.project.demo.model.VehicleModel;
import br.unicap.si.poo.project.demo.repository.UserRepository;
import br.unicap.si.poo.project.demo.repository.VehicleRepository;
import br.unicap.si.poo.project.demo.utils.VerifyNullAndEmptyFields;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findById(Long id) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }

        Optional<UserModel> result = userRepository.findById(id);

        if (result.isPresent()) {
            UserModel user = result.get();
            return user;
        }
        throw new ModelNotFoundException("user");
    }

    public UserModel save(UserModel user) {
        VerifyNullAndEmptyFields<UserModel> verifyNullFields = new VerifyNullAndEmptyFields<>();
        ArrayList<String> nullFields = verifyNullFields.verify(user);

        if (!nullFields.isEmpty()) {
            String errorMessage = String.join(", ", nullFields);
            throw new NullOrEmptyFieldsException(errorMessage);
        }

        return userRepository.save(user);
    }

    public UserModel addFavoriteVehicle(Long userId, Long vehicleId) {
        if (userId < 0 || vehicleId < 0) {
            throw new InvalidIdParameter();
        }

        Optional<UserModel> userOpt = userRepository.findById(userId);
        Optional<VehicleModel> vehicleOpt = vehicleRepository.findById(vehicleId);

        if (userOpt.isEmpty()) {
            throw new ModelNotFoundException("user");
        }

        if (vehicleOpt.isEmpty()) {
            throw new ModelNotFoundException("vehicle");
        }

        UserModel user = userOpt.get();
        user.getFavorites().add(vehicleOpt.get());
        userRepository.save(user); 
        return user;
    }


    public UserModel update(Long id, UserModel updatedUser) {
        if (id < 0) {
            throw new InvalidIdParameter();
        }

        Optional<UserModel> existingUserOpt = userRepository.findById(id);
        
        if (existingUserOpt.isPresent()) {
            UserModel existingUser = existingUserOpt.get();
            StringBuilder errorStack = new StringBuilder();
            
            if (updatedUser.getBirthDate() != null) {
                if (!updatedUser.getBirthDate().isEmpty()) {
                    existingUser.setBirthDate(updatedUser.getBirthDate());
                } else {
                    errorStack.append(" birthDate");
                }
            }
            if (updatedUser.getCpf() != null) {
                if (!updatedUser.getCpf().isEmpty()) {
                    existingUser.setCpf(updatedUser.getCpf());
                } else {
                    errorStack.append(" cpf");
                }
            }
            if (updatedUser.getEmail() != null) {
                if (!updatedUser.getEmail().isEmpty()) {
                    existingUser.setEmail(updatedUser.getEmail());
                } else {
                    errorStack.append(" email");
                }
            }
            if (updatedUser.getName() != null) {
                if (!updatedUser.getName().isEmpty()) {
                    existingUser.setName(updatedUser.getName());
                } else {
                    errorStack.append(" name");
                }
            }
            if (updatedUser.getPassword() != null) {
                if (!updatedUser.getPassword().isEmpty()) {
                    existingUser.setPassword(updatedUser.getPassword());
                } else {
                    errorStack.append(" password");
                }
            }
      
            if (updatedUser.getPhoneNumber() != null) {
                if (!updatedUser.getPhoneNumber().isEmpty()) {
                    existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                } else {
                    errorStack.append(" phoneNumber");
                }
            }

             if (errorStack.length() > 0) {
                throw new NullOrEmptyFieldsException(errorStack.toString());
            }

            return userRepository.save(existingUser);
        } else {
            throw new ModelNotFoundException("user");
        }
    }

   public void deleteUser(Long id) {
        if (id == null || id < 0) {
            throw new InvalidIdParameter();
        }
        
        Optional<UserModel> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty()) {
            throw new ModelNotFoundException("user");
        }
        
        userRepository.deleteById(id);
    }
}
