package primitives;

/**
 * class Color represents color in RGB.
 * Wrapper class based on Class java.awt.Color
 */
public class Color {

    java.awt.Color color;

    // ***************** Constructor ********************** //

    /**
     * Construt new Color object and set the default value to (255,255,255) = Black.
     */
    public Color() {
        color = new java.awt.Color(0, 0, 0);
    }

    /**
     * Construt new Color object and set the color by user integer RGB values (0,255).
     *
     * @param r - red integer index
     * @param g - green integer index
     * @param b - blue integer index
     */
    public Color(int r, int g, int b) {

        // corrects overflows
        int[] RGB = setBoundaries(r, g, b);

        // set this Color to this sum of colors.
        color = new java.awt.Color(RGB[0], RGB[1], RGB[2]);

    }

    /**
     * copy ctor
     *
     * @param myColor
     */
    public Color(Color myColor) {
        this.color = new java.awt.Color(
                myColor.color.getRed(),
                myColor.color.getGreen(),
                myColor.color.getBlue());
    }


    /**
     * Construt new Color object and set the color by user float RGB values (0.0,1.0).
     *
     * @param r - red float index
     * @param g - green float index
     * @param b - blue float index
     */
    public Color(float r, float g, float b) {

        if (r > 1.0 || r < 0.0 || g > 1.0 || g < 0.0 || b > 1.0 || b < 0.0)
            throw new ExceptionInInitializerError("one or more value is ilegal. note that all values should be in range: 0.0 - 1.0");

        else color = new java.awt.Color(r, g, b);
    }


    // ***************** Getters ********************** //
    public java.awt.Color getColor() {
        return color;
    }

    // ***************** Operations ******************** //

    /**
     * recieve other color(s) and fuse them to one Color.
     *
     * @param otherColors
     */
    public void add(Color... otherColors) {

        // make sure there are no empty objects
        for (Color c :
                otherColors) {
            if (c == null || c.getColor() == null)
                throw new NullPointerException("Unintialized color object");
        }

        int red = color.getRed(), green = color.getGreen(), blue = color.getBlue();

        // sum colors RGBs.
        for (Color c :
                otherColors) {
            red += c.getColor().getRed();
            green += c.getColor().getGreen();
            blue += c.getColor().getBlue();
        }

        // corrects overflows
        int[] RGB = setBoundaries(red, green, blue);

        // set this Color to this sum of colors.
        color = new java.awt.Color(RGB[0], RGB[1], RGB[2]);
    }

    /**
     * scale an Color object by factor.
     *
     * @param factor
     */
    public void scale(double factor) {

        int red = color.getRed(), green = color.getGreen(), blue = color.getBlue();
        red *= factor;
        green *= factor;
        blue *= factor;

        // corrects overflows
        int[] RGB = setBoundaries(red, green, blue);

        // set this Color to this sum of colors.
        color = new java.awt.Color(RGB[0], RGB[1], RGB[2]);
    }

    /**
     * reduce the values of RGB color by a value sent by the user.
     *
     * @param reducer
     */
    public void reduce(double reducer) {

        int red = color.getRed(), green = color.getGreen(), blue = color.getBlue();
        red -= reducer;
        green -= reducer;
        blue -= reducer;

        // corrects overflows
        int[] RGB = setBoundaries(red, green, blue);

        // set this Color to this sum of colors.
        color = new java.awt.Color(RGB[0], RGB[1], RGB[2]);
    }

    @Override
    public boolean equals(Object other) {

        Color otherCast = (Color) other;
        return otherCast.color.equals(this.color);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return color.toString();
    }

    /**
     * receive RGB values and corrects overflows over 255 and underflows over 0.
     *
     * @param r
     * @param g
     * @param b
     * @return
     */
    public int[] setBoundaries(int r, int g, int b) {

        int[] finalRGB = new int[3];

        finalRGB[0] = setBoundary(r);
        finalRGB[1] = setBoundary(g);
        finalRGB[2] = setBoundary(b);

        return finalRGB;
    }

    /**
     * receive RGB value and corrects overflows over 255 and underflows over 0.
     *
     * @param rgb
     * @return
     */
    public int setBoundary(int rgb) {

        if (rgb < 0)
            rgb = 0;
        else if (rgb > 255)
            rgb = 255;

        return rgb;
    }

}
