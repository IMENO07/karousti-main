package edu.usdb.cs.karousti.order.requests;

import edu.usdb.cs.karousti.enums.Color;
import edu.usdb.cs.karousti.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationRequest {
    private Model carModel;
    private Color carColor;
}
