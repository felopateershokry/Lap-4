/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication7;

/**
 *
 * @author GAMING
 */
import java.io.*;

/**
 *
 * @author GAMING
 */
public class EmployeeUser implements NewInterface {
    String employeeId;
    String name;
    String email;
    String address;
    String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String lineRepresentation(){
        String line = String.join(",", employeeId, name, email, address, phoneNumber);
        return line;
    }
    @Override
    public String getSearchKey(){
        return employeeId;
    }
    
    
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        if (email.contains("@")) { // بيشوف انه لازم يكون فيها @
            this.email = email;
        } else {
            System.out.println("Invalid email format.");
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d+")) { // لازم تكون ارقام بس 
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Invalid phone number.");
        }
    }

    
}
