package br.unicap.si.poo.project.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleHistoryRequestDTO {
    Long sellerId;
    Long vehicleId;
    String state;
    String city;
}
