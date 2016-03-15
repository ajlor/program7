/**
 * A class describing tabspacing in a string, that implements the Checkable interface.
 * Can check for tabspacing and print a proper tabspace error message if
 * the indentation is incorrect via the tabspaces.
 * @author Nikki Everson
 * @author Annie Lor
 * @version program07
 */

import java.util.Scanner;
public class TabSpace implements Checkable{

   /**
    * Prompts user for inputs to put into the Options Object and scans the input.
    * If the input is incorrect, it will reprompt for input.
    * @param options The option input from the Scanner.
    * @param scan The scanner for the option input.
    */

   public void askForOptions(Options o, Scanner scan){
      boolean flag = false;
      String s = "";
      while (!flag){
         System.out.println("What should the limit on spaces per indent (soft tab) be?");
         s = scan.nextLine();
         Scanner sc = new Scanner(s);
         if (!sc.hasNextInt()){
            System.out.println("Improper Input for editing Options: "+
                              "  integer required for option you specified. Try again.");

         }else{
            sc.next();
            if (sc.hasNext()){
               System.out.println("Improper Input for editing Options: "+
                                 "  ONLY integer required for option you specified. Try again.");

            }else{
               flag = true;
            }
         }
      }
      o.set("tabspace", s);

   }


   /**
    * Method that checks a string for pre-line spacing,
    * returning true if the string
    * contains pre-line spacing divisible by the amount of spaces
    * specified when constructing
    * the TabSpace object and false if the tabs (indents) are proper.
    * @param input The string the method is checking.
    * @return True if the string contains correct tabspacing and false if not.
    */
   public boolean check(String input, Options o){
      Boolean tabs = (Boolean) o.get("tabs");
      Integer ts = (Integer) o.get("tabspace");
      if (tabs.booleanValue()){
         int count = 0;
         Scanner scan = new Scanner(input);
         if (scan.hasNext()){
            for (int i = 0; input.charAt(i) == ' ';i++){
               if (i < input.length()){
                  count++;
               }
            }
            if (input.trim().startsWith("*")){
               return true;
            }
            return count % ts == 0;
         }
      }
      return true;
   }

   /**
    * Method that simply returns the proper error
    * message for a line containing
    * improper spacing before the the code.
    * @param line The line number that the error occured
    * @return The error message.
    */
   public String error(int line){
      return "Failed TabSpace (spaces per tab) at line: "+line;
   }
}

