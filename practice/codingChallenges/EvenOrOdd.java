import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EvenOrOdd{
  public static void main(String[] args) throws IOException{
    try{
            int n;
            BufferedReader in = 
            new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(in.readLine());
        if (n % 2 == 0)
        {
            System.out.println("The number is Even.");
        }
        else
        {
            System.out.println("The number is Odd.");
        }
    } catch(NumberFormatException e){
         System.out.println(e.getMessage() + " is not a numeric value.");
         System.exit(0);
        }
  }
} 