/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.util.*;
import java.io.*;

/**
 *
 * @author Lenovo
 */
public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String filename;

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    
    public void readFromFile(){
        records.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                EmployeeUser emp = createRecordForm(line);
                insertRecord(emp);
            }
        } catch (IOException e) {
            System.out.println("️Error reading file ");
        }
    }
    public ArrayList<EmployeeUser> returnAllRecords(){
        return records;
    }
    public EmployeeUser createRecordForm(String line){
        String[] parts = line.split(",");
        if(parts.length == 5){
            return new EmployeeUser(parts[0].trim(),parts[1].trim(),parts[2].trim(),parts[3].trim(),parts[4].trim());
        }
        return null;
    }
    
    public boolean contains(String key){
        for(EmployeeUser emp : records){
            if(emp.getSearchKey().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }
    
    public EmployeeUser getRecord(String key){
        for(EmployeeUser emp : records){
            if(emp.getSearchKey().equalsIgnoreCase(key)){
                return emp;
            }
        }
        return null;
    }
    
    public void insertRecord(EmployeeUser record){
        if(record != null){
            records.add(record);
        }
    }
    public void deleteRecord(String key){
        records.removeIf(emp -> emp.getSearchKey().equalsIgnoreCase(key));
    }
    
    public void saveToFile() {
    try (FileWriter fw = new FileWriter(filename, false)) {
        for (EmployeeUser emp : records) {
            fw.write(emp.lineRepresentation() + "\n");
        }
        System.out.println("All employees successfully saved to the file ✅");
    } catch (IOException e) {
        System.out.println("⚠️ Error saving employees to file: " + e.getMessage());
    }
}

}
