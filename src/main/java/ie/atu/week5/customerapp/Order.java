package ie.atu.week5.customerapp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    @NotNull(message = "Order code is mandatory")
    private int orderCode;
    @NotBlank(message = "Order details are mandatory")
    private String orderDetails;
    @NotBlank(message = "Order date is mandatory")
    private String orderDate;

    private String customerId; // Reference to the associated customer
}
