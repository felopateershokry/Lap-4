/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Lenovo
 */
public class EmployeeUser {
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
    
    public String lineRepresentation(){
        String line = String.join(",", employeeId, name, email, address, phoneNumber);
        return line;
    }
    public String getSearchKey(){
        return employeeId;
    }
    
    public void saveToFile() {
        String fileName = "Employees.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lineRepresentation());
            writer.newLine(); // move to next line
            System.out.println("Employee " + employeeId + " saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving employee:");
        }
    }
}