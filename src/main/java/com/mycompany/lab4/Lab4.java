package com.mycompany.lab4;

import java.util.*;
import java.io.*;

public class Lab4 {
    public static void main(String[] args) throws IOException {
        EmployeeUserDatabase db = new EmployeeUserDatabase("Employees.txt");
        db.readFromFile();

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

                    EmployeeUser emp = new EmployeeUser(empId, empName, empEmail, empAddress, empPhone);
                    db.insertRecord(emp);
                    db.saveToFile();
                    System.out.println("Employee added successfully with ID: " + empId);
                    break;
                }

                case 2: {
                    System.out.print("Enter Employee ID to remove: ");
                    String empId = sc.nextLine();
                    db.deleteRecord(empId);
                    db.saveToFile();
                    break;
                }

                case 3: {
                    System.out.print("Enter Employee ID to get: ");
                    String empId = sc.nextLine();
                    EmployeeUser emp = db.getRecord(empId);
                    if (emp != null) {
                        System.out.println("Employee found: " + emp.lineRepresentation());
                    } else {
                        System.out.println("No employee found with ID " + empId);
                    }
                    break;
                }

                case 4: {
                    System.out.println("\nAll Employees:");
                    for (EmployeeUser emp : db.returnAllRecords()) {
                        System.out.println(emp.lineRepresentation());
                    }
                    break;
                }

                case 0:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid input. Try again!");
                    break;
            }
        }
    }
}
