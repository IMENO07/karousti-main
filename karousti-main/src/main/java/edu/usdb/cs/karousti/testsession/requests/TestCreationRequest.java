package edu.usdb.cs.karousti.test_session.requests;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCreationRequest {
    private Date date;
    private Time time;
}
