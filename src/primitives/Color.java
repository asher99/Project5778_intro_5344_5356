package primitives;

/**
 * class Color represents color in RGB.
 */
public class Color {

    java.awt.Color color;

    // ***************** Constructor ********************** //

    /**
     * Construt new Color object and set the default value to (255,255,255) = Black.
     */
    public Color(){
        color = new java.awt.Color(255,255,255);
    }

    /**
     * Construt new Color object and set the color by user integer RGB values (0,255).
     * @param r - red integer index
     * @param g - green integer index
     * @param b - blue integer index
     */
    public Color(int r, int g, int b){
        color = new java.awt.Color(r,g,b);
    }

    /**
     * Construt new Color object and set the color by user float RGB values (0.0,1.0).
     * @param r - red float index
     * @param g - green float index
     * @param b - blue float index
     */
    public Color(float r, float g, float b){
        color = new java.awt.Color(r,g,b);
    }


    // ***************** Getters ********************** //
    public java.awt.Color getColor() {
        return color;
    }

    // ***************** Operations ******************** //

    /**
     * recieve other color(s) and fuse them to one Color.
     * @param otherColors
     */
    public void add(Color... otherColors){}

    /**
     * scale an Color object by factor.
     * @param factor
     */
    public void scale(double factor){
    }

    /**
     * reduce the values of RGB color by a value sent by the user.
     * @param reducer
     */
    public void reduce(double reducer){}

    public java.awt.Color setBoundaries (){
        return null;
    }
}
