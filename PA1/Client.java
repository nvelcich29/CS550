import java.rmi.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
//import java.net.*;
import java.io.FileOutputStream;

public class Client{

    public static void main(String [] args)throws IOException{
        //Socket socket = new Socket("127.0.0.1",15123);
        int myId=-1;
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

        //Taking all the file names from the directory given from cmd line
        ArrayList<String> fileNames = Util.getDirFiles(file);
        //System.out.println(fileNames);
        //getting the IP address for this running program.  
        String addr = InetAddress.getLocalHost().getHostAddress();

        //Registering with the server.  
        try{
            Object o = Naming.lookup("rmi://localhost/Remo");

            RemoteMethodsInterface rM = (RemoteMethodsInterface) o;

            myId=rM.registry(addr, fileNames, dir, port);

            //System.out.println("Your system information:"+addr+", "+dir+", "+port);
        }
        catch(RemoteException ex){
            System.err.println("Remote object threw exception "+ ex);
        }
        catch(NotBoundException ex){
            System.err.println("Could not find the requested remote object on the server");
        }
        
        //This starts the thread that waits for other clients to connect
        ClientServer cs = new ClientServer(port, dir);
        Thread t = new Thread(cs);
        t.start();

        //Outputs to terminal and requests a file name to search for
        Scanner in = new Scanner(System.in);
        System.out.println("This machine has been registered by the server. Please enter a file name to search for or enter 'Q' to quit.");
        System.out.print(">");
        String searchFile = in.nextLine();

        int fileId=-1;
        String uIn=null;
        while(!(searchFile.equals("Q"))){

            try{
                Object o = Naming.lookup("rmi://localhost/Remo");

                RemoteMethodsInterface rM = (RemoteMethodsInterface) o;

                fileId=rM.search(searchFile, myId);
                //System.out.println(fileId);
            }
            catch(RemoteException ex){
                System.err.println("Remote object threw exception "+ ex);
            }
            catch(NotBoundException ex){
                System.err.println("Could not find the requested remote object on the server");
            }

            if(fileId>=0){
                System.out.println("The file you are searching for is available.  Would you like to download (y,n)?");
                System.out.print(">");

                uIn=in.nextLine();

                //if user decides to download the searched for and found file.  
                if(uIn.equals("y")){
                    System.out.println("Downloading");
                    String ipAddr = null;
                    int nPort=0;
                    String nFile=dir+"/"+searchFile;
                    try{
                        Object o = Naming.lookup("rmi://localhost/Remo");

                        RemoteMethodsInterface rM = (RemoteMethodsInterface) o;

                        ipAddr=rM.getIp(fileId);
                        nPort=rM.getPort(fileId);
                        OutputStream out = new FileOutputStream(nFile);
                         

                        Util.download(ipAddr,nPort, out, searchFile);

                        out.close();

                    }
                    catch(RemoteException ex){
                        System.err.println("Remote object threw exception "+ ex);
                    }
                    catch(NotBoundException ex){
                        System.err.println("Could not find the requested remote object on the server.");
                    }
                }
                //if user declines to download a found file.
                else{
                    System.out.println("Ok, the file will not be downloaded.");
                }
            }
            else if(fileId==-1){
                System.out.println("Sorry, the file is unavailable at this time.");
            }
            else if(fileId==-2){
                System.out.println("This file is allready in your file directory.");
            }     

                //Update myId incase id on server has changed.   
            try{
                Object o = Naming.lookup("rmi://localhost/Remo");

                RemoteMethodsInterface rM = (RemoteMethodsInterface) o;
                //Taking all the file names from the directory given from cmd line
                fileNames = Util.getDirFiles(file);
                myId=rM.registry(addr, fileNames, dir, port);
            }
            catch(RemoteException ex){
                System.err.println("Remote object threw exception "+ ex);
            }
            catch(NotBoundException ex){
                System.err.println("Could not find the requested remote object on the server");
            }
                //Prompt for the next loop
                System.out.print("Type another file to search for or type 'Q' to quit.\n>");
                searchFile=in.nextLine();
        }

        //Update myId incase id on server has changed.  
        try{
            Object o = Naming.lookup("rmi://localhost/Remo");

            RemoteMethodsInterface rM = (RemoteMethodsInterface) o;
            
            myId=rM.registry(addr, fileNames, dir, port);
            
            //System.out.println("Your system information:"+addr+", "+dir+", "+port+"id,"+myId);
        }
        catch(RemoteException ex){
            System.err.println("Remote object threw exception "+ ex);
        }
        catch(NotBoundException ex){
            System.err.println("Could not find the requested remote object on the server");
        }

        try{
            Object o = Naming.lookup("rmi://localhost/Remo");

            RemoteMethodsInterface lo = (RemoteMethodsInterface) o;

            lo.logOff(myId);
        }
        catch(RemoteException ex){
            System.err.println("Remote object threw exception "+ ex);
        }
        catch(NotBoundException ex){
            System.err.println("Could not find the requested remote object on the server");
        }
        cs.stop();
        in.close();
        //socket.close();
    }

}
