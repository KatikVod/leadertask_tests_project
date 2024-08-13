package common.data;

import com.github.javafaker.Faker;

public class MobileTestData {
    final Faker faker = new Faker();
    public final String unknownEmail = faker.internet().emailAddress();
}
