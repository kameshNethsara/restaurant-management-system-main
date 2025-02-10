package com.ijse.rms.entity;

import java.sql.Date;

public class Employee {

    private String id;
    private String name;
    private String position;
    private String phone;
    private String email;
    private Date hireDate; // keep as java.sql.Date for database compatibility

    // Updated constructor
    public Employee(String id, String name, String position, String email, String phone, Date hireDate) {
        this.id = id; // Initialize id
        this.name = name; // Initialize name
        this.position = position; // Initialize position
        this.email = email; // Initialize email
        this.phone = phone; // Initialize phone
        this.hireDate = hireDate; // Initialize hireDate
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}
