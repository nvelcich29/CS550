import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Util{

    public static ArrayList<String> getDirFiles(final File dir){
        ArrayList<String> fileNames=new ArrayList<String>();

        for(final File fileEntry : dir.listFiles()){
            fileNames.add(fileEntry.getName());
        }

        return fileNames;
    }

    public static void download(String ip, int port, OutputStream out, String inFile) throws IOException{
        Socket connect=new Socket(ip, port);
        OutputStreamWriter os = new OutputStreamWriter(connect.getOutputStream());
        os.write(inFile+"\n");
        os.flush();
        
        //System.out.println("download:after os.flush");

        InputStream in = connect.getInputStream();

        transfer(in,out);
        
        os.close();
        connect.close();
    }

    public static void transfer(InputStream in, OutputStream out) throws IOException{
        int byteRead = 0;
        byte[] buf = new byte[1024];
        long start = System.currentTimeMillis();
        while((byteRead=in.read(buf))!=-1){
            //System.out.println("byteRead="+byteRead);
            out.write(buf, 0, byteRead);
        }
        //System.out.println("byteRead="+byteRead);
        System.out.print("Download complete:"+(System.currentTimeMillis()-start)+" milli-seconds\n>");
    }


}