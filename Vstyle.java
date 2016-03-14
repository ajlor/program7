/**
 * Vstyle is a program styleguide checker and Vimrc editor. It allows a user to check
 * an exisiting file for correct format, create a new program specifications, and
 * obtain vim tips.
 * @author Nikki Everson
 * @author Annie Lor
 * @version program 07
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Vstyle{

   /**
    * A driver that serves as a menu for the user and prompts the user for inputs to either check
    * programs style or create a new specification to change their vimrc.
    */
   public static void main(String[] args){
      ArrayList<Checkable> checks = new ArrayList<Checkable>();
      checks.add(new LineWidth());
      checks.add(new TabSpace());
      checks.add(new Tabs());
      checks.add(new WhiteSpace());
      checks.add(new JavaDocs());
      Scanner scan = new Scanner(System.in);

      if (args.length == 0){
         System.out.println("Please type in 'java Vstyle' followed by one of the following "+
                              "commands: 'new', 'tips', 'help', or fileName.java");
         return;
      }


      Options o = new Options();
      if (args[0].equals("new")){
         for (Checkable c : checks){
            c.askForOptions(o,scan);
         }
      }else if (args[0].equals("help")){
         System.out.println("Welcome! This is Vstyle. A source meant to help you "
               +"check style on your programs as well as changin your vimrc if "+
               "desired. Type in 'new' to create new specifications, type 'tips' "+
               "for helpful vim tips, type in the file name to use the existing "+
               "Vstyle guide on that specific file. Note that to use your saved "+
               "preferences, you must have the preference file in your current "+
               "directory");
      }
      else if (args[0].equals("tips")){
         System.out.println("Here are some some useful tips when using vim. ");
         System.out.println("\nFor basic searching...");
         System.out.println("\t/pattern" + "\t-search forward for pattern");
         System.out.println("\t?pattern" + "\t-search backward");
         System.out.println("\nReformatting...");
         System.out.println("\tV=" + "\t\t-select text, then reformat with =");
         System.out.println("\t=" + "\t\t-will correct alignment of code");
         System.out.println("\t==" + "\t\t-one line");
         System.out.println("\tgq" + "\t\t-reformat paragraph");
         System.out.println("\nFor more tips, go to www.cs.oberlin.edu/~kuperman/help/vim");
         System.out.println("\nIndenting...");
         System.out.println("These are some variables that you can set in the vim editor  or "
               +"your vimrc file.");
         System.out.println("\t:set tabstop 8" + "\t\t-tabs are at proper location");
         System.out.println("\t:set expandtab" + "\t\t-don't use actual tab character "
               + "(ctrl-v)");
         System.out.println("\t:set shiftwidth=4" + "\t-indenting is 4 spaces");
         System.out.println("\t:set autoindent" + "\t\t-copies the indentation from "
               + "the previous line when starting a new line");
         System.out.println("\nFor more tips, go to www.cs.oberlin.edu/~kuperman/help/vim");
         System.out.println("\n or simply type :help for more vim help.");
      }
      else if (args[0].endsWith(".java")){
         System.out.println("Checking "+args[0]+ "...");
         savedCheck(args[0], checks, o);
         if (args.length > 1){
            for (int i = 1; i<args.length;i++){
               if (args[i].endsWith(".java")){
                  System.out.println("Checking "+args[i]+ "...");
                  savedCheck(args[i], checks, o);
               }else{
                  System.out.println("To scan multiple files, they all must end in '.java'");
                  return;
               }
            }
         }

      }else{
         System.out.println("Please  X type one of the following commands: "+
                              " 'new', 'tips', 'help',or files inding in '.java'");

         return;
      }

   }

   private static void savedCheck(String fileName, ArrayList<Checkable> Checks, Options o){
      ArrayList<String> errorlist = new ArrayList<String>();
      Scanner scanjava = null;
      Scanner scan = null;
      try{
         scan = new Scanner(new File(fileName));
         scanjava = new Scanner(new File(fileName));
      }catch (FileNotFoundException e){
         System.out.print("File unable to be found, try again with the file in the current "+
                           "directory");
      }
      Boolean jd = (Boolean) o.get("javadocs");
      JavaDocs j = new JavaDocs();
      if (scanjava != null){
         if (jd.booleanValue()){
            String contents = scanjava.useDelimiter("\\*/").next();
            if (!j.check(contents, o)){
               errorlist.add(j.error(0));
            }
         }
         scanjava.close();
      }
      int line = 1;
      if (scan != null){
         while (scan.hasNextLine()){
            String in = scan.nextLine();
            if (!in.isEmpty()){
               for (Checkable x : Checks){
                  if (!(x instanceof JavaDocs)){
                     if (!x.check(in, o)){
                        errorlist.add(x.error(line));
                     }
                  }
               }
            }
            line++;
         }
         scan.close();
      }
      if (errorlist.size() == 0){
         System.out.println("Wubba Wubba Dubb Dubb! No errors found in this file");
         System.out.println();
      }else{
         System.out.println("Errors for file: "+fileName);
         for (String e : errorlist){
            System.out.println(e);
         }
         System.out.println();
      }
   }
}
