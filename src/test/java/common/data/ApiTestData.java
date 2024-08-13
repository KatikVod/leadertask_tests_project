package common.data;

import com.github.javafaker.Faker;

import java.util.UUID;

public class ApiTestData {
    final Faker faker = new Faker();
    public final String wrongEmail = faker.internet().emailAddress(),
            wrongPassword = faker.random().hex(5),
            projectName = faker.company().name(),
            projectUid = UUID.randomUUID().toString();
}
