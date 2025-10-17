/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Enter Computer
 */
public class ProductDatabase {

    private String filename;
    private ArrayList<Product> records;

    public ProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<Product>();
    }

    public void readFromFile() {
        try {
            FileReader file = new FileReader(this.filename);
            BufferedReader read = new BufferedReader(file);

            String l;
            while ((l = read.readLine()) != null) {
                String[] s = l.split(",");
                if (s.length >= 6) {
                    String productID = s[0];
                    String productName = s[1];
                    String supplierName = s[2];
                    String manufacturerName = s[3];
                    int quantity = Integer.parseInt(s[4]);
                    double price = Double.parseDouble(s[5]);

                    Product p = new Product(productID, productName, supplierName, manufacturerName, quantity, (float) price);
                    records.add(p);
                }
            }

            read.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Product createRecordFrom(String line) {
        String[] s = line.split(",");
        String productID = s[0];
        String productName = s[1];
        String supplierName = s[2];
        String manufacturerName = s[3];
        int quantity = Integer.parseInt(s[4]);
        double price = Double.parseDouble(s[5]);

        Product p = new Product(productID, productName, supplierName, manufacturerName, quantity, (float) price);
        return p;
    }

    public ArrayList<Product> returnAllRecords() {
        return this.records;
    }

    public boolean contains(String key) {
        boolean flag = false;
        for (int i = 0; i < records.size(); i++) {
            if (key.equals(records.get(i).getProductId())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public Product getRecord(String key) {
        Product target = null;
        for (int i = 0; i < records.size(); i++) {
            if (key.equals(records.get(i).getProductId())) {
                target = records.get(i);
                break;
            }
        }
        return target;
    }

    public void insertRecord(Product record) {
        this.records.add(record);
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (key.equals(records.get(i).getProductId())) {
                records.remove(i);
                break;
            }
        }
    }

    public void saveToFile() {
        try {
            PrintWriter w = new PrintWriter(new FileWriter(this.filename, false));
            for (int i = 0; i < this.records.size(); i++) {
                w.println(records.get(i).lineRepresentation());
            }
            System.out.println("Data saved successfully to " + this.filename);
            w.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
