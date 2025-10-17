/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

import java.time.LocalDate;

/**
 *
 * @author GAMING
 */
public class CustomerProduct implements NewInterface {
  private  String  customerSSN ,  productID ; 
  private LocalDate purchaseDate ;
  private boolean paid ;
 
  
  public CustomerProduct( String customerSSn , String productid ,LocalDate purchaseDate ){
      this.customerSSN = customerSSn;
      this.paid = false ; 
      this.productID = productid  ;
      this.purchaseDate = purchaseDate ;
  }
  
   public String getCustomerSSN(){
   return this.customerSSN ; 
   }
    public String getProductID(){
        return this.productID ; 
    }
  @Override
   public String lineRepresentation() {
       String s = "" ;
       int d = this.purchaseDate.getDayOfMonth() ;
      int m = this.purchaseDate.getMonthValue() ;
      int y = this.purchaseDate.getYear() ;
      String date = String.format("%02d-%02d-%d",d,m,y ) ;
       s = this.customerSSN + "," +this.productID + "," + date+ "," + String.valueOf(paid) ;  
       return s ; 
   }
   
   public boolean isPaid(){
       return this.paid ; 
   }
    public LocalDate getPurchaseDate(){
        return this.purchaseDate ;
    }

   
   public void setPaid(boolean paid){
       this.paid = paid ; 
   }
   
  @Override
   public String getSearchKey(){
      String s = "" ;
      int d = this.purchaseDate.getDayOfMonth() ;
      int m = this.purchaseDate.getMonthValue() ;
      int y = this.purchaseDate.getYear() ;
      String date = String.format("%02d-%02d-%d",d,m,y ) ;
      s = this.customerSSN + "," +this.productID + "," + date  ;
      return s ; 
   }
public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    
}
