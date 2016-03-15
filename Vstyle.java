


import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Vstyle{

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
         System.out.println();
         System.out.println();
         getVim(scan);

      }else if (args[0].equals("help")){
         System.out.println("Welcome! This is Vstyle. This is a source meant to "+
               "help you "+
               "check style on your programs as well as changing your vimrc if "+
               "desired. Type in 'new' to create new specifications, type 'tips' "+
               "for helpful vim tips, type in the file name to use the existing "+
               "Vstyle guide on that specific file. Note that to use your saved "+
               "preferences, you must have the preference file in your current "+
               "directory");
      }
      else if (args[0].equals("tips")){
         System.out.println("Here are some some useful tips when using vim. ");
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
   private static void getVim(Scanner scan){
      boolean done = false;
      boolean correct = false;
      String str = "";
      VimEditor vm = null;

      while (!done){
         System.out.println("Would you also like to change your .vimrc? (yes/no)");
         str = scan.nextLine();
         if (str.equals("yes")){
            String username = "1 1";
            while (!correct){
               username = getUsername(scan);
               try{
                  vm = new VimEditor(username);
                  correct = true;
               }catch (FileNotFoundException e){
                  System.out.println("Incorret username given, try again.");
               }
            }
            done = true;

         }else if (str.equals("no")){
            System.out.println("Ok, cool. It's fine, I get it.");
            done = true;
         }else{
            System.out.println("That wasn't a yes or no ya dunce.");
         }
      }
      if (vm != null){
         vm.askForOptions(scan);
      }
   }



   private static String getUsername(Scanner scan){
      boolean done = false;
      String username = "1 1";
      while (!done){
         System.out.println("Enter your username for this home directory");
         username = scan.nextLine();
         Scanner sc = new Scanner(username);
         sc.next();
         if (!sc.hasNext()){
            done = true;
         }else{
            System.out.println("Please only enter one word");
         }
      }
      if (username.equals("1 1")){
         done = false;
      }
      return username;
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
         System.out.println("Wubba Lubba Dubb Dubb! No errors found in this file");
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
