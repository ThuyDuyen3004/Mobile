package Models;

public class CartItem {
   private String productName;
   private double price;
   private String promotion;

   public CartItem(String productName, double price, String promotion) {
      this.productName = productName;
      this.price = price;
      this.promotion = promotion;
   }

   public String getProductName() {
      return productName;
   }

   public double getPrice() {
      return price;
   }

   public String getPromotion() {
      return promotion;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof CartItem)) return false;

      CartItem other = (CartItem) obj;
      return productName.equals(other.productName)
              && price == other.price
              && promotion.equals(other.promotion);
   }

   @Override
   public int hashCode() {
      return productName.hashCode() * 31 + Double.hashCode(price) + promotion.hashCode();
   }
}