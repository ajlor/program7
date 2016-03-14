/** A class to check for tabs in a string, that implements the Checkable interface.
 * Can check for tabs and print a proper tabs error message.
 * @author Nikki Everson
 * @version program07
 */

import java.util.Scanner;
public class Tabs implements Checkable{

   public void askForOptions(Options o, Scanner scan){
      boolean flag = false;
      String input = "";
      while (!flag){
         System.out.println("Check for tabs? (yes/no)");
         input = scan.nextLine();
         if (input.equals("yes")){
            o.set("tabs", "true");
            flag = true;
         }else if (input.equals("no")){
            o.set("tabs","false");
            flag = true;
         }else{
            System.out.println("Incorrect input for tabs. You must enter 'yes' or 'no'");
         }
      }
   }

   /**Method that checks a string for tabs, returning true if the string
    * contains no tabs and false if there are tab characters.
    * @param input The string the method is checking.
    * @return True if the string contains tabs and false if not.
    */
   public boolean check(String input, Options o){
      Boolean tb = (Boolean) o.get("tabs");
      if (tb.booleanValue()){
         return (!(input.contains("\t")));
      }
      return true;
   }


   /** Method that simply returns the proper error message for a line containing
    * tabs when it should not.
    * @param line The line number that the error occured
    * @return The error message.
    */
   public String error(int line){
      return "Failed Tabs (file contains tabs) at line: "+line;
   }
}
