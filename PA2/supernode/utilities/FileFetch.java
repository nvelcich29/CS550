package utilities;

import java.io.File;
import java.util.ArrayList;

public class FileFetch {

    public static ArrayList<String> fetchFiles(final File dir){
        ArrayList<String> fileNames=new ArrayList<String>();

        for(final File fileEntry : dir.listFiles()){
            fileNames.add(fileEntry.getName());
        }

        return fileNames;
    }
}