package edu.usdb.cs.karousti.test_session;

import java.sql.Date;
import java.sql.Time;

import edu.usdb.cs.karousti.enums.TestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "user_id", nullable = false)
    private String user_id;

    @ManyToOne
    @Column(name = "car_id", nullable = false)
    private String car_id;

    // @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private TestStatus status;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private Time time;
}
