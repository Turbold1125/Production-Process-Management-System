package com.example.Backend.Repo.User;

import com.example.Backend.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByPhoneNumber(String phoneNumber);

//    @Query("SELECT u FROM User u JOIN u.userRoles ur WHERE ur.role.name = :roleName")
//    List<User> findByRoleName(String roleName);
//
    Optional<User> findByEmail(String email);
}

