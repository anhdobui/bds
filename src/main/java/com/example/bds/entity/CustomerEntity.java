package com.example.bds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity extends BaseEntity {
    @Column(name = "fullname")
    private String fullName;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String company;

    @Column
    private String desire;

    @Column
    private String note;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid"),
            inverseJoinColumns = @JoinColumn(name = "staffid"))
    private List<UserEntity> staffs;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private List<TransactionEntity> transactions;
}
