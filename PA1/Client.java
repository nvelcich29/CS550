import java.rmi.*;
import java.io.IOException;
//import java.net.*;

public class Client{

    public static void main(String [] args)throws IOException{
        //Socket socket = new Socket("127.0.0.1",15123);
        try{
            Object o = Naming.lookup("rmi://localhost/Remo");

            RemoteMethodsInterface rM = (RemoteMethodsInterface) o;

            rM.registry(2, "fileName");
        }
        catch(RemoteException ex){
            System.err.println("Remote object threw exception "+ ex);
        }
        catch(NotBoundException ex){
            System.err.println("Could not find the requested remote object on the server");
        }
        

        //socket.close();
    }

}
