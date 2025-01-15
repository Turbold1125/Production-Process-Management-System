package com.example.Backend.Repo.Const;

import com.example.Backend.Model.User.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
