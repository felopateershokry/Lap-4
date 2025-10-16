/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication5;

import java.time.LocalDate;

/**
 *
 * @author GAMING
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       CustomerProductDatabase db = new CustomerProductDatabase("customers.txt");

        CustomerProduct c1 = new CustomerProduct("12345", "P001", LocalDate.of(2023, 5, 10));
        CustomerProduct c2 = new CustomerProduct("67890", "P002", LocalDate.of(2024, 3, 22));
        c2.setPaid(true);

        db.insertRecord(c1);
        db.insertRecord(c2);

        db.saveToFile();

        System.out.println("\n📄 Data from file:");
        db.readFromFile();
        for (CustomerProduct cp : db.returnAllRecords()) {
            System.out.println(cp.lineRepresentation());
        }
    
    }
  }
