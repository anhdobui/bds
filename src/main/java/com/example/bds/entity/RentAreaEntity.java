package com.example.bds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rentarea")
@Getter
@Setter
public class RentAreaEntity extends BaseEntity {
    @Column
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;
}
