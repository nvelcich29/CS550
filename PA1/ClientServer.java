import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer implements Runnable {
    int port;
    String dir;
    private volatile boolean exit = false;

    public ClientServer(int nPort, String nDir){
        port = nPort;
        dir=nDir;
        
        return;
    }
    public void run(){
        //System.out.println("inside ClientServer");
        while(!exit){
            try{
                //setting up the server for listening for other clients.
                ServerSocket serverSock = new ServerSocket(port);
                //serverSock.setSoTimeout(5000);
                //System.out.println("Right before accept()");
                Socket socket=serverSock.accept();
                if(socket!=null){
                    //Gets the file name from the client looking to download that file
                    //System.out.println("clientserver:inside if");
                    InputStreamReader read=new InputStreamReader(socket.getInputStream());
                    BufferedReader in = new BufferedReader(read);
                    String file = in.readLine();
                    //System.out.println("ClientServer: after in.readLine()");                    

                    //sets up outputstream to downloading client and inputstream from the file.  Transfers file using Util.transfer()
                    //System.out.println("Before get output Stream in ClientServer");
                    OutputStream out = socket.getOutputStream();
                    InputStream fin = new FileInputStream(dir+"/"+file);
                    //System.out.print(dir+"/"+file);
                    Util.transfer(fin, out);

                    out.flush();
                    out.close();
                    fin.close();
                    socket.close();
                    serverSock.close();
                }
               
            }
            catch(IOException e){
                //System.err.println("ClientServer IOException: "+e);
            }
        }      
        
        return;
    }

    public void stop() throws IOException{
        exit=true;
        Socket s = new Socket("localhost",port);
        s.close();
    }
}