public class MenuItem {
    private String itemCode;
    private String name;  //instance variable - it exists in each object
    private double price;
    public static int numItems = 0;
    
    MenuItem(String code, String nname, double pprice) { //food item
      itemCode = code;
      name = nname;
      price = pprice;
      numItems++;
    }
  
    public double getPrice() {
      return price;
    }
    public String getCode() {
      return itemCode;
    }
    public String getName() {
      return name;
    }
  } //end of class