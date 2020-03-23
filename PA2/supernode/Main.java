
import java.util.ArrayList;

import utilities.*;

public class Main{
    public static void main(String[] args){
        FileParse fp=new FileParse();

        String filePath="resources/config.txt";

        ArrayList<String> a=fp.parse(filePath);

        System.out.println(a);

        return;
    }
}