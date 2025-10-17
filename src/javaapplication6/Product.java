/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author GAMING
 */
public class Product implements NewInterface{
    
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;

    private int quantity;

    private float price;
    
     public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }



    public void setQuantity(int quantity) {
        if(this.quantity <0){
            this.quantity = 0 ;
        }
        else 
        this.quantity = quantity;
    }

    @Override
    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + ","
                + supplierName + "," + quantity + "," + price;
    }

    @Override
    public String getSearchKey() {
        return productID;
    }
    
    /*
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;

    private int quantity;

    private float price;
    */
    public String getId() {
    return this.productID;
}

public String getName() {
    return this.productName;
}

public double getPrice() {
    return this.price;
}

public int getQuantity() {
        return quantity;
    }

// Setters
public void setId(String id) {
    this.productID= id;
}

public void setName(String name) {
    this.productName = name;
}

public void setPrice(float price) {
    if (price >= 0) {
        this.price = price;
    } else {
        System.out.println("Price cannot be negative.");
    }
}
    
    
}
