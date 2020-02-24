import java.rmi.*;
import java.util.ArrayList;

public interface RemoteMethodsInterface extends Remote {
    public int registry(String nAddr, ArrayList<String> fileNames, String nDir, int nPort) throws RemoteException;
    public int search(String fileName, int myId) throws RemoteException;
    public void logOff(int id) throws RemoteException;
    public int getPort(int id) throws RemoteException;
    public String getIp(int id) throws RemoteException;
}