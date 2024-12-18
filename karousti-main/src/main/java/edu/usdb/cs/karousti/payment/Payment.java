package edu.usdb.cs.karousti.payment;

import edu.usdb.cs.karousti.enums.OrderPaymentStatus;
import edu.usdb.cs.karousti.user.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "status", nullable = false)
    private OrderPaymentStatus status;

}
