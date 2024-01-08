package com.example.bds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity extends BaseEntity {
    @Column
    private String code;
    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;

}
