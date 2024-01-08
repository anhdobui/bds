package com.example.bds.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuildingDTO {
    private Long id;
    @NotEmpty(message = "Name cannot be blank")
    private String name;
    private String address;
    private String ward;
    private String street;
    private Long staffId;
    private Integer floorArea;
    private Integer numberOfBasement;
    private String districtCode;
    private String direction;
    private String level;
    private String managerName;
    private String managerPhone;
    private Integer rentPrice;
    private String rentPriceDescription;
    private String serviceFee;
    private String type;
    private String rentArea;

    private String carFee;
    private String motoFee;
    private String overtimeFee;
    private String waterFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String renttime;
    private String note;
    private String linkOfBuilding;
    private String map;

    private BigDecimal brokeragetee;

    private String image;

    private String imageBase64;

    private String imageName;
}
