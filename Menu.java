import java.util.*;
import java.io.*;

//read menu twice for exact length
public class Menu {
    MenuItem menu[] = new MenuItem[100];
    private String filename;  //instance variable - it exists in each object

    Menu(String name) throws FileNotFoundException{ //we just need the config file and where to start
      filename = name;
      int lineNum = 0;

      File config = new File(name);
      Scanner filee = new Scanner(config); //open file
      String namee = " ";
      String codee = " ";
      String pricee =" ";
      int fixIn = 0;
      for(int i=0; i<38; i++) {
      if(i<38) {
        
        if(i > 21) {
          codee = filee.next();
          namee = filee.next();
          
          pricee = filee.next();
          Double d = Double.valueOf(pricee);
          //System.out.println(codee + " " + namee + " " + d);
          menu[fixIn] = new MenuItem(codee,namee,d); //make an object
          fixIn++; //tightly pack at beginning of array
        }
        else
          codee = filee.nextLine();
        lineNum++;
      }
    }
      filee.close(); //shut
    }

    public boolean realItem(String codey) { //returns true for valid item code
      int bb = MenuItem.numItems;
      for(int i=0; i<bb; i++) { //search all available
        if (menu[i].getCode().equals(codey)) {
          return true; //got it
        }
      }
      return false; //hit end, not here
    }//end real item
  } //end of class