package external;

import org.codehaus.groovy.ant.Groovy;

import java.awt.*;
import java.net.URI;

public class ExternalProgramManager {
    public Desktop dt;

    public ExternalProgramManager(){
        dt = Desktop.getDesktop();
        try {
            dt.browse(URI.create(""));
        }catch (Exception ignored){}
    }

    public boolean loadWebsite(String url){
        try {
            dt.browse(URI.create(url));
            return true;
        }catch (Exception ignored){
            return false;
        }
    }


    public static void main(String[] args){
        ExternalProgramManager e = new ExternalProgramManager();
        
    }
}
