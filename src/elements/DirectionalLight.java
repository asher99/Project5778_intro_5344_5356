package elements;

import primitives.*;

/**
 * class DirectionalLight for a LightSource in the infinity and beyond.
 * had only direction.
 */
public class DirectionalLight extends Light implements LightSource{

    Vector direction;


    /**
     * @return Color of a point
     */
    @Override
    public Color getIntensity(Point3D p){return null;};

    /**
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point3D p){return null;};

    /**
     * @param p
     * @return Vector
     */
    @Override
    public Vector getD(Point3D p){return null;};
}
