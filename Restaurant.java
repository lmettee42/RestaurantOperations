import java.util.*;
import java.io.*;

public class Restaurant {
    static Table tables[] = new Table [100];
    static int numTables = 0;
    static Menu menu;
    public static void main(String[] args) throws FileNotFoundException { //main
      //setup
      //String pathname = "config.txt";
      String pathname = "C:/Users/17178/OneDrive - The University of Texas at Dallas/Schoolwork/CS 2336/RestaurantOperations/config.txt";
      File config = new File(pathname);
      Scanner configInput = new Scanner(config);
      int menuIndex = 0; //for line number of blank line

      while (configInput.hasNextLine()) { //while there are tokens to read

        if(menuIndex == 0) {
          String f = " ";
          f = configInput.nextLine();
        }
        
        if(menuIndex != 0 && menuIndex<20) { //not first, not last, setup tables in restaurant
          int num = configInput.nextInt();
          int seats = configInput.nextInt();
          tables[menuIndex] = new Table(num, seats);
        }
        if(menuIndex == 20) //done with tables
          break;
        menuIndex++;
      }
      configInput.close(); //shut file
      menu = new Menu(pathname); //open the menu and setup there

  Scanner input = new Scanner(System.in); //Scanner for active input
  //loop begin
      while(true) { //active restaurant loop
        
        if(input.hasNextInt()) { //carry on
          int tableNumber = input.nextInt();
          String varInputs = input.next();
          //find what was input
          if(varInputs.charAt(0) == 'P') { //Party assign
            //get # in party
            String spNum = varInputs.substring(1);
            
            int pNum = Integer.parseInt(spNum);
            if(tables[findTableIndex(tableNumber)].getStatus() == true) //occupied
              System.out.println("Table " + tableNumber + " already occupied!");
            else if (pNum > tables[findTableIndex(tableNumber)].getMaxSeats()) { //too big party
              System.out.println("Sorry, max " + tables[findTableIndex(tableNumber)].getMaxSeats() + " seats in Table " + tableNumber + "!");
            }
            else { //good fit, put them at table
              tables[findTableIndex(tableNumber)].setStatus(true); //occupied
              tables[findTableIndex(tableNumber)].numGuests = pNum; //numGuests
              System.out.println("Party of " + pNum + " assigned to Table " + tableNumber);
            }
          }

          else if(varInputs.charAt(0) == 'O') { //Order
            //1 O A1 A1 B1 format
            String oo = input.nextLine(); //order str
            String ord = oo.substring(1); //remove first space
            String[] stOrd = ord.split(" ", 0); //split into items
            int newItems = 0; //counter
            for(int k=0; k<stOrd.length; k++) { //loop items in order
              if(menu.realItem(stOrd[k]) == false)
                System.out.println("No item with code " + stOrd[k]);
              else { //real code
                //add to tables order
                int itIndex = findItemIndex(stOrd[k]); //index for requested item
                String cccode = menu.menu[itIndex].getCode(); //item info to be stored
                String nnname = menu.menu[itIndex].getName();
                double ppprice = menu.menu[itIndex].getPrice();
                tables[findTableIndex(tableNumber)].addItem(cccode, nnname, ppprice); //feed item info for order
                newItems++;
              }
            } //end order loop
            //inform of the order
            System.out.print(newItems);
            if(tables[findTableIndex(tableNumber)].getOrderStatus() == true) //already ordered
              System.out.print(" additional");
            System.out.println(" items ordered for Table " + tableNumber);
            tables[findTableIndex(tableNumber)].setOrderStatus(true); //order has happened
          }

          else if(varInputs.charAt(0) == 'S') { //serve food
            if (tables[findTableIndex(tableNumber)].getOrderStatus() == false) { //no order
              System.out.println("Order not placed at Table " + tableNumber + " yet!");
            }
            else { //yes order
              System.out.println("Food served in table " + tableNumber);
              tables[findTableIndex(tableNumber)].setServed(true);
            }
          }
          else if(varInputs.charAt(0) == 'C') { //check
            if(tables[findTableIndex(tableNumber)].getServed() == false) { //no food out
              System.out.println("Food not served for Table " + tableNumber + " yet!");
            }
            else {
              System.out.println("Table " + tableNumber + " is closed. Here is the bill.");
              //do bill
              tables[findTableIndex(tableNumber)].printBill();

              tables[findTableIndex(tableNumber)].setStatus(false); //reset table to empty
              tables[findTableIndex(tableNumber)].setServed(false);
              tables[findTableIndex(tableNumber)].setOrderStatus(false);
              tables[findTableIndex(tableNumber)].numGuests = 0;
            }
          }
        }
        else //not an int start, close the program
          break;
      }//end of active loop
    }//end of main
    public static int findTableIndex(int tableN) { //use this everywhere to get the right index in table array for table number
      int index = 0;
      for(int j=1; j<=Table.numTables; j++) {
        if(tableN == tables[j].getTableNumber())
          index = j;
      }
      return index;
    }//end of table index
    public static int findItemIndex(String itemC) { //use this to get the right index in menu array for item code
      int index = 0;
      for(int j=0; j<16; j++) {
        if(menu.menu[j].getCode().equals(itemC)) {
          index = j;
        }
      }
      return index;
    }//end of item index
  } //end of class