package elements;

import primitives.*;

/**
 * a light that has a point
 */
public class PointLight extends Light implements LightSource {
    /**
     *
     */
   public Point3D position;

    /**
     * this factor is always equal to 1.
     * it exist to make sure the denominator of getIntensity method will be bigger than 1.
     */
   double Kc = 1;

    /**
     * linear attenuation factor.
     */
   double Ki;

    /**
     * squared attenuation factor.
     */
   double Kq;

    /**
     * @return Color of a point
     * here we need to use attenuation factor for the distance between the light source and the object.
     *
     *  Il = I0 / (Kc + Ki*d + Kq^2*d).
     */
    @Override
    public Color getIntensity(Point3D p){

        double distance = Point3D.distance(position,p);
        double denominator = Kc + distance*Ki + distance*Math.pow(Kq,2);

        Color result = new Color(super.color);
        result.scale(1/denominator);
        return  result;
    };

    /**
     * calculate the vector from the light source to the point on the Geometry.
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point3D p){
        return new Vector(position);
    };

    /**
     * return the direction of the light.
     * no implementation for PointLight
     * @param p
     * @return Vector
     */
    @Override
    public Vector getD(Point3D p){return null;};

}
