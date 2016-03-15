/**
 * The Checkable interface contains the checking methods.
 * @author Nikki Everson
 * @author Annie Lor
 * @version program07
 */
import java.util.Scanner;
public interface Checkable{
   /**
    * Prompts user for inputs to put into the Options Object and scans the input.
    * If the input is incorrect, it will reprompt for input.
    * @param options The option input from the Scanner.
    * @param scan The scanner for the option input.
    */
   public void askForOptions(Options options, Scanner scan);
   /**
    * Method that checks a string for line length,
    * returning true if the string
    * contains more characters than the number
    * specified when constructing
    * the LineWidth object and false if the line is too long.
    * @param input The string the method is checking.
    * @param options The options object that has the information on the check.
    * @return True if the string contains the correct length and false if not.
    */
   public boolean check(String input, Options options);
   /**
    * Method that simply returns the proper error
    * message for a line containing
    * improper length.
    * @param line The line number that the error occured
    * @return The error message.
    */
   public String error(int line);
}
