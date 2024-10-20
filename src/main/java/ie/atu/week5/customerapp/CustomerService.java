package ie.atu.week5.customerapp;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    private List<Customer>customerList = new ArrayList<>();


    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public List<Customer> getCustomerById(String id){
        customerRepository.findById(id);
        return customerRepository.findAll();
    }
    public List<Customer>createCustomer(Customer customer){
        customerRepository.save(customer);
        return customerRepository.findAll();
    }

    public List<Customer>updateCustomer(String id, Customer customer){
        if(customerRepository.existsById(id)){
            customer.setId(id);
            customerRepository.save(customer);
        }
        return customerRepository.findAll();
    }
    public List<Customer>deleteCustomer(String id){

        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
        return customerRepository.findAll();
    }
}
