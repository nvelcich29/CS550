import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;


public class RMImpl extends UnicastRemoteObject implements RemoteMethodsInterface{
    
    /**
     *this was added using the quick fix
     */
    private static final long serialVersionUID = 1L;

    public RMImpl() throws RemoteException {
        super();
    }

    //implementation of the registry RMI
    public void registry(int pid, String fileName) throws RemoteException{
        System.out.println("pid:"+pid+" file name:"+fileName);
    }

    //implementation of the search RMI
    public int search(String fileName) throws RemoteException{
        System.out.println("searching for:"+fileName);
        return 1;
    }
}