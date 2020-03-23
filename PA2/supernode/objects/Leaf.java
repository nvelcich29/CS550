package objects;

import java.util.ArrayList;

public class Leaf{
    String superIp;
    int superPort;
    int port;
    String ip;   
    ArrayList<String> files=new ArrayList<String>();

    public Leaf(String ns, int nsPort, int nPort, String nIp, ArrayList<String> nFiles){
        this.superIp=ns;
        this.superPort=nsPort;
        this.port=nPort;
        this.ip=nIp;
        this.files=nFiles;
    }

    public String getSuperIp() {
        return superIp;
    }

    public void setSuperIp(String superIp) {
        this.superIp = superIp;
    }

    public int getSuperPort() {
        return superPort;
    }

    public void setSuperPort(int superPort) {
        this.superPort = superPort;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}