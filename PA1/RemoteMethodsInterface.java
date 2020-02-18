import java.rmi.*;
import java.util.ArrayList;

public interface RemoteMethodsInterface extends Remote {
    public void registry(String nAddr, ArrayList<String> fileNames, String nDir, int nPort) throws RemoteException;
    public int search(String fileName) throws RemoteException;
}