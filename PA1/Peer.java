import java.util.ArrayList;

public class Peer{
    int id;
    ArrayList<String> files= new ArrayList<String>();

    public Peer(int nId, String nFile){
        id = nId;
        files.add(nFile);
    }

    public Peer(int nId, ArrayList<String> nFiles){
        id=nId;
        for(int i=0; i<nFiles.size();i++){
            files.add(nFiles.get(i));
        }

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
}