package data;

import com.github.javafaker.Faker;

public class MobileTestData {
    Faker faker = new Faker();
    public String unknownEmail = faker.internet().emailAddress();
}
