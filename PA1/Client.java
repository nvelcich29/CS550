import java.rmi.*;
import java.io.IOException;
import java.util.Scanner;
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
        
        Scanner in = new Scanner(System.in);
        int id=0;
        System.out.println("This machine has been registered by the server. Please enter a file name to search for.");
        System.out.print(">");
        String searchFile = in.nextLine();

        try{
            Object o = Naming.lookup("rmi://localhost/Remo");

            RemoteMethodsInterface rM = (RemoteMethodsInterface) o;

            id=rM.search(searchFile);
        }
        catch(RemoteException ex){
            System.err.println("Remote object threw exception "+ ex);
        }
        catch(NotBoundException ex){
            System.err.println("Could not find the requested remote object on the server");
        }
        System.out.println("The server returned:"+ id);

        in.close();
        //socket.close();
    }

}
