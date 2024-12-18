package edu.usdb.cs.karousti.order;

import edu.usdb.cs.karousti.car.Car;
import edu.usdb.cs.karousti.enums.Color;
import edu.usdb.cs.karousti.model.Model;
import edu.usdb.cs.karousti.user.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_model", nullable = false)
    private Model carModel;

    @Column(name = "car_color", nullable = false)
    private Color carColor;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

}
