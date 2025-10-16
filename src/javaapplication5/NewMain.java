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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               CustomerProductDatabase db = new CustomerProductDatabase("CustomersProducts.txt");

        CustomerProduct c1 = new CustomerProduct("12345", "P001", LocalDate.of(2023, 5, 10));
        CustomerProduct c2 = new CustomerProduct("67890", "P002", LocalDate.of(2024, 3, 22));
        c2.setPaid(true);
        c1.setPaid(true);
        System.out.println(c1.getSearchKey());
        System.out.println(c1.lineRepresentation());
    }
    
}
