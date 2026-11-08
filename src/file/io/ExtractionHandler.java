package file.io;

import be.stef.rar.Unrar5j;
import file.FileManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ExtractionHandler {
    public static File zips = new File("src\\zips");
    public static int currentZip=1;
    public static int currentRar=1;

    public static File[] extract(File... archives){
        try {
            List<File> out = new ArrayList<>();
            for(File f: archives){
                switch (FileManager.getExtension(f)){
                    case "zip"-> out.add(unzip(f));
                    case "rar"-> out.add(unrar(f));

                    default -> {}
                }
            }
            return out.toArray(new File[0]);
        }catch (Exception e){
            return null;
        }
    }
    public static boolean purge(){
        try (Stream<Path> paths = Files.walk(new File("zips").toPath())) {
            paths.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            zips.mkdir();
            currentZip=1;
            currentRar=1;
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static File unrar(File rar){
        try{
            Unrar5j.extract(rar.getAbsolutePath(), zips+"\\rar"+currentRar, null);
            currentRar++;
            return new File(zips+"\\rar"+currentRar);
        }catch (Exception e){
            return null;
        }
    }

    public static File[] unrar(File... rar){
        List<File> files = new ArrayList();
        for(File f:rar) files.add(unrar(f));
        return files.toArray(new File[0]);
    }

    public static File unzip(File zip){
        try(BufferedInputStream fis = new BufferedInputStream(new FileInputStream(zip))){
            File dir = new File(zips+"\\zip"+(currentZip));
            currentZip++;
            if (!dir.exists()) dir.mkdirs();
            byte[] buffer = new byte[1024*1024];
            ZipInputStream z1 = new ZipInputStream(fis); {
                ZipEntry z = z1.getNextEntry();
                while (z != null) {
                    String fileName = z.getName();
                    File newFile = new File(dir + File.separator + fileName);
                    if (z.isDirectory()) {
                        new File(newFile.getParent()).mkdirs();
                    } else {
                        new File(newFile.getParent()).mkdirs();
                        try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(newFile))) {
                            int len;
                            while ((len = z1.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }
                        }
                    }
                    z1.closeEntry();
                    z = z1.getNextEntry();
                }
                z1.closeEntry();
            }
            return dir;
        }catch (Exception e){
            return null;
        }
    }

    public static File[] unzip(File... zip){
        List<File> files = new ArrayList();
        for(File f:zip) files.add(unzip(f));
        return files.toArray(new File[0]);
    }
}
