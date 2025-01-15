package com.example.Backend.Controller.User;

import com.example.Backend.Api.User.UserApi;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.User;
import com.example.Backend.Service.User.UserService;
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
    public User updateUser(@PathVariable Long id, @RequestBody User user) throws ServiceException {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws ServiceException {
        userService.deleteUser(id);
    }

}
