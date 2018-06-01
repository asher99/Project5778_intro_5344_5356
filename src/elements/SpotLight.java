package elements;

import primitives.*;

/**
 * a light source that has a direction and position
 * extends point light that has the values:
 */
public class SpotLight extends PointLight {

    // the direction of the light source
    Vector direction;

    /**
     * constructor
     * @param place
     * @param kc
     * @param ki
     * @param kq
     * @param color
     * @param direct
     */
    public SpotLight(Point3D place, double kc, double ki, double kq, Color color, Vector direct) {
        super(place, kc, ki, kq, color);
        direction = direct.normal();
    }

    /**
     * return the direction of the light.
     *
     * @param p
     * @return Vector
     */
    @Override
    public Vector getD(Point3D p) {
        return direction;
    }

    /**
     * @return Color of a point
     * in SpotLight we have to treat the direction of the light relative to the object position - the numerator component,
     * along with the distance from the object - dhe denominator component.
     */
    @Override
    public Color getIntensity(Point3D p) {

        //distance
        double distance = Point3D.distance(position, p);
        double denominator = Kc +  Ki*distance + Kq * Math.pow(distance, 2);

        //position
        Vector l = new Vector(position, p);
        double numerator = Vector.dotProduct(direction, l.normal());

        //scale
        Color result = new Color(color);
        result.scale(Math.abs(numerator / denominator));

        return result;
    }
}
