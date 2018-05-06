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

   double Kc;

   double Ki;

   double Kq;

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