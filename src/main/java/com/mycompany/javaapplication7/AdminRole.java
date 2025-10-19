/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication7;

/**
 *
 * @author GAMING
 */
import java.util.*; 
import java.util.regex.*;

public class AdminRole {
    
  private EmployeeUserDatabase database ;
  
  public AdminRole(){
  database = new EmployeeUserDatabase("Employees.txt");
  database.readFromFile();
  }
  
  private boolean testEmail(String email){
  String emailRegex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
  Pattern pattern= Pattern.compile(emailRegex);
  if(email==null){
  return false;
  }
  return pattern.matcher(email).matches();
  }
  
   private boolean testPhone(String phoneNumber){
   if(phoneNumber== null)
       return false;
   for(char d:phoneNumber.toCharArray()){
   if(!Character.isDigit(d))
       return false;
   }
   if(phoneNumber.length()!=11)
       return false;
   return true;
   }
  
  public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber){
      
  if(employeeId==null || employeeId.isEmpty()){
      System.out.println("----------invalid id----------");
      return;
  }
  
  if(name==null || name.isEmpty()){
      System.out.println("---------invalid name---------");
      return;
  }
  if(!testEmail(email)){
      System.out.println("---------invalid email--------");
      return;      
  }
  if(address==null || address.isEmpty()){
      System.out.println("--------invalid address--------");  
      return;      
  }
  if(!testPhone(phoneNumber)){
      System.out.println("-------invalid phonenumber------");
      return;      
  }
  EmployeeUser emp = new EmployeeUser(employeeId, name, email, address, phoneNumber);
  database.insertRecord(emp);
//  database.saveToFile();
  System.out.println("Employee added successfully with ID: " + employeeId);
    
  
  }
  
  public void removeEmployee(String key){
  if(key==null||key.isEmpty()){
      System.out.println("invalid id");
      return;
  }
  if(!database.contains(key)){
      System.out.println("id not found");
      return;
  }
  database.deleteRecord(key);
//  database.saveToFile();
  System.out.println("Employee with ID " + key + " removed successfully.");

  }
  public EmployeeUser[] getListOfEmployees() {
  ArrayList<EmployeeUser> all = database.returnAllRecords();
  EmployeeUser[] employeeArray = new EmployeeUser[all.size()];
  all.toArray(employeeArray);
      System.out.println("All employees");
      for (EmployeeUser emp : employeeArray) {
        System.out.println(emp.lineRepresentation());
    }
    return employeeArray;
  }
  
  public void logout() {
    database.saveToFile();
    System.out.println("Logged out successfully. All data saved.");
}
}
