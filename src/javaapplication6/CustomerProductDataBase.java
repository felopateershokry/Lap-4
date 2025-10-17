/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author GAMING
 */
public class CustomerProductDataBase extends Database<CustomerProduct> {

    public CustomerProductDataBase(String filename) {
        super(filename);
    }


    @Override
    CustomerProduct createRecordFrom(String line) {
       String [] s = line.split(",") ;
       if (s.length < 4) {
        System.out.println("Invalid line format: " + line);
        return null;
    }
       String customerSSN = s[0];
       String productID = s[1];
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       LocalDate purchaseDate = LocalDate.parse(s[2], formatter);
     
       CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate) ; 
       boolean p = Boolean.parseBoolean(s[3]);
       cp.setPaid(p);
       return cp;
    }
    
       
    
}
