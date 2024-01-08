package com.example.bds.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 2005348851282764710L;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "fullname", nullable = true)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = true)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(mappedBy = "staffs")
    private List<CustomerEntity> customers;

    @ManyToMany(mappedBy = "staffs")
    private List<BuildingEntity> buildings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();


}
