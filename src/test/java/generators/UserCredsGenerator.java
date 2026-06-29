package generators;

import com.github.javafaker.Faker;

public class UserCredsGenerator {
    private static final Faker faker = new Faker();

    public static String randomName(){
        return faker.name().username();
    }

    public static String randomEmail(){
        return faker.internet().emailAddress();
    }

    public static String randomPassword(int maxSize){
        return faker.internet().password(1, maxSize);
    }

    public static String randomPassword(){
        return faker.internet().password();
    }
}
