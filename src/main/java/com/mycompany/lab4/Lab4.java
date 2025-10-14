/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab4;

/**
 *
 * @author Lenovo
 */
public class Lab4 {

    public static void main(String[] args) {
        EmployeeUser emp = new EmployeeUser(
            "E001",
            "John Doe",
            "john@example.com",
            "123 Main St",
            "555-1234"
        );
        emp.saveToFile();
    }
}
