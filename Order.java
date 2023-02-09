public class Order {
    public String codes[] = new String[100];
    public String names[] = new String[100];
    public double prices[] = new double[100];

    static int foodIndex=0; //how many are in here
    
    Order() { //just open the object
      
    }
    public void addItem(String code, String name, double price) { //store ordered item info
      codes[foodIndex] = code;
      names[foodIndex] = name;
      prices[foodIndex] = price;
      foodIndex++;
    }
  } //end of class