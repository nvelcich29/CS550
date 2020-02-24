import java.util.ArrayList;

public class Peer{
    private int id;
    private static int count=0;
    private ArrayList<String> files= new ArrayList<String>();
    private String addr;
    private int port;
    private String directory;


    public Peer(String newAddr, ArrayList<String> nFiles, String nDir, int nPort){
        setId(count++);
        //System.out.println("size of nFiles is:"+nFiles.size());
        for(int i=0; i<nFiles.size();i++){
            files.add(nFiles.get(i));
        }
        addr = newAddr;
        directory = nDir;
        port = nPort;
        //System.out.println(files.get(0));
        System.out.println("ID is:"+this.id);
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

    public void setId(int jobId){
        this.id=jobId;
    }
    public static void setCount(int nCount){
        count = nCount;
    }

    public void setFiles(ArrayList<String> nFiles){
        for(int i=0; i<nFiles.size();i++){
            if(i<files.size()){
                files.set(i, nFiles.get(i));
            }
            else{
                files.add(nFiles.get(i));
            }
        }
    }
}