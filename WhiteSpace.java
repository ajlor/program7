/**
 * A class describing trailing whitespace in a string, that implements the
 * Checkable interface.
 * Can check for trailing whitespace and print a proper
 * whitespace error message.
 * @author Nikki Everson
 * @author Annie Lor
 * @version program07
 */

import java.util.Scanner;
public class WhiteSpace implements Checkable{

   /**
    * Prompts user for inputs to put into the Options Object and scans the input.
    * If the input is incorrect, it will reprompt for input.
    * @param options The option input from the Scanner.
    * @param scan The scanner for the option input.
    */
   public void askForOptions(Options o, Scanner scan){
      boolean flag = false;
      String input = "";
      while (!flag){
         System.out.println("Check for trailing/extra whitespace? (yes/no)");
         input = scan.nextLine();
         if (input.equals("yes")){
            o.set("whitespace", "true");
            flag = true;
         }else if (input.equals("no")){
            o.set("whitespace","false");
            flag = true;
         }else{
            System.out.println("Incorrect input for whitespace prefercence. You must enter "+
                                 "'yes' or 'no'");
         }
      }
   }

   /**
    * Method that checks a string for trailing whitespace,
    * returning true if the string
    * contains no extra whitespace and false if there is
    * any at the end of the line.
    * @param input The string the method is checking.
    * @return True if the string contains trailing whitespace and false if not.
    */
   public boolean check(String input, Options o){
      Boolean ws = (Boolean) o.get("whitespace");
      if (ws.booleanValue()){
         return (!(input.endsWith(" ")));
      }
      return true;
   }

   /**
    * Method that simply returns the proper error message for a line containing
    * trailing whitespace when it should not.
    * @param line The line number that the error occured
    * @return The error message.
    */
   public String error(int line){
      return "Failed trailing/extra whitespace check at line: "+line;
   }
}
