//import java.io.DataInputStream;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.net.*;
import java.rmi.*;


public class Server extends Thread{

    public static void main(String [] args) throws IOException{
        try{
            RMImpl r= new RMImpl();
            Naming.rebind("Remo",r);
            System.out.println("RMI Server Ready!");
        }
        catch (RemoteException rex){
            System.out.println("Exception in RMImpl.main: " + rex);
        }
        catch (MalformedURLException ex){
            System.out.println("MalformedURLException " + ex);
        }

        ServerSocket serverSock = new ServerSocket(15123);
        Socket socket=serverSock.accept();

        System.out.println("Accepted connection: " + socket);

        socket.close();
        serverSock.close();
    }
        
}