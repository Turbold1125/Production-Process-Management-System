package com.example.Backend.Controller.Const;

import com.example.Backend.Api.Const.CustomerApi;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.Customer;
import com.example.Backend.Service.Const.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() throws ServiceException {
        return customerService.getAllCustomers();
    }

    @PostMapping("/create")
    public Customer createCustomer(Customer customer) throws ServiceException {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws ServiceException {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable(required = true) Long id) throws ServiceException {
        customerService.deleteCustomer(id);
    }
}
