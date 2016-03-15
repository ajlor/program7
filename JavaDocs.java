/**
 * A class tha describes how to check if a file properly contains a javadocs header.
 * @author Nikki Everson
 * @author Annie Lor
 * @version program07
 *
 */

import java.util.Scanner;
public class JavaDocs implements Checkable{

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
         System.out.println("Check for JavaDocs heading? (yes/no)");
         input = scan.nextLine();
         if (input.equals("yes")){
            o.set("javadocs", "true");
            flag = true;
         }else if (input.equals("no")){
            o.set("javadocs","false");
            flag = true;
         }else{
            System.out.println("Incorrect input for JavaDocs heading prefercence. You must enter "+
                                 "'yes' or 'no'");
         }
      }
   }


   /**
    * Method that checks a string for javadocs attributes,
    * returning true if the string
    * contains no all proper attributes for the header and false if not.
    * @param input The string the method is checking.
    * @return True if the string contains all requirements and false if not.
    */
   public boolean check(String input, Options o){
      Boolean jd = (Boolean) o.get("javadocs");
      boolean passing = true;
      if (jd.booleanValue()){
         if (!input.contains("/**")){
            passing = false;
         }
         //if (!input.contains("*/")){
            //passing = false;
         //}
         if (!input.contains("@author")){
            passing = false;
         }
         if (!input.contains("@version")){
            passing = false;
         }
      }
      return passing;
   }

   /**
    * Method that simply returns the proper error message
    * for a line containing
    * insufficient javadoc headings.
    * @param line The line number that the error occured
    * @return The error message.
    */
   public String error(int line){
      return "Failed JavaDocs beginning comments at line: "+line;
   }
}

