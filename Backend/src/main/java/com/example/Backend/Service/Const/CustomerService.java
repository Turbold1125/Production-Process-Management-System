package com.example.Backend.Service.Const;

import com.example.Backend.Constants.Constants;
import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.Customer;
import com.example.Backend.Repo.Const.CustomerRepository;
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

    public void deleteCustomer(Long id) throws ServiceException {
        if (!customerRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NO_CUSTOMER);
        }
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) throws ServiceException {

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
