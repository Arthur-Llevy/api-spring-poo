package br.unicap.si.poo.project.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.unicap.si.poo.project.demo.abstractsClasses.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class SellerModel extends User {
    @Id @GeneratedValue
    Long id;
    String name;
    String email;
    String phoneNumber;
    String cpf;
    String birthDate;
    String password;

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private List<SaleHistoryModel> saleHistory = new ArrayList<>();
}
