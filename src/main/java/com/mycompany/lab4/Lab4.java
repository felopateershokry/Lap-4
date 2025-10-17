package com.mycompany.lab4;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lab4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        AdminRole admin = new AdminRole();
        EmployeeRole empRole = new EmployeeRole();

        System.out.println("#######################################");
        System.out.println("#### Welcome to Inventory System ######");
        System.out.println("#######################################");

        while (true) {
            System.out.println("\nSelect Role:");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("0. Exit Program");
            System.out.print("Enter your choice: ");
            int roleChoice = sc.nextInt();
            sc.nextLine();

            if (roleChoice == 0) {
                System.out.println("Exiting program...");
                admin.logout();
                sc.close();
                return;
            }

            switch (roleChoice) {
                // ---------------------- ADMIN ROLE ----------------------
                case 1: {
                    while (true) {
                        System.out.println("\n##### Admin Menu #####");
                        System.out.println("1. Add Employee");
                        System.out.println("2. Remove Employee");
                        System.out.println("3. Show All Employees");
                        System.out.println("0. Back to Main Menu");
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

                                admin.addEmployee(empId, empName, empEmail, empAddress, empPhone);
                                break;
                            }

                            case 2: {
                                System.out.print("Enter Employee ID to remove: ");
                                String empId = sc.nextLine();
                                admin.removeEmployee(empId);
                                break;
                            }

                            case 3: {
                                admin.getListOfEmployees();
                                break;
                            }

                            case 0:
                                admin.logout();
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid input. Try again!");
                                break;
                        }

                        if (input == 0)
                            break;
                    }
                    break;
                }

                // ---------------------- EMPLOYEE ROLE ----------------------
                case 2: {
                    while (true) {
                        System.out.println("\n##### Employee Menu #####");
                        System.out.println("1. Add Product");                       
                        System.out.println("2. Purchase Product");
                        System.out.println("3. Return Product");
                        System.out.println("4. Apply Payment");
                        System.out.println("5. Show All Products");
                        System.out.println("0. Back to Main Menu");
                        System.out.print("Enter your choice: ");

                        int empInput = sc.nextInt();
                        sc.nextLine();

                        switch (empInput) {
                            case 1: {
                             System.out.print("Enter Product ID: ");
                              String pid = sc.nextLine();
                              System.out.print("Enter Product Name: ");
                              String pname = sc.nextLine();
                              System.out.print("Enter Manufacturer Name: ");
                               String manufacturer = sc.nextLine();
                               System.out.print("Enter supplier Name: ");
                               String supplier = sc.nextLine();
                               System.out.print("Enter Price: ");
                                double price = sc.nextDouble();
                               System.out.print("Enter Quantity: ");
                               int quantity = sc.nextInt();
                               sc.nextLine();

                             empRole.addProduct(pid, pname, manufacturer, supplier, quantity,price);
                               break;
                            
                            }
                            case 2: {
                                System.out.print("Enter Customer SSN: ");
                                String ssn = sc.nextLine();
                                System.out.print("Enter Product ID: ");
                                String pid = sc.nextLine();
                                System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                                String dateStr = sc.nextLine();
                                LocalDate pDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                                boolean ok = empRole.purchaseProduct(ssn, pid, pDate);
                                if (ok)
                                    System.out.println("✅ Product purchased successfully.");
                                else
                                    System.out.println("❌ Purchase failed.");
                                break;
                            }

                            case 3: {
                                System.out.print("Enter Customer SSN: ");
                                String ssn = sc.nextLine();
                                System.out.print("Enter Product ID: ");
                                String pid = sc.nextLine();
                                System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                                String pDateStr = sc.nextLine();
                                System.out.print("Enter Return Date (dd-MM-yyyy): ");
                                String rDateStr = sc.nextLine();
                                LocalDate purchaseDate = LocalDate.parse(pDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                LocalDate returnDate = LocalDate.parse(rDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                                double refund = empRole.returnProduct(ssn, pid, purchaseDate, returnDate);
                                if (refund > 0)
                                    System.out.println("✅ Product returned successfully. Refund = " + refund);
                                else
                                    System.out.println("❌ Return failed.");
                                break;
                            }

                            case 4: {
                                System.out.print("Enter Customer SSN: ");
                                String ssn = sc.nextLine();
                                System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                                String dateStr = sc.nextLine();
                                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                                boolean paid = empRole.applyPayment(ssn, date);
                                if (paid)
                                    System.out.println("✅ Payment applied successfully.");
                                else
                                    System.out.println("❌ Payment failed.");
                                break;
                            }

                            case 5: {
                                empRole.getListOfProducts();
                                break;
                            }

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid input. Try again!");
                                break;
                        }

                        if (empInput == 0)
                            break;
                    }
                    break;
                }

                default:
                    System.out.println("Invalid role. Try again!");
                    break;
            }
        }
    }
}
