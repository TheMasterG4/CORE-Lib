package file.pathing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static core.CORE.fileSeparator;

public final class FilePathBuilder {
    public final List<String> path;

    public FilePathBuilder(){
        path = new ArrayList<>();
    }
    public FilePathBuilder(String starterFile){
        path = new ArrayList<>();
        path.add(starterFile);
    }
    public FilePathBuilder(String... newPath){
        path = new ArrayList<>();
        for(int x = 0; x<newPath.length-1; x++){
            path.add(newPath[x]); path.add(fileSeparator);
        }
        path.add(newPath[newPath.length-1]);
    }

    /// Statics so don't need to list everytime
    public static String buildPath(String... directories){
        StringBuilder construct = new StringBuilder();
        for(int x = 0; x<directories.length-1; x++) construct.append(directories[x]).append(fileSeparator);
        construct.append(directories[directories.length-1]);
        return construct.toString();
    }
    public static String buildPath(String path){
        String[] directories = path.split("\\\\");
        StringBuilder construct = new StringBuilder();
        for(int x = 0; x<directories.length-1; x++) construct.append(directories[x]).append(fileSeparator);
        construct.append(directories[directories.length-1]);
        return construct.toString();
    }
    public static String bp(String... directories){
        StringBuilder construct = new StringBuilder();
        for(int x = 0; x<directories.length-1; x++) construct.append(directories[x]).append(fileSeparator);
        construct.append(directories[directories.length-1]);
        return construct.toString();
    }
    public static String bp(String path){
        String[] directories = path.split("\\\\");
        StringBuilder construct = new StringBuilder();
        for(int x = 0; x<directories.length-1; x++) construct.append(directories[x]).append(fileSeparator);
        construct.append(directories[directories.length-1]);
        return construct.toString();
    }
    public static String subfolder(String folder, String... subfolder){
        List<String> l = List.of(subfolder); l.addFirst(folder);
        return bp(l.toArray(new String[0]));
    }
    public static String sf(String folder, String... subfolder){
        List<String> l = new ArrayList<>(); l.add(folder); l.addAll(List.of(subfolder));
        return bp(l.toArray(new String[0]));
    }


    /// Same method :p
    public void append(String s){
        if(path.isEmpty()){
            path.add(s);
        }else{
            if(path.getLast().equals(fileSeparator)){
                path.add(s);
            }else{
                path.add(fileSeparator);
                path.add(s);
            }
        }
    }
    public void add(String s){
        if(path.isEmpty()){
            path.add(s);
        }else{
            if(path.getLast().equals(fileSeparator)){
                path.add(s);
            }else{
                path.add(fileSeparator);
                path.add(s);
            }
        }
    }
    public void a(String s){
        if(path.isEmpty()){
            path.add(s);
        }else{
            if(path.getLast().equals(fileSeparator)){
                path.add(s);
            }else{
                path.add(fileSeparator);
                path.add(s);
            }
        }
    }

    public boolean isEmpty(){
        return path.isEmpty();
    }
    public void clear(){
        path.clear();
    }

    public String toString(){
        return String.join("",path.toArray(new String[0]));
    }
}
