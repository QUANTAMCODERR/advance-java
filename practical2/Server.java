import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Arithmetic arithmetic = new ArithmeticImpl();
            Naming.rebind("rmi://localhost/ArithmeticService", arithmetic);
            System.out.println("ArithmeticService is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}