package com.mycompany.lab4;

import java.util.*;
import java.io.*;

public class Lab4 {
    public static void main(String[] args) {
//        EmployeeUserDatabase db = new EmployeeUserDatabase("Employees.txt");
//       db.readFromFile();
         AdminRole adm= new AdminRole();

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while (true) {
            System.out.println("\n##### Main Menu #####");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Get Employee");
            System.out.println("4. Show All Employees");
            System.out.println("0. Exit Program");
            System.out.print("Enter your choice: ");

            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1: {
                    String empId = "E" + rand.nextInt(1000000);
                    System.out.print("Enter the name: ");
                    String empName = sc.nextLine();
                    System.out.print("Enter the email: ");
                    String empEmail = sc.nextLine();
                    System.out.print("Enter the address: ");
                    String empAddress = sc.nextLine();
                    System.out.print("Enter the phone number: ");
                    String empPhone = sc.nextLine();

//                    EmployeeUser emp = new EmployeeUser(empId, empName, empEmail, empAddress, empPhone);
//                    db.insertRecord(emp);
//                    db.saveToFile();
                    adm.addEmployee(empId, empName, empEmail, empAddress, empPhone);
                    System.out.println("exiting program");
                    adm.logout();
                    break;
                }

                case 2: {
                    System.out.print("Enter Employee ID to remove: ");
                    String empId = sc.nextLine();
                    adm.removeEmployee(empId);                    
                    break;
                }

                case 4: {
                   adm.getListOfEmployees();
                    break;
                }

                case 0:
                    System.out.println("Exiting program...");
                    adm.logout();
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid input. Try again!");
                    break;
            }
        }
    }
}
