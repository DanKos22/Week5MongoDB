package ie.atu.week5.customerapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;

    List<Order> orders = new ArrayList<>();


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByCustomerId(String customerId){
        orderRepository.findByCustomerId(customerId);
        return orderRepository.findAll();
    }

    public List<Order> createOrder(Order order){
        orderRepository.save(order);
        return orderRepository.findAll();
    }

    public List<Order> updateOrder(String id, Order order){
        if(orderRepository.existsById(id)){
            order.setId(id);
            //order.setCustomerId(customer.getId());
            orderRepository.save(order);
        }
        return orderRepository.findAll();
    }

    public List<Order> deleteOrder(String id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }
        return orderRepository.findAll();
    }
}
