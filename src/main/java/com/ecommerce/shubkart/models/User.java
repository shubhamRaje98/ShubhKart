package com.ecommerce.shubkart.models;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;
}
