package br.unicap.si.poo.project.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleModel {
    @Id @GeneratedValue
    Long id;

    String vehicleType;
    String model;
    String brand;
    Double price;
    String fuelType;
    String transmissionType;
    String photo;
    String color;

    @ManyToMany(mappedBy = "favorites")
    @JsonIgnore
    private List<UserModel> users = new ArrayList<>();

    @OneToOne(mappedBy = "vehicle") 
    @JsonIgnore
    private SaleHistoryModel saleHistory;
}