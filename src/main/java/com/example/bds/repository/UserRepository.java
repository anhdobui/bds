package com.example.bds.repository;

import com.example.bds.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserName(String username);
    boolean existsByUserName(String username);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
}
