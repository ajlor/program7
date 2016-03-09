


import java.util.Scanner;
import jave.util.ArrayList;
public class Vstyle{
   private boolean whitespace = false;
   private boolean javadocs = false;
   private Integer linewidth;
   private boolean tabs = false;
   private Integer whitecount;
   private boolean braces;
   private boolean spacing;
   private ArrayList<String> keys = new ArrayList<String> 

   public static void main(String[] args){
      
      if (args.length > 1){
         System.out.println("Please type one of the following commands: "+ 
                            " 'new', 'tips', 'help',or fileName.java");
         return;
      }


      if (args[0].equals("new")){
         System.out.println("To create a new Style Checker, "+
               "enter the following information in order it is printed, "+
               "separated by spaces.");
         ArrayList<String> list = new ArrayList<String>();
         if (list.size() != 4){
            System.out.println("Imporper Input. Start over with correct responses.");
         }
         setCheck(list);

      }
      else if (args[0].equals("help")){
         System.out.println("Welcome! This is Vstyle. A source meant to help you "
               +"check style on your programs as well as changin your vimrc if "+
               "desired. Type in 'new' to create new specifications, type 'tips' "+
               "for helpful vim tips, type in the file name to use the existing "+
               "Vstyle guide on that specific file.");
      }
      else if (args[0].equals("tips")){
         System.out.println("Here are some some useful tips when using vim. ");
      }
      else if (args[0].endsWith(".java")){
         System.out.println("Checking "+args[0]+ "...");
         savedCheck(args[0]);

      }else{
         System.out.println("Please type one of the following commands: "+ 
                            " 'new', 'tips', 'help',or fileName.java");

         return;
      }
   
   }

   private static void savedCheck(String fileName){
      Scanner scan = new Scanner(new File(fileName);
      int line = 0;
      while (scan.hasNextLine()){
         String in = scan.nextLine();
         checkLineWidth(in,line);
         checkTabs(in,line);
         checkWhiteSpace(in,line);
         checkJavaDocs(in,line);
         checkBraces(in,line);
         checkBOSpcacing(in,line);
         checkCamelCasing(in,line);
         checkIndents(in,line);
         line++;
      }
   }

   private static void setCheck(ArrayList list){
      setLineWidth(list.get(0));
      if (list.get(1).equals("tabs"){
         //keep tabs
         tabs = true;
      }
      else{
         setTabs(list.get(1));
      }
      setWhiteSpace(list.get(2));
      setJavaDocs(list.get(3));
   }
         
   private static void setTabs(String i){
      try{
         whitecount = new Integer(i);
      }catch (NumberFormatException e){
         System.out.println("Imporper Input. Start over with correct responses.");
         return;
      }
   }
   private static void setLineWidth(String i){
      try{
         linewidth = new Integer(i);
      }catch (NumberFormatException e){
         System.out.println("Imporper Input. Start over with correct responses.");
         return;
      }
   }
   private static void setWhiteSpace(String i){
      if (i.equals("yes"){
         whitespace = true;
      }else if (i.equals("no"){
         whitespace = false;
      }else{
         System.out.println("Imporper Input. Start over with correct responses.");
         return;
      }
   }
   private static void setBraces(String i){
      if (i.equals("yes"){
         braces = true;
      }else if (i.equals("no"){
         braces = false;
      }else{
         System.out.println("Imporper Input. Start over with correct responses.");
         return;
      }
   }
   private static void setSpacing(String i){
      if (i.equals("yes"){
         spacing = true;
      }else if (i.equals("no"){
         spacing = false;
      }else{
         System.out.println("Imporper Input. Start over with correct responses.");
         return;
      }
   }
   private static void setJavaDocs(String i){
      if (i.equals("yes"){
         javadocs = true;
      }else if (i.equals("no"){
         javadocs = false;
      }else{
         System.out.println("Imporper Input. Start over with correct responses.");
         return;
      }
   }
   private static void checkBraces(String input, int line){
      if (!braces){
         return;
      }
      
   }
   private static void checkLineWidth(String input, int line){
      if (input.length() > linewidth){
         System.out.println("Failed LineWidth check at line: "+line);
      }
   }

      

}
