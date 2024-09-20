import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ArithmeticImpl extends UnicastRemoteObject implements Arithmetic {
    public ArithmeticImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }
}