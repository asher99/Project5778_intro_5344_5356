package elements;

import primitives.*;

/**
 * interface that represents all kinds of light sources.
 */
public interface LightSource {
    /**
     * @return Color of a point
     */
    public Color getIntensity();

    /**
     * @param p
     * @return Vector
     */
    public Vector getL(Point3D p);

    /**
     * @param p
     * @return Vector
     */
    public Vector getD(Point3D p);

}
