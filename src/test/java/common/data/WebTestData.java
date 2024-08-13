package common.data;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class WebTestData {
    Faker faker = new Faker(new Locale("ru"));
    public String newUserName = faker.name().fullName(),
            employeeName = faker.name().fullName(),
            employeeEmail = faker.internet().emailAddress(),
            projectName = faker.funnyName().name(),
            projectUid = UUID.randomUUID().toString(),
            userPhoneNumber = faker.number().digits(10),
            shortUserPhoneNumber = faker.number().digits(3);
}
