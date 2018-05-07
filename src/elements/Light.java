package elements;

import primitives.Color;

/**
 * abstract class Light for all types of light implementation.
 */
public abstract class Light {

    Color color;

    // ***************** Constructors ********************** //

    /**
     * constructor default is white
     */
    public Light(){
        color = new Color(255,255,255);
    }

    /**
     * constructor gets the color of the light
     * @param c
     */
    public Light(Color c){
        color = c;
    }

    /**
     * this method implemented differently in any extending class.
     * @return the color after calculating its factor.
     */
    public Color getIntensity(){return null;}
}
