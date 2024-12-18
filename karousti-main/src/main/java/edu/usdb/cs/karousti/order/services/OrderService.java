package edu.usdb.cs.karousti.order.services;

import edu.usdb.cs.karousti.car.CarRepository;
import edu.usdb.cs.karousti.order.Order;
import edu.usdb.cs.karousti.order.OrderRepository;
import edu.usdb.cs.karousti.order.requests.OrderCreationRequest;
import edu.usdb.cs.karousti.user.customer.Customer;
import edu.usdb.cs.karousti.user.customer.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public void createOrder(Order order) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();

        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order = order.toBuilder()
                .customer(customer)
                .orderDate(LocalDateTime.now())
                .build();

        orderRepository.save(order);
    }

}
