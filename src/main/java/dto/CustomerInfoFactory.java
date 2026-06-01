package dto;

import com.github.javafaker.Faker;

public class CustomerInfoFactory {

    public static CustomerInfo getCustomer() {
        Faker faker = new Faker();
        return new CustomerInfo(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );
    }
}
