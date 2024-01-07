package com.example.bds.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name="name")
    private String name;
    @Column(name="street")
    private String street;
    @Column(name="ward")
    private  String ward;
    @Column(name="district")
    private String districtCode;
    @Column(name="numberofbasement")
    private Integer numberOfBasement;
    @Column(name="direction")
    private String direction;
    @Column(name="level")
    private String level;
    @Column(name="floorarea")
    private Integer floorArea;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;
    @Column(name="rentprice")
    private Integer rentPrice;
    @Column(name="rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name="type")
    private String type;

    @Column(name="carfee")
    private String carFee;
    @Column(name="motofee")
    private String motoFee;
    @Column(name="overtimefee")
    private String overtimeFee;
    @Column(name="waterfee")
    private String waterFee;
    @Column(name="electricityfee")
    private String electricityFee;
    @Column(name="deposit")
    private String deposit;
    @Column(name="payment")
    private String payment;
    @Column(name="renttime")
    private String renttime;
    @Column(name="note")
    private String note;
    @Column(name="linkofbuilding")
    private String linkOfBuilding;
    @Column(name="map")
    private String map;
    @Column(name="avatar")
    private String avatar;
    @Column(name="brokeragetee",precision = 13, scale = 2)
    private BigDecimal brokeragetee;

    @OneToMany(mappedBy = "building",cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private List<RentAreaEntity> rentAreas;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid"),
            inverseJoinColumns = @JoinColumn(name = "staffid"))
    private List<UserEntity> staffs;
}
