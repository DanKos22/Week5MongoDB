package ie.atu.week5.customerapp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerOrderController {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    List<Customer>customers = new ArrayList<>();

    public CustomerOrderController(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/customer-with-orders")
    public ResponseEntity<String> createCustomerWithOrders(@Valid @RequestBody CustomerOrderRequest customerOrderRequest) {

        // 1. Save the Customer and get the generated customer ID
        Customer customer = customerOrderRequest.getCustomer();
        Customer savedCustomer = customerRepository.save(customer);
        String customerId = savedCustomer.getId();


        // 2. Save the Orders and link them to the customer
        List<Order> orders = customerOrderRequest.getOrders();
        //Iterate over each Order object in the list of orders
        for(Order order : orders){
            order.setCustomerId(customer.getId());
            orderRepository.save(order);
        }
        return ResponseEntity.ok("Customer and orders created successfully");
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomersWithOrders(@PathVariable String customerId, @RequestBody Customer customer, @RequestBody Order order){
        if(customerRepository.existsById(customerId)){
            customer.setId(customerId);
            customerRepository.save(customer);
        }
        if(orderRepository.existsById(customerId)){
            order.setId(customerId);
            order.setCustomerId(customer.getId());
            orderRepository.save(order);
        }
        return ResponseEntity.ok("Customer and orders updated successfully");
    }


    @DeleteMapping("/deleteOrders/{customerId}")
    public ResponseEntity<String> deleteCustomersWithOrders(@PathVariable String customerId){

        //Find all orders associated with the given customerId
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        //If any associated orders exist, delete them
        if(!orders.isEmpty()){
            orderRepository.deleteAll(orders);
        }

        //If the customer exist, delete them
        //Optional is used to handle the case when the customer may or may not exist in the database
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            customerRepository.deleteById(customerId);
        }
        return ResponseEntity.ok("Customer and orders deleted successfully");
    }
}
