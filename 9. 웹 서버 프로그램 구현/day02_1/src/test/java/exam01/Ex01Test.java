package exam01;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.Locale;

public class Ex01Test {
    @Test
    void test1() {
        // Locale.KOREAN
        Faker faker = new Faker(new Locale("ko_kr"));
        String name = faker.name().fullName();
        System.out.println(name);
        // 호출할때마다 새로운 무작위 데이터가 만들어짐
    }

    @Test
    void test2() {
        Faker faker = new Faker(Locale.KOREAN);
        String address = faker.address().zipCode() + " " + faker.address().cityName() + faker.address().cityName() + faker.address().streetAddress();
        String addressSub = faker.address().secondaryAddress();
        System.out.println(address + " " + addressSub);
    }
}
