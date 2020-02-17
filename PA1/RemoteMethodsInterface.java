import java.rmi.*;

public interface RemoteMethodsInterface extends Remote {
    public void registry(int pid, String fileName) throws RemoteException;
    public int search(String fileName) throws RemoteException;
}