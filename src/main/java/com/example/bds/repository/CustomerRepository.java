package com.example.bds.repository;

import com.example.bds.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByIdAndStaffs_Id(Long id, Long staffid);
}
