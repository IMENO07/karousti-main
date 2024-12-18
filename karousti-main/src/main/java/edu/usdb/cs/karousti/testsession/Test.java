package edu.usdb.cs.karousti.testsession;

import java.sql.Date;
import java.sql.Time;

import edu.usdb.cs.karousti.car.Car;
import edu.usdb.cs.karousti.enums.TestStatus;
import edu.usdb.cs.karousti.user.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer userId;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car carId;

    // @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TestStatus status;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private Time time;
}
