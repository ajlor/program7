/**
 * VimEditor allows the user to edit their vimrc with the preferences inputted
 * in the command line. VimEditor will look for the vimrc in the home directory
 * and make a copy of the vimrc. The user inputs for the specific settings will be
 * written into the vimrc.
 * @author Nikki Everson
 * @author Annie Lor
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;

public class VimEditor{
   private ArrayList<Boolean> preferences = new ArrayList<Boolean>();
   private Integer linewidth;
   private Integer tabspace;
   private String path;

   /**
    * Method finds the vimrc in the home directory and backs up the file into a file
    * called "vim_back". The method will throw a FileNotFoundException if the home directory
    * username is not found.
    * @param username The username for the home directory (if on the SSH server, it will be your
    * Cal Poly username).
    */
   public VimEditor(String username) throws FileNotFoundException{
      path = "//home/"+username+"/.vimrc";
      try{
         backUp();
      }catch (FileNotFoundException e){
         throw new FileNotFoundException();
      }
   }

   private void backUp() throws FileNotFoundException{
      Scanner scan = null;
      PrintStream p = null;
      try{
         scan = new Scanner(new File(path));
         p = new PrintStream("vim_backup");
      }catch(FileNotFoundException e){
         System.out.println(".vimrc not found");
         throw new FileNotFoundException();
      }
      if ((scan != null) && (p != null)){
         while (scan.hasNextLine()){
            p.println(scan.nextLine());
         }
         scan.close();
         p.close();
      }
   }

   private void append(PrintStream p){
      p.println("\"Vstyle Preferences");
      if (preferences.get(0).booleanValue()){
         p.println("\"Vstyle Line limits");
         p.println("set formatoptions+=t  \"Vstyle");
         p.println("set textwidth="+linewidth+"  \"Vstyle");
         p.println("set nopaste \"Vstyle");
      }
      if (preferences.get(1).booleanValue()){
         p.println("\"Vstyle Tabspacing");
         p.println("set softtabstop="+tabspace+"  \"Vstyle");
         p.println("set expandtab  \"Vstyle");
      }
      if (preferences.get(3).booleanValue()){
         p.println("\"Vstyle Highlighting");
         p.println("highlight ExtraWhitespace ctermbg=yellow   \"Vstyle");
         p.println("match ExtraWhitespace /\\S\\zs\\s\\+$/  \"Vstyle");
      }
      if (preferences.get(2).booleanValue()){
         p.println("\"Vstyle color");
         p.println("syntax on  \"Vstyle");

      }
   }

   /**
    * Method executes the methods that are used to find line widths, tabspace, coloring,
    * white space keywords and the method to delete the path that contains the keyword(s).
    * @param scan The scanner to scan for the keyword that sets the line widths, tabspace,
    * coloring, and white spaces in the vimrc.
    */
   public void askForOptions(Scanner scan){
      askLineWidth(scan);
      askTabSpace(scan);
      askColoring(scan);
      askWhiteSpace(scan);
      searchAndDelete(path);
      System.out.println("\nDone! If you are dissatisfied, you can find a copy of your original "+
                           "vimrc in a"+
                           " file called vim_back up in this directory. You can also execute the "+
                           "program again "+
                           "with new preferences");
   }

   private void askLineWidth(Scanner scan){
      boolean done = false;
      String in = "";
      while (!done){
         System.out.println("\nWould you like to change the linewidth in your .vimrc to the "+
                           "currently specified setting in your options? (yes/no)");
         System.out.println("Note, this will only autofix comment lines.");
         in = scan.nextLine();
         if (in.equals("yes")){
            Options o = new Options();
            preferences.add(new Boolean(true));
            linewidth = (Integer) o.get("linewidth");
            done = true;
         }else if (in.equals("no")){
            preferences.add(new Boolean(false));
            done = true;
         }else{
            System.out.println("\nIncorrect input, try again.");
         }
      }
   }
   private void askTabSpace(Scanner scan){
      boolean done = false;
      String in = "";
      while (!done){
         System.out.println("\nWould you like to change the tabspacing in your .vimrc to the "+
                           "currently specified setting in your options? (yes/no)");
         in = scan.nextLine();
         if (in.equals("yes")){
            Options o = new Options();
            tabspace = (Integer) o.get("tabspace");
            preferences.add(new Boolean(true));
            done = true;
         }else if (in.equals("no")){
            preferences.add(new Boolean(false));
            done = true;
         }else{
            System.out.println("\nIncorrect input, try again.");
         }
      }
   }
   private void askColoring(Scanner scan){
      boolean done = false;
      String in = "";
      while (!done){
         System.out.println("\nWould you like coloring? (yes/no)");
         in = scan.nextLine();
         if (in.equals("yes")){
            preferences.add(new Boolean(true));
            done = true;
         }else if (in.equals("no")){
            preferences.add(new Boolean(false));
            done = true;
         }else{
            System.out.println("\nIncorrect input, try again.");
         }
      }
   }
   private void askWhiteSpace(Scanner scan){
      boolean done = false;
      boolean checkeriq = false;
      String in = "";

      while (!done){
         System.out.println("\nWould you like to highlight trailing whitespace? (yes/no)");
         in = scan.nextLine();
         if (in.equals("yes")){
            preferences.add(new Boolean(true));
            done = true;
         }else if (in.equals("no")){
            preferences.add(new Boolean(false));
            done = true;
         }else{
            System.out.println("\nIncorrect input, try again.");
         }
      }
      if (done){
         while (!checkeriq){
            System.out.println("\nAre you Eriq? (yes/no)");
            in = scan.nextLine();
            if (in.equals("yes")){
               System.out.println("\nHa! Lame! Hey guys come look at the cool kid over here!");
               System.out.println("We're taking your leading whitespace "+
                                 " highlighting away!");
               System.out.println("...");
               System.out.println("Jk. We trollin'. We gon' not even mess with bullshit "+
                                 "beginning line "+
                                 "whitespace highlights.");
               System.out.println("Cuz it's lame.");
               System.out.println("Cooler kids just highlight trailing whitespace.");
               checkeriq = true;

            }else if (in.equals("no")){
               checkeriq = true;
            }else{
               System.out.println("\nIncorrect input, try again.");
            }
         }
      }
   }


   private void searchAndDelete(String fileName){
      boolean flag = false;
      Scanner scan = null;
      PrintStream p = null;
      File other = null;
      File actual = null;
      String str = "i";
      String nl = "i";
      try{
         other = new File(fileName+"-");
         actual = new File(fileName);
         scan = new Scanner(actual);
         p = new PrintStream(other);
      }catch(FileNotFoundException e){
         System.out.println(".vimrc not found" + e.getMessage());
         return;
      }
      if ((scan != null) && (p != null)){
         while (scan.hasNextLine()){
            str = scan.nextLine();
            if (str.contains("Vstyle")){
               flag = true;
            }
            if (!(flag)){
               p.println(str);
               nl = str;
            }
         }
         if (!nl.isEmpty()){
            p.println();
         }

         append(p);
         scan.close();
         p.close();
         if ((other != null) && (actual != null)){
            if (!other.renameTo(actual)){
               System.out.println("Failed to rename");
            }
         }
      }
   }

}




