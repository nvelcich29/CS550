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

    //This is how the server keeps a record of clients registered to the server.  
    ArrayList<Peer> peers = new ArrayList<Peer>();

    //implementation of the registry RMI
    public void registry(String nAddr, ArrayList<String> fileNames, String nDir, int nPort) throws RemoteException{
        
        //This checks to see if the client has allready been registered to the server by checking the Ip Address.  
        Boolean key = true;
        for(int i=0;i<peers.size();i++){
            if(peers.get(i).getAddr().equals(nAddr)){
                key = false;
            }
        }
        if(key){
            Peer p = new Peer(nAddr, fileNames, nDir, nPort);
            peers.add(p);
        }
        System.out.println("Number of registered machines:" + peers.size());
        
    }

    //implementation of the search RMI
    public int search(String fileName) throws RemoteException{
        for(int i=0; i<peers.size();i++){
            for(int k=0; k<((peers.get(i)).getFiles()).size(); k++){
                if((peers.get(i).getFile(k)).equals(fileName)){
                    return i;
                }
            }
        }
        return -1;
    }
}