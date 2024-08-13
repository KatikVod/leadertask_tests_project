package common.data;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class WebTestData {
    final Faker faker = new Faker(new Locale("ru"));
    public final String newUserName = faker.name().fullName();
    public final String employeeName = faker.name().fullName();
    public final String employeeEmail = faker.internet().emailAddress();
    public final String projectName = faker.funnyName().name();
    public final String projectUid = UUID.randomUUID().toString();
    public final String userPhoneNumber = faker.number().digits(10);
    public final String shortUserPhoneNumber = faker.number().digits(3);
}
