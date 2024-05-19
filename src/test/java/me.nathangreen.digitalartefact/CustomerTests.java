package me.nathangreen.digitalartefact;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTests {
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("07123 123123", "Test", "Person", new Address("Line 1", "Line 2", "Town", "POST CODE"));
    }

    @Test
    void testPhoneNumber() {
        assertEquals("+44 7123 123123", customer.getPhoneNumberFormatted());

        customer.setPhoneNumber("7333123322");
        assertEquals("+44 7333 123322", customer.getPhoneNumberFormatted());
    }

    @Test
    void testName() {
        assertEquals("Test", customer.getFirstName());
        assertEquals("Person", customer.getLastName());
        assertEquals("Test Person", customer.getName());
    }
}
