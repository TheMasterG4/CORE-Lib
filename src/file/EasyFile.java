package file;

import file.pathing.FilePathBuilder;
import org.jetbrains.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static core.CORE.fileSeparator;

public abstract class EasyFile<Data> extends File implements EncryptableFile<Data>{
    public Data content = null;
    public EasyFile(@NotNull String pathname) {
        super(pathname);
    }

    public EasyFile(String parent, @NotNull String child) {
        super(parent, child);
    }

    public EasyFile(File parent, @NotNull String child) {
        super(parent, child);
    }

    public EasyFile(@NotNull URI uri) {
        super(uri);
    }
    public EasyFile (@NotNull String... path){
        super(FilePathBuilder.bp(path));
    }
    public EasyFile (@NotNull StringBuilder filePathBuilderStringBuilder){
        super(FilePathBuilder.bp(filePathBuilderStringBuilder.toString()));
    }
    public EasyFile(@NotNull File f){
        super(f.getAbsolutePath());
    }

    @Override
    public @NotNull final String getName(){
        if(isDirectory()){
            return super.getName();
        }else{
            String s = super.getName();
            String[] s1 = s.split("\\.");
            s1[s1.length-1] = "";
            return String.join("",s1);
        }
    }
    public @NotNull final String getExtension(){
        if(isDirectory()){
            return "";
        }else{
            String[] s = super.getName().split("\\.");
            return s[s.length-1];
        }
    }

    public @NotNull final String name(){
        if(isDirectory()){
            return super.getName();
        }else{
            String s = super.getName();
            String[] s1 = s.split("\\.");
            s1[s1.length-1] = "";
            return String.join("",s1);
        }
    }
    public @NotNull final String extension(){
        if(isDirectory()){
            return "";
        }else{
            String[] s = super.getName().split("\\.");
            return s[s.length-1];
        }
    }

    public @NotNull final String n(){
        if(isDirectory()){
            return super.getName();
        }else{
            String s = super.getName();
            String[] s1 = s.split("\\.");
            s1[s1.length-1] = "";
            return String.join("",s1);
        }
    }
    public @NotNull final String e(){
        if(isDirectory()){
            return "";
        }else{
            String[] s = super.getName().split("\\.");
            return s[s.length-1];
        }
    }

    public final String getNameFull(){
        return getParentFile().getAbsolutePath() + fileSeparator + getName();
    }
    public final void move(EasyFile<Data> output) {
        try {
            Files.walk(toPath())
                    .forEach(path -> {
                        Path destination = output.toPath().resolve(toPath().relativize(path));
                        try {
                            Files.move(path, destination, StandardCopyOption.ATOMIC_MOVE);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception ignored) {
        }
    }
    public final void m(EasyFile<Data> output) {
        try {
            Files.walk(toPath())
                    .forEach(path -> {
                        Path destination = output.toPath().resolve(toPath().relativize(path));
                        try {
                            Files.move(path, destination, StandardCopyOption.ATOMIC_MOVE);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception ignored) {
        }
    }

    public abstract @Nullable Data read();
    public abstract void write(Data d);
    public void write(EasyFile<Data> d){
        write(d.content);
    }
    public void write(){
        write(content);
    }


    public void extract(EasyFile<?> to){
        
    }

    public String getContentType(){
        try{
            return Files.probeContentType(toPath());
        }catch (Exception ignored){
            return "Unknown.";
        }
    }


    public static void testContentType(String path){
        try{
            System.out.println( Files.probeContentType(Path.of(path)));
        }catch (Exception ignored){
        }
    }
}
