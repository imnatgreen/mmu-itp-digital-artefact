package me.nathangreen.digitalartefact;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;

import java.util.UUID;

public class Customer {
    private final String Id;

    private PhoneNumber phoneNumber;
    private String firstName;
    private String lastName;
    private Address address;

    public Customer(String Id, String phoneNumberStr, String firstName, String lastName, Address address) {
        this.Id = Id;
        this.setPhoneNumber(phoneNumberStr);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
    }

    public Customer(String phoneNumberStr, String firstName, String lastName, Address address) {
        this(UUID.randomUUID().toString(), phoneNumberStr, firstName, lastName, address);
    }

    public String getId() {
        return Id;
    }

    public void setPhoneNumber(String phoneNumberStr) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            this.phoneNumber = phoneUtil.parse(phoneNumberStr, "GB");
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
    }

    public String getPhoneNumberFormatted() {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        return phoneUtil.format(this.phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
