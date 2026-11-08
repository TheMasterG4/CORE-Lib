package image.imageprocessing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class ColorLayering {
    public static EasyImage[] getLayers(EasyImage e, Color color, int tolerance) {
        BufferedImage src = e.image;
        int w = src.getWidth(), h = src.getHeight();
        long tolSq = (long) Math.max(0, tolerance) * Math.max(0, tolerance);
        int out = color.getRGB();

        Map<Integer, Integer> counts = new HashMap<>();
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int argb = src.getRGB(x, y);
                if ((argb >>> 24) != 0) counts.merge(argb & 0xFFFFFF, 1, Integer::sum);
            }

        List<Integer> colors = new ArrayList<>(counts.keySet());
        colors.sort((a, b) -> counts.get(b) - counts.get(a));

        List<Integer> seeds = new ArrayList<>();
        Map<Integer, Integer> colorToGroup = new HashMap<>();
        for (int c : colors) {
            int g = 0;
            while (g < seeds.size() && ds(c, seeds.get(g)) > tolSq) g++;
            if (g == seeds.size()) seeds.add(c);
            colorToGroup.put(c, g);
        }

        EasyImage[] layers = new EasyImage[seeds.size()];
        Arrays.setAll(layers, i -> new EasyImage(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)));

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int argb = src.getRGB(x, y);
                if ((argb >>> 24) != 0)
                    layers[colorToGroup.get(argb & 0xFFFFFF)].image.setRGB(x, y, out);
            }
        return layers;
    }
    public static EasyImage[] gl(EasyImage e, Color color, int tolerance) {
        BufferedImage src = e.image;
        int w = src.getWidth(), h = src.getHeight();
        long tolSq = (long) Math.max(0, tolerance) * Math.max(0, tolerance);
        int out = color.getRGB();

        Map<Integer, Integer> counts = new HashMap<>();
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int argb = src.getRGB(x, y);
                if ((argb >>> 24) != 0) counts.merge(argb & 0xFFFFFF, 1, Integer::sum);
            }

        List<Integer> colors = new ArrayList<>(counts.keySet());
        colors.sort((a, b) -> counts.get(b) - counts.get(a));

        List<Integer> seeds = new ArrayList<>();
        Map<Integer, Integer> colorToGroup = new HashMap<>();
        for (int c : colors) {
            int g = 0;
            while (g < seeds.size() && ds(c, seeds.get(g)) > tolSq) g++;
            if (g == seeds.size()) seeds.add(c);
            colorToGroup.put(c, g);
        }

        EasyImage[] layers = new EasyImage[seeds.size()];
        Arrays.setAll(layers, i -> new EasyImage(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)));

        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int argb = src.getRGB(x, y);
                if ((argb >>> 24) != 0)
                    layers[colorToGroup.get(argb & 0xFFFFFF)].image.setRGB(x, y, out);
            }
        return layers;
    }





    private static long ds(int c1, int c2) {
        long dr = ((c1 >> 16) & 0xFF) - ((c2 >> 16) & 0xFF);
        long dg = ((c1 >> 8) & 0xFF) - ((c2 >> 8) & 0xFF);
        long db = (c1 & 0xFF) - (c2 & 0xFF);
        return dr * dr + dg * dg + db * db;
    }
}
