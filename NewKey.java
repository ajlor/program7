import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class NewKey {
   public static void main(String[] args) {
      //ArrayList<String> list = new ArrayList<String>();
      System.out.println("1. Max amount of characters per line (as an integer). ");
      System.out.println("2. Tabs or number of spaces in a tab (as an integer). ");
      System.out.println("3. Find extra white spaces? (yes or no)");
      System.out.println("4. Would you like a javadoc comment heading? (yes or no)");

      Scanner input = new Scanner(System.in);
      String items = input.nextLine();
      ArrayList<String> list = new ArrayList<String>(Arrays.asList(items.split(" ")));
      // do -while loop if input less or greater than 6 inputs (repeatedly entered.)
      /*
          do {
         System.out.println("Improper input. Start over with correct responses.");
         list.clear();
         items = input.nextLine();
         list.addAll(Arrays.asList(items.split(" ")));
      } while (list.size() != 4);
      */

      // if statement for input less or greater than 6 inputs.
      if (list.size() != 4) {
         System.out.println("Improper input. Start over with correct responses.");
         list.clear();
         items = input.nextLine();
         list.addAll(Arrays.asList(items.split(" ")));
      }

      System.out.println(list);
      System.out.println("\nWould you like to change your vimrc to reflect some of this?"
              + " (yes or no)");
      list.add(input.next());
      // Prints to see what's in the list and how big the size is
      //Comment out printing later
      System.out.println("In this list: " + list + "\tList size: " + list.size());

      System.out.println("\nWould you like to add any of the following style preferences? ");
      System.out.println("\t1. Line numbers displayed on left.");
      System.out.println("\t2. Highlight the extra white spaces.");
      System.out.println("\t3. Change colors for keywords.");
      System.out.println("If yes, please enter the number of the option you would like "
              + "otherwise, type \"no\" to end.");

      String prefs = input.nextLine();
      if (input.hasNext("1") == true) {
         System.out.println("Display line numbers to left.");
      }
      if (input.hasNext("2") == true) {
         System.out.println("Highlight extra white spaces.");
      }
      if (input.hasNext("3") == true) {
         System.out.println("Changes keyword colors");
      }
      if (input.hasNext("no") == true) {
         return;
      }

      System.out.println("\nWould you like to make anymore changes? If yes, type in the number "
              + "otherwise type \"no\" to end.");
      String secPrefs = input.nextLine();
      if (input.hasNext("1") == true) {
         System.out.println("Display line numbers to left.");
      }
      if (input.hasNext("2") == true) {
         System.out.println("Highlight extra white spaces.");
         }
      if (input.hasNext("3") == true) {
         System.out.println("Changes keyword colors.");
      }
      if (input.hasNext("no") == true) {
         return;
      }
      System.out.println("\nWould you like to make anymore changes? If yes, type in the number "
              + "otherwise type \"no\" to end.");
      String thirdPrefs = input.nextLine();
      if (input.hasNext("1") == true) {
         System.out.println("Display line numbers to left.");
      }
      if (input.hasNext("2") == true) {
         System.out.println("Highlight extra white spaces.");
      }
      if (input.hasNext("3") == true) {
         System.out.println("Changes keyword colors.");
      }
      if (input.hasNext("no") == true) {
         return;
      }
   }
}
