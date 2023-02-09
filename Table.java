public class Table {
    private boolean status; //true is occupied
    private int maxSeats;  //instance variable - it exists in each object
    public int numGuests;
    public int tableNumber;
    private boolean orderStatus;
    private boolean served;
    static int numTables = 0;
    static Order tOrder;
    
    Table(int num, int seats) { //make a table
      tableNumber = num;
      maxSeats = seats;
      status = false;
      numTables++;
      orderStatus = false;
      served = false;
      tOrder = new Order();
    }
    public void addItem(String code, String name, double price) { //store order info in order
      tOrder.addItem(code, name, price);
    }
    public void printBill() { //print the bill with neat formatting and total
      System.out.println("Receipt Table# " + tableNumber + " Party " + numGuests);
      double total = 0;
      for(int n=0; n<tOrder.foodIndex; n++) {
        System.out.print(tOrder.codes[n]);
        System.out.printf("%19s", tOrder.names[n]);
        System.out.printf("%7.2f\n", tOrder.prices[n]);
        total += tOrder.prices[n];
      }
      String tot = "Total";
      System.out.printf("%21s", tot);
      System.out.printf("%7.2f\n", total);
    }
    public boolean getStatus() {
      return status;
    }
    public boolean getServed() {
      return served;
    }
    public void setServed(boolean stat) {
      served = stat;
    }
    public void setGuests(int people) {
      numGuests = people;
    }
    public int getMaxSeats() {
      return maxSeats;
    }
    public void setStatus(boolean stat) {
      status = stat;
    }
    public void setOrderStatus(boolean stat) {
      orderStatus = stat;
    }
    public boolean getOrderStatus() {
      return orderStatus;
    }
    public int getTableNumber() {
      return tableNumber;
    }
    public int getNumGuests() {
      return numGuests;
    }
  } //end of class