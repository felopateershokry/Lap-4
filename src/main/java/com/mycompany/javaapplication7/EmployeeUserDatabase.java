/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication7;

/**
 *
 * @author GAMING
 */
public class EmployeeUserDatabase extends Database<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if(parts.length == 5){
            return new EmployeeUser(parts[0].trim(),parts[1].trim(),parts[2].trim(),parts[3].trim(),parts[4].trim());
        }
        return null;
    }
    
    
}
