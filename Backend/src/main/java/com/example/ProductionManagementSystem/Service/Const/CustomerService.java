package com.example.ProductionManagementSystem.Service.Const;

import com.example.ProductionManagementSystem.Constants.Constants;
import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.User.Customer;
import com.example.ProductionManagementSystem.Repo.Const.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() throws ServiceException {
        List<Customer> customers = customerRepository.findAll();

        if (customers == null || customers.isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_CONTENT);
        }

        return customers;
    }

    public Customer addCustomer(Customer customer) throws ServiceException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ServiceException(ErrorResponse.EMAIL_ALREADY_EXIST);
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) throws ServiceException {
        if (!customerRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NO_CUSTOMER);
        }
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) throws ServiceException {

        Customer existingCustomer = customerRepository.findById(id)
            .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CUSTOMER));

        if (!Constants.isValidEmail(updatedCustomer.getEmail())) {
            throw new ServiceException(ErrorResponse.VALID_EMAIL);
        }

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setFax(updatedCustomer.getFax());

        return customerRepository.save(existingCustomer);
    }
}
