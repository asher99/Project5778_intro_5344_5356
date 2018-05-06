package elements;

import primitives.Color;

/**
 * abstract class Light for all types of light implementation.
 */
public abstract class Light {

    Color color;

    /**
     * this method implemented differently in any extending class.
     * @return the color after calculating its factor.
     */
    public Color getIntensity(){return null;}
}
