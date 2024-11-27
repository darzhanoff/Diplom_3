package site.stellarburger.user;

import com.github.javafaker.Faker;

public class UserRandomizer {
    private static final Faker faker = new Faker();

    public static User randomUser() {
        return new User(
                faker.internet().emailAddress(),
                faker.internet().password(8, 12),
                faker.name().fullName()
        );
    }
}
