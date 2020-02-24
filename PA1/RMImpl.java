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
        
        //This checks to see if the client has allready been registered to the server by checking the Ip Address and port number.  
        Boolean key = true;
        int id=-1;
        if(!peers.isEmpty()){
            for(int i=0;i<peers.size();i++){
                if(peers.get(i).getAddr().equals(nAddr) && peers.get(i).getPort()==nPort){
                    key = false;
                    id = peers.get(i).getId();
                    peers.get(i).setFiles(fileNames);
                }
            }
        }
        //if there isn't a match for IP and port then this code runs to create a new peer and add it to the ArrayList
        if(key){
            Peer p = new Peer(nAddr, fileNames, nDir, nPort);
            peers.add(p);
            id = p.getId();
        }
        System.out.println("Number of registered machines:" + peers.size());

        return id;
        
        
    }

    //implementation of the search RMI
    public int search(String fileName, int myId) throws RemoteException{
        int r=-1;
        for(int i=0; i<peers.size();i++){
            for(int k=0; k<((peers.get(i)).getFiles()).size(); k++){
                
                if((peers.get(i).getFile(k)).equals(fileName)){
                    if(peers.get(i).getId()==myId){
                        r=-2;
                        break;
                    }
                    else{
                        r=(peers.get(i)).getId();
                    }
                }
            }
        }
        
        return r;
        
    }

    public int getPort(int id) throws RemoteException{
        return (peers.get(id)).getPort();
    }
    public String getIp(int id) throws RemoteException{
        return (peers.get(id)).getAddr();
    }

    public void logOff(int id) throws RemoteException{
        //System.out.println("logOff ID:"+id);
        peers.remove(id);
        //System.out.println("isempty "+peers.isEmpty());
        if(peers.isEmpty()){
            Peer.setCount(0);
        }
        else{
            int newCount=0;
            //System.out.println("peers.size():"+peers.size()+"\npeers.get(0).getId():"+peers.get(0).getId());
            for(int i = id ; i<peers.size();i++){
                //System.out.println("This is i:"+i);
                peers.get(i).setId(i);
                newCount=i;
                
             }
            Peer.setCount(newCount+1);
        }
        //System.out.println("peers.size():"+peers.size()+"\npeers.get(0).getId():"+peers.get(0).getId());
        System.out.println("The number of users at logOff() are:"+peers.size());
        return;
    }
}