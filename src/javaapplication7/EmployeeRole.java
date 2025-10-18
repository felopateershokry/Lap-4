/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

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
        productsDatabase.saveToFile();

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
     
     
     public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        // 1. get product by ID
        Product product = productsDatabase.getRecord(productID);

        // 2. if product not found or quantity = 0 => return false
        if (product == null) {
            System.out.println("❌ Product not found with ID: " + productID);
            return false;
        }

        if (product.getQuantity() <= 0) {
            System.out.println("❌ Product out of stock: " + productID);
            return false;
        }

        // 3. decrease product quantity by 1
        product.setQuantity(product.getQuantity() - 1);

        // 4. create new CustomerProduct record
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);

        // 5. add to customerProductDatabase
        customerProductDatabase.insertRecord(cp);

        // 6. save both files
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        System.out.println("✅ Purchase recorded successfully.");
        return true;
    }
     
     
     
     
     
     public double returnProduct(String customerSSN, String productID,
                            LocalDate purchaseDate, LocalDate returnDate) {

    // 1️⃣ لو تاريخ الإرجاع قبل الشراء → خطأ
    if (returnDate.isBefore(purchaseDate)) {
        System.out.println("❌ Return date cannot be before purchase date.");
        return -1;
    }

    // 2️⃣ نجيب المنتج
    Product product = productsDatabase.getRecord(productID);
    if (product == null) {
        System.out.println("❌ Product not found in Products.txt.");
        return -1;
    }

    // 3️⃣ نكوّن المفتاح للبحث في CustomerProductDatabase
    String formattedDate = String.format("%02d-%02d-%d",
            purchaseDate.getDayOfMonth(),
            purchaseDate.getMonthValue(),
            purchaseDate.getYear());

    String key = customerSSN + "," + productID + "," + formattedDate;

    // 4️⃣ نتحقق إن السجل موجود
    CustomerProduct record = customerProductDatabase.getRecord(key);
    if (record == null) {
        System.out.println("❌ Purchase record not found in CustomersProducts.txt.");
        return -1;
    }

    // 5️⃣ نتحقق من مرور أكتر من 14 يوم
    if (returnDate.isAfter(purchaseDate.plusDays(14))) {
        System.out.println("❌ Cannot return after 14 days of purchase.");
        return -1;
    }

    // 6️⃣ نزود الكمية
    product.setQuantity(product.getQuantity() + 1);

    // 7️⃣ نحذف العملية من ملف العملاء
    customerProductDatabase.deleteRecord(key);

    // 8️⃣ نحفظ الملفات بعد التعديل
    productsDatabase.saveToFile();
    customerProductDatabase.saveToFile();

    System.out.println("✅ Product returned successfully.");
    return product.getPrice();
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
    customerProductDatabase.saveToFile();
    System.out.println("Payment successfully applied for customer " + customerSSN + ".");
    return true;
    }
}