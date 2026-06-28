package generators;

import com.github.javafaker.Faker;
import api.models.User;

public class UserGenerator {
    private static final Faker faker = new Faker();

    public static User randomUser() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .name(faker.name().username())
                .build();
    }

}