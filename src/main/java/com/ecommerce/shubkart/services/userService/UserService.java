package com.ecommerce.shubkart.services.userService;

import com.ecommerce.shubkart.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void createUser(@RequestBody User user);
    void updateUser(@RequestBody User user, @PathVariable String userName);

    void deleteByUserName(String userName);
}
