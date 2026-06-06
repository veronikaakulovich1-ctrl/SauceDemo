package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CustomerInfo {

    private String firstName;
    private String lastName;
    private String zipCode;
}
