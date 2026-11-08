package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileManager {
    public static boolean move(File origin, File output) {
        try {
            Files.walk(origin.toPath())
                    .forEach(path -> {
                        Path destination = output.toPath().resolve(origin.toPath().relativize(path));
                        try {
                            Files.move(path, destination, StandardCopyOption.ATOMIC_MOVE);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getExtension(File f){
        String[] s = f.getAbsolutePath().split("\\.");
        return s[s.length-1];
    }
    public static String ge(File f){
        String[] s = f.getAbsolutePath().split("\\.");
        return s[s.length-1];
    }
}
