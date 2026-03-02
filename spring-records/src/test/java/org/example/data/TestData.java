package org.example.data;

import org.example.dto.UserRecord;
import org.example.dto.UserAddress;
import net.datafaker.providers.base.Address;
import net.datafaker.Faker;

import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class TestData {

    private static Random random = new Random();
    private static Faker faker = new Faker(Locale.US, random);
    
    public static UserRecord randomUser() {

        return new UserRecord(
            faker.name().name(),
            faker.internet().emailAddress(),
            Set.of(randomAddressRecord(), randomAddressRecord()));
    }
    
    public static UserAddress randomAddressRecord() {

        Address fakerAddress = faker.address();
        return new UserAddress(fakerAddress.streetAddress(),
            fakerAddress.city(),
            fakerAddress.state(),
            fakerAddress.zipCode());
    }
}
