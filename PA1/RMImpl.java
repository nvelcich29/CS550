import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RMImpl extends UnicastRemoteObject implements RemoteMethodsInterface{
    
    /**
     *this was added using the quick fix
     */
    private static final long serialVersionUID = 1L;

    public RMImpl() throws RemoteException {
        super();
    }

    ArrayList<Peer> peers = new ArrayList<Peer>();

    //implementation of the registry RMI
    public void registry(int pid, String fileName) throws RemoteException{
        Boolean key = true;
        for(int i=0;i<peers.size();i++){
            if(peers.get(i).getId()==pid){
                key = false;
            }
        }
        if(key){
            Peer p = new Peer(pid, fileName);
            peers.add(p);
        }
        
    }

    //implementation of the search RMI
    public int search(String fileName) throws RemoteException{
        for(int i=0; i<peers.size();i++){
            for(int k=0; k<((peers.get(i)).getFiles()).size(); k++){
                if((peers.get(i).getFile(k)).equals(fileName)){
                    return (peers.get(i)).getId();
                }
            }
        }
        return -1;
    }
}