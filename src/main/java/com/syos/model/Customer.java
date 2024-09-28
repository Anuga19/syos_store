package com.syos.model;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String address;
    private String customerType;  // ONLINE or IN_STORE

    // Default Constructor
    public Customer() {
    }

    // Constructor with fields
    public Customer(int customerId, String name, String email, String address, String customerType) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.customerType = customerType;
    }

    // Constructor without customerId (for new customers)
    public Customer(String name, String email, String address, String customerType) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.customerType = customerType;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}