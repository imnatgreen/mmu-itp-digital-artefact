package me.nathangreen.digitalartefact;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressTests {
    Address address;

    @BeforeEach
    void setUp() {
        address = new Address("Line 1", "Line 2", "Town", "POST CODE");
    }

    @Test
    void testLine() {
        assertEquals("Line 1, Line 2, Town, POST CODE", address.getAsLine());

        address.setLine2("");
        assertEquals("Line 1, Town, POST CODE", address.getAsLine());
    }

    @Test
    void testBlock() {
        assertEquals("""
                Line 1,
                Line 2,
                Town,
                POST CODE""", address.getAsBlock());

        address.setLine2("");
        assertEquals("""
                Line 1,
                Town,
                POST CODE""", address.getAsBlock());
    }
}
