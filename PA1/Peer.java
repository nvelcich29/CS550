import java.util.ArrayList;

public class Peer{
    private int id = 0;
    private ArrayList<String> files= new ArrayList<String>();
    private String addr;
    private int port;
    private String directory;


    public Peer(String newAddr, ArrayList<String> nFiles, String nDir, int nPort){
        id=id++;
        for(int i=0; i<nFiles.size();i++){
            files.add(nFiles.get(i));
        }
        addr = newAddr;
        directory = nDir;
        port = nPort;
        System.out.println(files.get(0));
    }
    public int getId(){
        return id;
    }

    public String getFile(int index){
        return files.get(index);
    }

    public ArrayList<String> getFiles(){
        return files;
    }
    public void addFile(String nFile){
        files.add(nFile);
    }

    public int getPort(){
        return port;
    }

    public String getDir(){
        return directory;
    }

    public String getAddr(){
        return addr;
    }
}