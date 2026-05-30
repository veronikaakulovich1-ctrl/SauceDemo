package dto;

import com.github.javafaker.Faker;

public final class CustomerInfoFactory {

    private CustomerInfoFactory() {
    }

    public static CustomerInfo defaultCustomer() {
        Faker faker = new Faker();
        return new CustomerInfo(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );
    }
}
