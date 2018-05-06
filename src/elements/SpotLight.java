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
     * return the direction of the light.
     * @param p
     * @return Vector
     */
    @Override
    public Vector getD(Point3D p){
        return direction;
    };

    /**
     * @return Color of a point
     * in SpotLight we have to treat the direction of the light relative to the object position - the numerator component,
     * along with the distance from the object - dhe denominator component.
     */
    @Override
    public Color getIntensity(Point3D p){

        //distance
        double distance = Point3D.distance(position,p);
        double denominator = Kc + distance*Ki + distance*Math.pow(Kq,2);

        //position
        Vector l = new Vector(position,p);
        double numerator = Vector.dotProduct(direction,l.normal());

        //scale
        Color result = new Color(color);
        result.scale(numerator/denominator);

        return result;
    };
}
