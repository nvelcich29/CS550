import java.rmi.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.File;
//import java.net.*;

public class Client{

    public static void main(String [] args)throws IOException{
        //Socket socket = new Socket("127.0.0.1",15123);
        
        if(args.length != 2){
            System.out.println("ERROR: Please type 'java Client [folder] [port]' to execute this program correctly.");
            return;
        }
        
        //Verifying that the port number entered is a valid one.  
        int port;
        try{
            port=Integer.parseInt(args[1]);
        }
        catch(Exception e){
            System.out.println("Please enter a valid port number between 1024-65534.");
            return;
        }
        if(port<1024 || port>65534){
            System.out.println("Please enter a valid port number between 1024-65534.");
            return;
        }

        //Verifying that the directory name entered is a valid one.  
        String dir = args[0];
        File file = new File(dir);
        if(!file.isDirectory()){
            System.out.println("Please enter a valid directory.");
            return;
        }

        //getting the IP address for this running program.  
        String addr = InetAddress.getLocalHost().getHostAddress();

        //Registering with the server.  
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
