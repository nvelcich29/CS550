package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParse {
    

    public ArrayList<String> parse(String filePath){
        ArrayList<String> in=new ArrayList<String>();
        try{
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            while(sc.hasNext()){
                in.add(sc.next());
            }

            sc.close();  
            
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }

        return in;
    }
}