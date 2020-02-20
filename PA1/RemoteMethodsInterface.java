import java.rmi.*;
import java.util.ArrayList;

public interface RemoteMethodsInterface extends Remote {
    public int registry(String nAddr, ArrayList<String> fileNames, String nDir, int nPort) throws RemoteException;
    public String search(String fileName) throws RemoteException;
    public void logOff(int id) throws RemoteException;
}