package main.java.OrganizationObject;

public class Address {

    private String zipCode; //Длина строки не должна быть больше 19, поле может быть null
    public Address(String zipCode) {
        this.zipCode = zipCode;
    }
    public boolean isValidZipCode() {
        return zipCode == null || zipCode.length() <= 19;
    }
    @Override
    public String toString() {
        return zipCode;
    }
    }
