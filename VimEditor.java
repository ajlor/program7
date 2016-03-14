import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;

public class VimEditor {
   private ArrayList<String> keyword;
   private ArrayList<String> vim = new ArrayList<String>();

   public VimEditor(String fileName, boolean makeChange) {
      keyword = new ArrayList();
      keyword.add("set textwidth");
      keyword.add("set formatoptions");
      keyword.add("set nopaste");
      keyword.add("set softtabstop");
      keyword.add("set expandtab");
      keyword.add("set match ExtraWhitespace");
      keyword.add("set highlight ExtraWhitespace ctermbg");
      try {
         File file = new File(fileName);
         Scanner s = new Scanner(file);
         while (s.hasNextLine()) {
            String line = s.nextLine();
            vim.add(line);
         } if (makeChange == true){
            change();
         }
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      }

   }
   public void change() {
      for (int i = 0; i < keyword.size(); i++) {
         if(i == 0) {
            lineWidth(in);
         } else if (i == 3) {
            tab(in);
         } else if (i == 5) {
            highlightWhiteSpaces(in);
         } else if (i == 6) {
            highlightColor(in);
         }
      }

   }

   public void write(String fileName) {
      try {
         File file = new File(fileName);
         PrintStream write = new PrintStream(file);
         for (int i = 0; i < vim.size(); i++) {
            write.println(vim.get(i));
         }
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      }
   }

   public String append(String input) {
      try
      {
         FileWriter fw = new FileWriter(".vimrc_back",true); //the true will append the new data
         fw.write(input);//appends the string to the file
         fw.close();
      }
      catch(IOException ioe)
      {
         System.err.println("IOException: " + ioe.getMessage());
      }
   }
   private void lineWidth(String in) {
      if (line.startsWith(keyword.get(0))) {
         for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ("=")){
               line.replace(line.charAt(i+1), in.charAt(i));
            }
         }
         append(keyword.get(1) + "+=t");
         append(keyword.get(2));
      } else {
         append(keyword.get(0) + in);
         append(keyword.get(1) + "+=t");
         append(keyword.get(2));
 
      }

   }

   private void tab(String in) {
      if (line.startsWith(keyword.get(3))) {
         for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ("=")){
               line.replace(line.charAt(i+1), in.charAt(i));
            }
         }
         append(keyword.get(4));
      } else {
         append(keyword.get(3) + in);
         append(keyword.get(4));
      }
   }

   private void highlightWhiteSpaces(String in) {
      if (line.startsWith(keyword.get(5))) {
         for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ("=")){
               line.replace(line.charAt(i+1), in.charAt(i));
            }
         }
      } else {
         append(keyword.get(5) + in);
      }
   }

   private void highlightColor(String in) {
      if (line.startsWith(keyword.get(6))) {
         for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ("=")){
               line.replace(line.charAt(i+1), in.charAt(i));
            }
         }
      } else {
         append(keyword.get(6) + in);
 
      }
   }

}
