import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
public class VimEditor {
   private ArrayList<String> keyword;

   public static void main(String[] args) {
      VimEditor vim = new VimEditor("vimrc.txt");
      System.out.println(vim);
   }

   public VimEditor(String fileName) throws VimEditorException {
      keyword = new ArrayList();
      keyword.add("linewidth");
      keyword.add("tabstop");
      keyword.add("softtabstop");
      keyword.add("expandtab");
      keyword.add("match ExtraWhitespace");
      keyword.add("highlight ExtraWhitespace ctermbg");
      try {
         File f = new File(fileName);
         Scanner s = new Scanner(f);
         while (s.hasNextLine()) {
            String line = s.nextLine();
            System.out.println(line);
         }
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      }
   }
   
   public String change() {
            if (line.contains(keyword.get(0))) {
               lineWidth(fileName);
            } else  {
               append(fileName);
            }
            if (line.contains(keyword.get(1))) {
               tab(fileName);
            } else {
               append(fileName);
            }
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   public String append(String fileName) {
      try
      {
         FileWriter fw = new FileWriter(fileName,true); //the true will append the new data
         fw.write("add a line\n");//appends the string to the file
         fw.close();
      }
      catch(IOException ioe)
      {
         System.err.println("IOException: " + ioe.getMessage());
      }
   }
   private String lineWidth(String fileName, String input) {
      
      PrintStream write = new PrintStream(fileName);
      write.println("linewidth " + input);
   }

   private String tab(String fileName, String input) {
      PrintStream write = new PrintStream(fileName);
      write.println("softtabstop " + input);

   }

   private String highlightWhitespaces(String fileName, String input) {
      PrintStream write = new PrintStream(fileName);
      write.println("match ExtraWhitespaces " + input);

   }

   private String highlightColor(String fileName) {
      PrintStream write = new PrintStream(fileName);
      write.println("highlight ExtraWhitespaces ctermbg = " + input);

   }
   
}
