import java.util.Scanner;
public interface Checkable{
   public void askForOptions(Options options, Scanner scan);
   public boolean check(String input, Options options);
   public String error(int line);
}
