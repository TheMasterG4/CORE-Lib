package image.colors;

import java.awt.*;

public final class EasyColor {
    public static Color getColor(String hex){
        hex = hex.trim().replaceFirst("^#", "");

        /// Confirm its real fr fr
        if (!(hex.length() == 3 || hex.length() == 6)) {
            return Color.BLACK;
        }

        /// ShortyHand
        if (hex.length() == 3) {
            hex = "" + hex.charAt(0) + hex.charAt(0) + hex.charAt(1) + hex.charAt(1) + hex.charAt(2) + hex.charAt(2);
        }

        if (!hex.matches("[0-9A-Fa-f]{6}")) {
            return Color.BLACK;
        }

        return new Color(Integer.parseInt(hex.substring(0, 2), 16),Integer.parseInt(hex.substring(2, 4), 16),Integer.parseInt(hex.substring(4, 6), 16));
    }
    public static Color gc(String hex){
        hex = hex.trim().replaceFirst("^#", "");

        /// Confirm its real fr fr
        if (!(hex.length() == 3 || hex.length() == 6)) {
            return Color.BLACK;
        }

        /// ShortyHand
        if (hex.length() == 3) {
            hex = "" + hex.charAt(0) + hex.charAt(0) + hex.charAt(1) + hex.charAt(1) + hex.charAt(2) + hex.charAt(2);
        }

        if (!hex.matches("[0-9A-Fa-f]{6}")) {
            return Color.BLACK;
        }

        return new Color(Integer.parseInt(hex.substring(0, 2), 16),Integer.parseInt(hex.substring(2, 4), 16),Integer.parseInt(hex.substring(4, 6), 16));
    }
    public static Color gc(int r, int b, int g){
        return new Color(r,b,g);
    }


    public static Color tint(Color base, Color tint, float strength) {
        strength = Math.clamp(strength, 0f, 1f);
        float mr = base.getRed()   * tint.getRed()   / 255f;
        float mg = base.getGreen() * tint.getGreen() / 255f;
        float mb = base.getBlue()  * tint.getBlue()  / 255f;
        int r = Math.round(base.getRed()   + (mr - base.getRed())   * strength);
        int g = Math.round(base.getGreen() + (mg - base.getGreen()) * strength);
        int b = Math.round(base.getBlue()  + (mb - base.getBlue())  * strength);
        return new Color(r, g, b, base.getAlpha());
    }
    private static native int tintNative(int baseArgb, int tintArgb, float strength);



    public static Color t(Color base, Color tint, float strength) {
        strength = Math.clamp(strength, 0f, 1f);
        float mr = base.getRed()   * tint.getRed()   / 255f;
        float mg = base.getGreen() * tint.getGreen() / 255f;
        float mb = base.getBlue()  * tint.getBlue()  / 255f;
        int r = Math.round(base.getRed()   + (mr - base.getRed())   * strength);
        int g = Math.round(base.getGreen() + (mg - base.getGreen()) * strength);
        int b = Math.round(base.getBlue()  + (mb - base.getBlue())  * strength);
        return new Color(r, g, b, base.getAlpha());
    }
}
