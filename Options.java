/**A class that defines Options for the style checker Vstyle. 
 * This class writes to a file the options specified by the set method, 
 * and if no previous options were stated, creates a default file of options.
 * 
 * @author Nikki Everson
 * @version program07
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Options{
   private ArrayList<String> options = new ArrayList<String>();

   /**Constructs an Options object in which the options from a text file named
    * Options.txt are put into list for ease of the get and set methods in this class.
    * If no previous Objects preferences exist, this constructor will make a default set
    * of options in a text file named Options.txt.
    */
   public Options(){
      boolean created = scanThis("Options.txt");
      if (!created){
         System.out.println("Previous options not detected, making default Options preferences..");
         deFault();
         System.out.println("done");
         scanThis("Options.txt");
      }
      if (options.size() != 10){
         System.out.println("Original file incorrect, overriding with new default file.");
         options.clear();
         deFault();
         scanThis("Options.txt");
      }

   }


   /** Method that gets the value for the specified option. The option is case sensitive
    * so the user must enter the exact option as it is written in the Options.txt file.
    * @param option The exact string of the option you are looking for in the object preferences.
    * @return The string representation of the option's value, (i.e. a string containing an
    * integer, 'true', or 'false')
    */
   public Object get(String option){
      if (options.size() == 0){
         return "No Options detected";
      }
      if (!options.contains(option)){
         System.out.println("Improper Option Input: "+ option);
         return "No Options detected";
      }
      String str = options.get(options.indexOf(option) + 1);
      if (str.equals("true") || str.equals("false")){
         return new Boolean(str);
      }else{
         return new Integer(str);
      }

   }

   /**Method that sets the specified option to the specified value. The option must be 
    * written exactly as is in the Options.txt file. 
    * @param option The exact string of the option you are looking for in the object preferences.
    * @param value The value you want to set the option to. Can be an integer, true or false 
    * depending on the option speficied.
    */
   public void set(String option, String value){
      if (options.size() == 0){
         return;
      }
      if (!options.contains(option)){
         System.out.println("Improper Option Input: "+ option);
         return;
      }
      if ((options.indexOf(option) == 0) || (options.indexOf(option) == 2)){
         Scanner sc = new Scanner(value);
         if (!sc.hasNextInt()){
            System.out.println("Improper Input for editing Options: "+value+
                               "  integer required for option you specified");
            return;
         }
         sc.next();
         if (sc.hasNext()){
            System.out.println("Improper Input for editing Options: "+value+
                               "  integer required for option you specified");
            return;
         }
      }else{
         if (!(value.equals("true")) && !(value.equals("false"))){
            System.out.println("Improper Input for editing Options: "+value+
                                 "  true or false required for option you specified");
            return;
         }
      }
      int i = options.indexOf(option) + 1;
      options.set(i,value);
      write("Options.txt", options);
   }

   private void write(String fileName, ArrayList list){
      PrintStream p = null;
      try{
       p = new PrintStream(fileName);
      }catch (FileNotFoundException e){
         System.out.println("Options file unable to be created");
         return;
      }
      if (p != null){
         for (int i = 0; i < list.size(); i++){
            p.print(list.get(i));
            if (i%2 != 0){
               p.println();
            }else{
               p.print(" ");
            }     
         }
         p.close();
      }
   }

   private void deFault(){
      ArrayList<String> list = new ArrayList<String>();
      list.add("linewidth");
      list.add("100");
      list.add("tabspace");
      list.add("3");
      list.add("tabs");
      list.add("true");
      list.add("whitespace");
      list.add("true");
      list.add("javadocs");
      list.add("false");
      write("Options.txt", list);
   }
   
   private boolean scanThis(String fileName){
      Scanner scan = null;
      boolean passed = false;
      try{
         scan = new Scanner(new File(fileName));
         if (!scan.hasNext()){
            System.out.println("Previous options not detected, making default Options preferences..");
            deFault();
            System.out.println("done");
            scanThis("Options.txt");
         }
         while (scan.hasNext()){
            options.add(scan.next());
         }
         passed = true;
         if (scan != null){
            scan.close();
         }
      }catch (FileNotFoundException y){
      }
      return passed;
   }

      
      
}


