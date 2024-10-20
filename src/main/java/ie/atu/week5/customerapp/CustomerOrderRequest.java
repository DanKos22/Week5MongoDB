package ie.atu.week5.customerapp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequest {

    @NotNull(message = "Customer cannot be blank")
    @Valid
    private Customer customer;
    @NotEmpty(message = "List cannot have any empty sections")
    @Valid
    private List<Order> orders;

}
