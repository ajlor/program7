/**
 * The Checkable interface contains the checking methods.
 * @author Nikki Everson
 * @author Annie Lor
 * @version program07
 */
import java.util.Scanner;
public interface Checkable{
   /**
    * Prompts user for inputs to put into the Options Object.
    * @param options The option input from the Scanner.
    * @param scan The scanner for the option input.
    */
   public void askForOptions(Options options, Scanner scan);
   
   /**
    * Checks if the input contains an "option" input.
    * @param input The user input for desired option.
    * @param options The option file.
    */
   public boolean check(String input, Options options);
   /**
    * Prints out error if an error occurs in an option.
    * @param line The number line where the error occurred.
    */
   public String error(int line);
}
