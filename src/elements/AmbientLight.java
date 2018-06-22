package elements;

import primitives.Color;

/**
 * class AmbientLigt.
 * determine the ambient light of a scene.
 */
public class AmbientLight extends Light {

    //protected Color color;
    protected double Ka;

    /*************constructors**********************/

    /**
     * default constructor.
     */
    public AmbientLight() {
        super.color = new Color(255, 255, 255);
        Ka = 1.0;
    }

    /**
     * construct AmbientLight with user arguments
     *
     * @param myColor
     * @param myKa
     */
    public AmbientLight(Color myColor, double myKa) {

        super.color = myColor;
        if (myKa > 1.0 || myKa < 0.0)
            throw new ExceptionInInitializerError("scaling is between: (0.0,1.0)");

        Ka = myKa;
    }

    /*************operations**********************/
    /**
     * return copy of the AmbientLight object scaled by the Ka factor.
     *
     * @return Color
     */
    public Color getIntensity() {
        Color temp = new Color(super.color);
        temp.scale(Ka);
        return temp;
    }

    /*************getters/setters**********************/
    /**
     * setter
     *
     * @param color
     */
    public void setColor(Color color) {
        super.color = color;
    }

    /**
     * setter
     *
     * @param ka
     */
    public void setKa(double ka) {
        Ka = ka;
    }

    /**
     * getter
     *
     * @return the object's color
     */
    public Color getColor() {
        return super.color;
    }

    /**
     * getter
     *
     * @return the object's scaling factor
     */
    public double getKa() {
        return Ka;
    }
}
