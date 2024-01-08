package com.example.bds.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BuildingSearchDTO {
    private String name;
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
    private Integer costRentFrom;
    private Integer costRentTo;
    private Integer rentAreaFrom;
    private Integer rentAreaTo;
    private List<String> types;
}
