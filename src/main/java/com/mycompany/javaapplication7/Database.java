/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication7;

/**
 *
 * @author GAMING
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author GAMING
 */
abstract class Database <T extends NewInterface >   //Generics + interface
{
    
   protected String filename ; 
   protected ArrayList<T> records ; 
   
   public Database(String filename) {
    this.filename = filename;
    this.records = new ArrayList<T>();
}
   
   
    abstract T createRecordFrom(String line) ;
    public ArrayList<T> returnAllRecords(){
        return this.records ; 
    }
    
    
     
   

     
      public T getRecord(String key){
        for(T emp : records){
            if(emp.getSearchKey().equalsIgnoreCase(key)){
                return emp;
            }
        }
        return null;
      
      } 
    
     public void insertRecord(T record){
         if(record != null){
            records.add(record);
        }
     } 
    
    
     public void deleteRecord(String key){
        records.removeIf(emp -> emp.getSearchKey().equalsIgnoreCase(key));
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
 
 void readFromFile() {
      

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                T emp = this.createRecordFrom(line);
                insertRecord(emp);
            }
        } catch (IOException e) {
            System.out.println("️Error reading file ");
        }
    }
    
     public boolean contains(String key ){
            for(T emp : records){
           if(emp.getSearchKey().equalsIgnoreCase(key)){
                return true;
            }  
         }
          return false ; 
    }
    
}

