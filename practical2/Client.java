import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Lookup the remote object in the registry
            Arithmetic arithmetic = (Arithmetic) Naming.lookup("rmi://localhost/ArithmeticService");

            Scanner sc = new Scanner(System.in);
            while (true){
            // declaring two variables for input ...
            System.out.println("enter the first number...");
            int num1 = sc.nextInt();
            System.out.println("enter the second number...");
            int num2 = sc.nextInt();

            
            // Call the remote methods
            int sum = arithmetic.add(num1,num2);
            int difference = arithmetic.subtract(num1,num2);

            System.out.println("Addition: " + sum);
            System.out.println("Subtraction: " + difference);
            
            System.out.println("enter 1 to continue or 0 for exit");
            int exit = sc.nextInt();
            
            if (exit==0){
                break;
            }
            else{
                continue;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}