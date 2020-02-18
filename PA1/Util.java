import java.util.ArrayList;
import java.io.File;

public class Util{

    public static ArrayList<String> getDirFiles(final File dir){
        ArrayList<String> fileNames=new ArrayList<String>();

        for(final File fileEntry : dir.listFiles()){
            fileNames.add(fileEntry.getName());
        }

        return fileNames;
    }
}