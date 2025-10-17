/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication6;

import java.time.LocalDate;

/**
 *
 * @author GAMING
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // P2394,Laptop,Apple,TechSupplier,10,1500
         
         
     for(int i =0 ; i<10 ; i++){
    int y =9 ;
    
     }
         ProductDatabase em = new ProductDatabase("products.txt")  ;
       Product x = new Product("P2394","Laptop","Apple","TechSupplier",10,1500) ; 
       //Product y = new Product("Xopijadf","Laptop","Awwqedcasle","Tech",300,12) ; 
       //Product z = new Product("fasljk;dfh","Laptop","Apw","upplier",1,159038) ; 
       // em.insertRecord(x);
       // em.insertRecord(y);
       // em.insertRecord(z);
       // em.saveToFile();
       em.readFromFile();
        for(Product cp : em.returnAllRecords() ){
            System.out.println(cp.lineRepresentation());
           System.out.println(cp.getSearchKey());
        }
        
        em.deleteRecord("P2394");
        System.out.println("------------------------------------------");
          for(Product cp : em.returnAllRecords() ){
            System.out.println(cp.lineRepresentation());
           System.out.println(cp.getSearchKey());
        }
          
        em.saveToFile();
        boolean a = em.contains("P2394") ;
        System.out.println(a);
        em.insertRecord(x);
        a = em.contains("P2394") ;
        System.out.println(a);
        
        
        
        
        
        
      
        
    }
    
}
