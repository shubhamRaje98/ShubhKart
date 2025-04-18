package com.ecommerce.shubkart.repositories;

import com.ecommerce.shubkart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();

    User save(User user);


    void deleteByUsername(String userName);
}
