//import java.io.DataInputStream;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    public static void main(String [] args) throws IOException{
        ServerSocket serverSock = new ServerSocket(15123);
        Socket socket=serverSock.accept();

        System.out.println("Accepted connection: " + socket);

        socket.close();
        serverSock.close();
    }
        
}