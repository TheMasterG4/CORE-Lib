package image.imageprocessing;

import file.io.EasyFileWriter;
import file.pathing.FilePathBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.*;

import static file.io.EasyFileReader.readImage;

public class EasyImage {
    public final BufferedImage image;
    public final int x;
    public final int y;

    public EasyImage(String s) {
        image = readImage(new File(s));
        x = image.getWidth();
        y = image.getHeight();
    }
    public EasyImage(File f) {
        image = readImage(f);
        x = image.getWidth();
        y = image.getHeight();
    }
    public EasyImage(FilePathBuilder f) {
        image = readImage(new File(f.toString()));
        x = image.getWidth();
        y = image.getHeight();
    }
    public EasyImage(BufferedImage b) {
        image = b;
        x = image.getWidth();
        y = image.getHeight();
    }

    @Deprecated
    public void display(){
        //This method is for testing, displaying an image.
        ImageIcon icon = new ImageIcon(image);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(icon));
        frame.pack();
        frame.setVisible(true);
    }
    @Deprecated
    public void disp(){
        //This method is for testing, displaying an image.
        ImageIcon icon = new ImageIcon(image);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(icon));
        frame.pack();
        frame.setVisible(true);
    }


    public EasyImage greyScaled() {
        BufferedImage result = new BufferedImage(
                x,
                y,
                BufferedImage.TYPE_INT_ARGB
        );

        for (int y1 = 0; y1 < y; y1++) {
            for (int x1 = 0; x1 < x; x1++) {
                int argb = image.getRGB(x1, y1);

                int alpha = (argb >>> 24) & 0xFF;
                int red   = (argb >>> 16) & 0xFF;
                int green = (argb >>> 8)  & 0xFF;
                int blue  = argb & 0xFF;

                int gray = (int) Math.round(
                        0.2126 * red +
                                0.7152 * green +
                                0.0722 * blue
                );

                int grayscale = (alpha << 24)
                        | (gray << 16)
                        | (gray << 8)
                        | gray;

                result.setRGB(x1, y1, grayscale);
            }
        }

        return new EasyImage(result);
    }
    public EasyImage greyScaledLL() {
        /// Linear Light approach
        int width = x;
        int height = y;
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);
                int alpha = (argb >>> 24) & 0xFF;
                int red   = (argb >>> 16) & 0xFF;
                int green = (argb >>> 8)  & 0xFF;
                int blue  = argb & 0xFF;
                double r = s(red / 255.0);
                double g = s(green / 255.0);
                double b = s(blue / 255.0);
                double luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                int gray = (int) Math.round(l(luminance) * 255.0);
                int grayscale = (alpha << 24) | (gray << 16) | (gray << 8) | gray;
                result.setRGB(x, y, grayscale);
            }
        }

        return new EasyImage(result);
    }
    public EasyImage greyScaledBright() {
        int width = x;
        int height = y;
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        double[][] luminances = new double[height][width];
        double maxLuminance = 0.0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);
                int red   = (argb >>> 16) & 0xFF;
                int green = (argb >>> 8)  & 0xFF;
                int blue  = argb & 0xFF;
                double r = s(red / 255.0);
                double g = s(green / 255.0);
                double b = s(blue / 255.0);
                double luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                luminances[y][x] = luminance;
                if (luminance > maxLuminance) {
                    maxLuminance = luminance;
                }
            }
        }
        if (maxLuminance == 0.0) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int alpha = (image.getRGB(x, y) >>> 24) & 0xFF;
                    result.setRGB(x, y, alpha << 24);
                }
            }
            return new EasyImage(result);
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int alpha = (image.getRGB(x, y) >>> 24) & 0xFF;
                double normalized = luminances[y][x] / maxLuminance;
                int gray = (int) Math.round(l(normalized) * 255.0);
                int argb = (alpha << 24) | (gray << 16) | (gray << 8) | gray;
                result.setRGB(x, y, argb);
            }
        }

        return new EasyImage(result);
    }
    public EasyImage tint(Color tint) {
        int width = x;
        int height = y;

        BufferedImage result = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_ARGB
        );

        int tr = tint.getRed();
        int tg = tint.getGreen();
        int tb = tint.getBlue();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);

                int alpha = (argb >>> 24) & 0xFF;
                int red   = (argb >>> 16) & 0xFF;
                int green = (argb >>> 8)  & 0xFF;
                int blue  = argb & 0xFF;
                double brightness = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
                int r = (int) Math.round(tr * brightness / 255.0);
                int g = (int) Math.round(tg * brightness / 255.0);
                int b = (int) Math.round(tb * brightness / 255.0);

                int tinted = (alpha << 24) | (r << 16) | (g << 8) | b;

                result.setRGB(x, y, tinted);
            }
        }

        return new EasyImage(result);
    }

    public EasyImage tint(Color tintColor, float alpha) {
        if (alpha < 0f || alpha > 1f) {
            return this;
        }
        BufferedImage tintedImage = new BufferedImage(
                x,
                y,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = tintedImage.createGraphics();
        try {
            g.drawImage(image, 0, 0, null);
            g.setComposite(AlphaComposite.SrcAtop.derive(alpha));
            g.setColor(tintColor);
            g.fillRect(0, 0, x, y);
        } finally {
            g.dispose();
        }

        return new EasyImage(tintedImage);
    }

    public EasyImage brighten(float brightnessFactor) {
        RescaleOp op = new RescaleOp(brightnessFactor, 0, null);
        return new EasyImage(op.filter(image, null));
    }
    public Color[] getColors() {
        Set<Color> colors = new HashSet<>();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);
                int alpha = (argb >>> 24) & 0xFF;

                if (alpha == 0) continue;
                colors.add(new Color(image.getRGB(x, y), true));
            }
        }
        return colors.toArray(new Color[0]);
    }


    /// Privates shhhhhh
    private double s(double value) {
        return value <= 0.04045
                ? value / 12.92
                : Math.pow((value + 0.055) / 1.055, 2.4);
    }
    private double l(double value) {
        return value <= 0.0031308
                ? 12.92 * value
                : 1.055 * Math.pow(value, 1.0 / 2.4) - 0.055;
    }

    public void export(String path){
        EasyFileWriter.write(image,path);
    }
    public void export(String... path){
        EasyFileWriter.write(image,FilePathBuilder.buildPath(path));
    }

}
