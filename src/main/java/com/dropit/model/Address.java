package com.dropit.model;

import java.util.Objects;

public class Address {
    private String street;
    private String line1;
    private String line2;
    private String country;
    private String postcode;

    public Address() {}
    public Address(String street, String line1, String line2, String country, String postcode) {
        this.street = street;
        this.line1 = line1;
        this.line2 = line2;
        this.country = country;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && line1.equals(address.line1) && line2.equals(address.line2) && country.equals(address.country) && postcode.equals(address.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, line1, line2, country, postcode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
