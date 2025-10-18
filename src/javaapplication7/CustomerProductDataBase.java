/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author GAMING
 */


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
    
       public boolean contains(String key ){
        if (key == null) return false;
    String[] s = key.split(",");
    if (s.length < 3) return false;

    String targetSSN = s[0].trim();
    String targetProductID = s[1].trim();
    String targetDateStr = s[2].trim(); // expecting dd-MM-yyyy

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    for (CustomerProduct cp : records) {
        if (!targetSSN.equals(cp.getCustomerSSN())) continue;
        if (!targetProductID.equals(cp.getProductID())) continue;
        String cpDateStr = cp.getPurchaseDate().format(formatter); // format the stored LocalDate the same way
        if (targetDateStr.equals(cpDateStr)) {
            return true;
        }
    }
    return false;
    }

    
}
