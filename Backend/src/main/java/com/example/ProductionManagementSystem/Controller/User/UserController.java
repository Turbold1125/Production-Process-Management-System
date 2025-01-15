package com.example.ProductionManagementSystem.Controller.User;

import com.example.ProductionManagementSystem.Api.User.UserApi;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.User.User;
import com.example.ProductionManagementSystem.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() throws ServiceException {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) throws ServiceException {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) throws ServiceException {
        userService.deleteUser(id);
    }

}
