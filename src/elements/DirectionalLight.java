package elements;

import primitives.*;

/**
 * class DirectionalLight for a LightSource in the infinity and beyond.
 * had only direction.
 */
public class DirectionalLight extends Light implements LightSource{

    // the direction of the light.
    Vector direction;


    /**
     * @return Color of a point
     * in directional light there is no importance to the distance of the light source from the object.
     */
    @Override
    public Color getIntensity(Point3D p){
        return super.color;
    };


    /**
     * calculate the vector from the light source to the point on the Geometry.
     * no implementation for DirectionalLight.
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point3D p){return null;};


    /**
     * return the direction of the light.
     * why it have a Point3D arg?
     * @param p
     * @return Vector
     */
    @Override
    public Vector getD(Point3D p){
        return direction;
    };
}
