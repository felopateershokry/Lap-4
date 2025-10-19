/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication7;

/**
 *
 * @author GAMING
 */
public class ProductDatabase extends Database<Product> {

    public ProductDatabase(String filename) {
        super(filename); // parent constructor 
    }



    @Override
    Product createRecordFrom(String line) {
        String[] s = line.split(",") ;
        if (s.length < 6) {
        System.out.println("Invalid line format: " + line);
        return null;
    }
        
        String productID=s[0];
        String supplierName = s[3] ;
        String manufacturerName = s[2];
        String productName = s[1];
        
        int quantity = Integer.parseInt(s[4]);
        float price = Float.parseFloat(s[5]);
        
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity,  price ) ;
        return p ; 
       
        
    }
    
   
    
    
    
}
