package org.kainos.ea.cli;

public class Customer {
    private int CustomerId;
    private String Name;
    private String Address;
    private String Phone;

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return Phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        Phone = phoneNumber;
    }

    public Customer(int CustomerId,String name, String address, String phoneNumber) {
        this.CustomerId = CustomerId;
        Name = name;
        Address = address;
        Phone = phoneNumber;
    }
}
