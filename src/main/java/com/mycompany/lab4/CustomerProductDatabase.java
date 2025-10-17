/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

/**
 *
 * @author Enter Computer
 */
import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author GAMING
 */
public class CustomerProductDatabase {
    
    private String filename ; 
    private ArrayList<CustomerProduct> records ; 
    
    public CustomerProductDatabase (String filename){
        this.filename = filename;
        this.records = new ArrayList<CustomerProduct>() ; 
    }
    
    public void readFromFile(){
try {
        FileReader file = new FileReader(this.filename); // open the file
        BufferedReader read = new BufferedReader(file);  // read line by line
        
        String l;
        
        
        while ((l = read.readLine()) != null) {
            String[] s = l.split(",");
            if (s.length >= 3) {
                String customerSSN = s[0];
                String productID = s[1];
                LocalDate purchaseDate;
try {
    // Try reading dd-MM-yyyy first
    purchaseDate = LocalDate.parse(s[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
} catch (Exception e1) {
    // If it fails, try yyyy-MM-dd
    purchaseDate = LocalDate.parse(s[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
                
                     
                CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
                records.add(cp);
            }
        }
        
        read.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
    
}
    
    public CustomerProduct createRecordFrom(String line){
       String [] s = line.split(",") ;
       String customerSSN = s[0];
       String productID = s[1];
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate purchaseDate = LocalDate.parse(s[2], formatter);
     
       CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate) ; 
       boolean p = Boolean.parseBoolean(s[3]);
       cp.setPaid(p);
       return cp;
        
    }
    
    
    public ArrayList<CustomerProduct> returnAllRecords(){
        return this.records ; 
    }
    
    public boolean contains(String key ){
         String [] s = key.split(",") ; 
        boolean flag = false ; 
        CustomerProduct target = null ;
        for(int i = 0 ; i < records.size() ; i++ ){
            if(s[0].equals(records.get(i).getCustomerSSN()) ){
                
            }else continue;
            
            if(s[1].equals(records.get(i).getProductID()) ){
                
            }else continue ;  
            String date = records.get(i).getPurchaseDate().toString();
            if(s[2].equals(date) ){
                
                flag = true ; 
                
            }
            else continue ;    
        }
        
        return flag ; 
    }
    
    
    public CustomerProduct getRecord(String key){
        String [] s = key.split(",") ; 
        CustomerProduct target = null ;
        for(int i = 0 ; i < records.size() ; i++ ){
            if(s[0].equals(records.get(i).getCustomerSSN()) ){
                
            }else continue;
            
            if(s[1].equals(records.get(i).getProductID()) ){
                
            }else continue ;  
            String date = records.get(i).getPurchaseDate().toString();
            if(s[2].equals(date) ){
                
                target = records.get(i) ; 
                
            }
            else continue ;    
        }
        
        return target ; 
    }
    public void insertRecord(CustomerProduct record){
        this.records.add(record) ; 
    }
    
    public void deleteRecord(String key){
        String [] s = key.split(",") ; 
        
        for(int i = 0 ; i < records.size() ; i++ ){
            if(s[0].equals(records.get(i).getCustomerSSN()) ){
                
            }else continue;
            
            if(s[1].equals(records.get(i).getProductID()) ){
                
            }else continue ;  
            String date = records.get(i).getPurchaseDate().toString();
            if(s[2].equals(date) ){
                
                 records.remove(i) ; 
                
            }
            else continue ;    
        }
        
        
    }
    
    public void saveToFile(){
        try {
      PrintWriter w = new PrintWriter(new FileWriter(this.filename, false));
      for(int i=0 ; i<this.records.size() ; i++){
          w.println(records.get(i).lineRepresentation());  
      } 
       System.out.println("Data saved successfully to " + this.filename);
      w.close();
        }
        catch (IOException ex) {
        ex.printStackTrace();
    }
    }
}