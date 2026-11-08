package file.io;

import file.EasyFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class EasyFileReader {
    public static String read(EasyFile file){
        try {
            BufferedReader f = new BufferedReader(new FileReader(file));
            StringBuilder s = new StringBuilder();
            String s1;
            while(true){
                s1 = f.readLine();
                if(s1!=null) s.append(s1); else break;;
            }
            f.close();
            return s.toString();
        }catch (Exception e){
            return "";
        }
    }
    public static byte[] readBytes(File file){
        try {
            BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
            byte[] b = f.readAllBytes();
            f.close();
            return b;
        }catch (Exception e){
            return null;
        }
    }
    public static BufferedImage readImage(File f){
        try {
            return ImageIO.read(f);
        }catch (Exception ignored){
            return null;
        }
    }
}
