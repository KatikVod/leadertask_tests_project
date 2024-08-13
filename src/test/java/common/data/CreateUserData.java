package common.data;

import com.github.javafaker.Faker;

public class CreateUserData {
    final Faker faker = new Faker();
    public final String userName = faker.name().fullName();
    public final String userPassword = faker.internet().password();
    public final String userEmail = faker.internet().emailAddress();
    public final String userPhoneNumber = "+7" + faker.number().digits(10);
}
