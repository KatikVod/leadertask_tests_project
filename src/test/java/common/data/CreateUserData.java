package common.data;

import com.github.javafaker.Faker;

public class CreateUserData {
    Faker faker = new Faker();
    public String userName = faker.name().fullName(),
            userPassword = faker.internet().password(),
            userEmail = faker.internet().emailAddress(),
            userPhoneNumber = "+7" + faker.number().digits(10);
}
