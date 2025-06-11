package br.unicap.si.poo.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class SaleHistoryModel {
    @Id @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    SellerModel seller;

    @OneToOne
    @JoinColumn(name = "vehicle_id", unique = true)
    private VehicleModel vehicle;

    private String city;
    private String state;
}