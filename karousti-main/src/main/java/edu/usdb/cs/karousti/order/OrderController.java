package edu.usdb.cs.karousti.order;

import edu.usdb.cs.karousti.emailing.EmailingService;
import edu.usdb.cs.karousti.order.requests.OrderCreationRequest;
import edu.usdb.cs.karousti.order.responses.OrderCreationResponse;
import edu.usdb.cs.karousti.order.services.OrderService;
import edu.usdb.cs.karousti.payment.Payment;
import edu.usdb.cs.karousti.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final EmailingService emailingService;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderCreationResponse> createOrder(@RequestBody OrderCreationRequest request) {
        Order order = Order.builder()
                .carModel(request.getCarModel())
                .carColor(request.getCarColor())
                .build();

        Payment payment = Payment.builder()
                .amount((float) (request.getCarModel().getBasePrice() * 0.10))
                .build();

        paymentService.processInitialPayment(payment);

        orderService.createOrder(order);

        emailingService.sendConfirmationEmail();

        return ResponseEntity.ok(OrderCreationResponse.builder()
                .message("Order created successfully")
                .build());
    }
}
