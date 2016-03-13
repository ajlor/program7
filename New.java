import java.util.Scanner;

public class New {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      boolean done = false;
   
      while (!done) {
         System.out.println("Max amount of characters per line (as an integer).");
         int lineWidth = input.nextInt();
         System.out.println("Tabs or number of spaces in a tab (as an integer).");
         int tabWidth = input.nextInt();
         System.out.println("Find extra white spaces? (yes or no)");
         boolean whiteSpace = input.nextBoolean();
         System.out.println("Would you like a javadoc comment heading? (yes or no)");
         boolean javaDocs = input.nextBoolean();
         break;
      }
   }
 }
