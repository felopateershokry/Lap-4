/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication7;

/**
 *
 * @author GAMING
 */
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Enter Computer
 */
public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDataBase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDataBase("customers.txt");

        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
        System.out.println("Loaded products: " + productsDatabase.returnAllRecords().size());
        System.out.println("Loaded purchases: " + customerProductDatabase.returnAllRecords().size());
    }
    private boolean testProductID(String productID){
    if(productID == null)
       return false;
      else
       return true;
    }
    
    public void addProduct(String productID, String productName,String manufacturerName, String supplierName, int quantity, double price) {

        if(!testProductID(productID))
       {
            System.out.println("----------invalid product id----------");
            return;
        }

        if (productName == null || productName.isEmpty()) {
            System.out.println("----------invalid product name----------");
            return;
        }

        if (supplierName == null || supplierName.isEmpty()) {
            System.out.println("----------invalid supplier name----------");
            return;
        }

        if (quantity <= 0) {
            System.out.println("----------invalid quantity----------");
            return;
        }

        if (price <= 0) {
            System.out.println("----------invalid price----------");
            return;
        }

        if (productsDatabase.contains(productID)) {
            System.out.println("Product already exists with ID: " + productID);
            return;
        }

        Product p =  new Product(productID, productName,manufacturerName, supplierName, quantity, (float) price);
        productsDatabase.insertRecord(p);
//        productsDatabase.saveToFile();

        System.out.println("Product added successfully with ID: " + productID);
    }

  
    public Product[] getListOfProducts() {
        ArrayList<Product> all = productsDatabase.returnAllRecords();

        if (all.isEmpty()) {
            System.out.println(" No products found.");
            return new Product[0];
        }

        Product[] productArray = new Product[all.size()];
        all.toArray(productArray);

        System.out.println("All Products:");
        for (Product p : productArray) {
            System.out.println(p.lineRepresentation());
        }

        return productArray;
    }
    
    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> all = customerProductDatabase.returnAllRecords();

        if (all.isEmpty()) {
            System.out.println(" No purchasing operations found.");
            return new CustomerProduct[0];
        }

        CustomerProduct[] operationsArray = new CustomerProduct[all.size()];
        all.toArray(operationsArray);

        System.out.println("All Purchasing Operations:");
        for (CustomerProduct cp : operationsArray) {
            System.out.println(cp.lineRepresentation());
        }

        return operationsArray;
    }
    
     private boolean testSSN(String customerSSN){
   if(customerSSN== null)
       return false;
   for(char d:customerSSN.toCharArray()){
   if(!Character.isDigit(d))
       return false;
   }
   if(customerSSN.length()!=14)
       return false;
   return true;
   }
     
     
    
     public boolean purchaseProduct(String customerSSN,String productID,LocalDate purchaseDate){
    if(!testSSN(customerSSN)){
        System.out.println("invalid customer social security number");
        return false;
    }
    if(!testProductID(productID))
       {
            System.out.println("----------invalid product id----------");
            return false;
        }
    
      if (!productsDatabase.contains(productID)) {
        System.out.println("Product not found with ID: " + productID);
        return false;
    }
   
  
    
    Product pro = productsDatabase.getRecord(productID);
    if(pro.getQuantity()==0){
        System.out.println("product out os stock");
        return false;
    }
    
    pro.setQuantity(pro.getQuantity()-1);
    CustomerProduct pur= new CustomerProduct(customerSSN,productID,purchaseDate);
    customerProductDatabase.insertRecord(pur);
//    customerProductDatabase.saveToFile();
//    productsDatabase.saveToFile();
    
    System.out.println("Purchase recorded successfully for customer: " + customerSSN);
    return true;

    }
     
    
    
     public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate){
     if(!testSSN(customerSSN)){
        System.out.println("invalid customer social security number");
        return -1 ;
    }
    if(!testProductID(productID))
       {
            System.out.println("----------invalid product id----------");
            return -1;
        }
    if (!productsDatabase.contains(productID)) {
        System.out.println("Product not found with ID: " + productID);
        return -1;
    }
    if(returnDate.isBefore(purchaseDate)){
        System.out.println("return date cannot be before purchase date");
        return -1;
    }
    String key=customerSSN + "," + productID + "," + purchaseDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    if(!customerProductDatabase.contains(key))
    {
        System.out.println("purchase record not found");
        return-1;
    }
    java.time.Period diff= java.time.Period.between(purchaseDate, returnDate);
    if(diff.getDays()>14){
        System.out.println("sorry the product was purchased"+diff.getDays()+"days ago and can't be retured");
        return -1;
    }
    Product pro=productsDatabase.getRecord(productID);
    pro.setQuantity(pro.getQuantity()+1);
    customerProductDatabase.deleteRecord(key);
//    productsDatabase.saveToFile();
//    customerProductDatabase.saveToFile();
    System.out.println(" Product return successful. Price refunded: " + pro.getPrice());
    return pro.getPrice();
    }
   
  
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate){
    if(!testSSN(customerSSN)){
        System.out.println("invalid customer social security number");
        return false ;
    }
    
    if(purchaseDate==null)
    return false;
    boolean found=false;
    for(CustomerProduct p :customerProductDatabase.returnAllRecords()){
    if(p.getCustomerSSN().equals(customerSSN)&&p.getPurchaseDate().equals(purchaseDate)){
    found=true;
    p.setPaid(found);
    break;
    }
    }
    if(!found){
        System.out.println("no record found for payment");
        return false;
    }
//    customerProductDatabase.saveToFile();
    System.out.println("Payment successfully applied for customer " + customerSSN + ".");
    return true;
    }
    
      public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        System.out.println("Logged out successfully. All data saved.");
    }
}