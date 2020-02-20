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
    public int registry(String nAddr, ArrayList<String> fileNames, String nDir, int nPort) throws RemoteException{
        
        //This checks to see if the client has allready been registered to the server by checking the Ip Address.  
        Boolean key = true;
        int id=-1;
        for(int i=0;i<peers.size();i++){
            if(peers.get(i).getAddr().equals(nAddr)){
                key = false;
                id = peers.get(i).getId();
            }
        }
        //if there isn't a match for IP then this code runs to create a new peer and add it to the ArrayList
        if(key){
            Peer p = new Peer(nAddr, fileNames, nDir, nPort);
            peers.add(p);
            id = p.getId();
        }
        System.out.println("Number of registered machines:" + peers.size());

        return id;
        
        
    }

    //implementation of the search RMI
    public String search(String fileName) throws RemoteException{
        String r="NONE";
        for(int i=0; i<peers.size();i++){
            for(int k=0; k<((peers.get(i)).getFiles()).size(); k++){
                
                if((peers.get(i).getFile(k)).equals(fileName)){
                    r=(peers.get(i).getAddr());
                }
            }
        }
        return r;
    }

    public void logOff(int id) throws RemoteException{
        peers.remove(id);
        System.out.println("The number of users at logOff are:"+peers.size());
        return;
    }
}