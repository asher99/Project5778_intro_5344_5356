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
     * constructor
     *
     * @param place
     * @param kc
     * @param ki
     * @param kq
     * @param color
     */
    public PointLight(Point3D place, double kc, double ki, double kq, Color color) {
        super(color);
        position = place;
        Kc = kc;
        Ki = ki;
        Kq = kq;
    }

    /**
     * @return Color of a point
     * here we need to use attenuation factor for the distance between the light source and the object.
     * <p>
     * Il = I0 / (Kc + Ki*d + Kq^2*d).
     */
    @Override
    public Color getIntensity(Point3D p) {

        double distance = Point3D.distance(position, p);
        double denominator = Kc + Ki * distance + Kq * Math.pow(distance, 2);

        Color result = new Color(super.color);
        result.scale(1 / denominator);
        return result;
    }

    /**
     * calculate the vector from the light source to the point on the Geometry.
     * actually return the opposite vector, fashion choice - DEAL WITH IT!
     *
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point3D p) {
        return new Vector(position, p).normal();
    }

    /**
     * return the direction of the light.
     * no implementation for PointLight
     *
     * @param p
     * @return Vector
     */
    @Override
    public Vector getD(Point3D p) {
        return null;
    }

    /**
     * @return the light source position in space.
     */
    public Point3D getPosition() {
        return position;
    }
}
