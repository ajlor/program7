/** A class to check linewidth in a string, that implements the Checkable interface.
 * Can check for linewidth and print a proper linewidth error message.
 * @author Nikki Everson
 * @version program07
 */

import java.util.Scanner;

public class LineWidth implements Checkable{


   public void askForOptions(Options o, Scanner scan){
      boolean flag = false;
      String s = "";
      while (!flag){
         System.out.println("What should the limit on characters per line be?");
         s = scan.nextLine();
         Scanner sc = new Scanner(s);
         if (!sc.hasNextInt()){
            System.out.println("Improper Input for editing Options: "+
                              "  integer required for option you specified. Try again.");

         }else if (sc.hasNextInt()){
            sc.next();
            if (sc.hasNext()){
               System.out.println("Improper Input for editing Options: "+
                                 "  ONLY integer required for option you specified. Try again.");

            }else{
               flag = true;
            }
         }
      }
      o.set("linewidth", s);

   }



   /**Method that checks a string for line length,
    * returning true if the string
    * contains more characters than the number
    * specified when constructing
    * the LineWidth object and false if the line is too long.
    * @param input The string the method is checking.
    * @param options The options object that has the information on the check.
    * @return True if the string contains the correct length and false if not.
    */
   public boolean check(String input, Options o){
      Integer lw = (Integer) o.get("linewidth");
      if (input.length() > lw){
         return false;
      }else{
         return true;
      }
   }


   /** Method that simply returns the proper error
    * message for a line containing
    * improper length.
    * @param line The line number that the error occured
    * @return The error message.
    */
   public String error(int line){
      return "Failed LineWidth check at line: "+line;
   }
}


