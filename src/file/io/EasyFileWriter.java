package file.io;

import file.EasyFile;
import image.imageprocessing.EasyImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

import static core.CORE.fileSeparator;

public class EasyFileWriter {
    public static boolean write(String content, File file){
        try {
            BufferedWriter f = new BufferedWriter(new FileWriter(file));
            f.write(content);
            f.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean write(String content,String path){
        try {
            BufferedWriter f = new BufferedWriter(new FileWriter(path));
            f.write(content);
            f.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean write(byte[] content, File file){
        try {
            BufferedOutputStream f = new BufferedOutputStream(new FileOutputStream(file));
            f.write(content);
            f.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean write(byte[] content,String path){
        try {
            BufferedOutputStream f = new BufferedOutputStream(new FileOutputStream(path));
            f.write(content);
            f.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean write(BufferedImage image, EasyFile<String> f){
        try {
            String[] s = f.getAbsolutePath().split(fileSeparator);
            System.out.println(Arrays.toString(s));
            ImageIO.write(image, s[s.length-1], f);
            return true;
        }catch (Exception f1){
            return false;
        }
    }
    public static boolean write(BufferedImage image, String path){
        try {
            String[] s = path.split(fileSeparator);
            ImageIO.write(image, s[s.length-1], new File(path));
            return true;
        }catch (Exception f){
            return false;
        }
    }
    public static boolean write(EasyImage e, File f){
        try {
            String[] s1 = f.getAbsolutePath().split("\\.");
            ImageIO.write(e.image, s1[s1.length-1], f);
            return true;
        }catch (Exception f1){
            return false;
        }
    }
    public static boolean write(EasyImage e, String s){
        try {
            File f = new File(s);
            String[] s1 = f.getAbsolutePath().split("\\.");
            ImageIO.write(e.image, s1[s1.length-1], f);
            return true;
        }catch (Exception f1){
            return false;
        }
    }

}
