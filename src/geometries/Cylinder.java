package geometries;

import primitives.*;
import primitives.Color;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * class Cylinder for cylinder in space.
 * a cylinder have direction and points => Ray orientation.
 * we decided to implement infinite cylinder.
 */
public class Cylinder extends RadialGeometry {

    Ray orientation;
    //double t_length;
    // ***************** Constructors ********************** //

    /**
     * constructor
     *
     * @param myRadius
     * //@param length
     * @param myRay
     * @param e
     * @param m
     */
    public Cylinder(double myRadius/*, double length*/, Ray myRay, Color e, Material m) {
        super(myRadius, e, m);
        _radius = myRadius;
        orientation = myRay;
        //t_length = length;
    }

    /**
     * constructor with no color uses the default color.
     *
     * @param myRadius
     * //@param length
     * @param myRay
     */
    public Cylinder(double myRadius/*, double length*/, Ray myRay) {
        super(myRadius);
        _radius = myRadius;
        orientation = myRay;
       // t_length = length;
    }

    /**
     * constructor with no length uses the default of 1.
     *
     * @param myRadius
     * @param myRay
     */
 /*   public Cylinder(double myRadius, Ray myRay) {
        super(myRadius);
        _radius = myRadius;
        orientation = myRay;
        t_length = 1;
    }
    */
    // ***************** Getters/Setters ********************** //

    /**
     * getter
     *
     * @return
     */
    public Ray getOrientaion() {
        return orientation;
    }

    /**
     * getter
     *
     * @return
     */
   /* public double getT_length() {
        return t_length;
    }
*/
    /**
     * getter
     *
     * @return
     */
    public Color getEmission() {
        return super.getEmission();
    }

    /**
     * getter
     *
     * @return
     */
    @Override
    public double get_radius() {
        return super.get_radius();
    }

    // ***************** Operations ******************** //

    /**
     * calculate the vector from somePoint to the point that defines the Cylinder orientation.
     * full explanation in the code.
     *
     * @param somePoint
     * @return
     */
    @Override
    public Vector getNormal(Point3D somePoint) {
        // first, we need to have all components for Pythgore theorem.
        // we already have the radius - which is one of the bases,
        // and the reminder will be the vector from the point that defines the orientation to somePoint.
        Vector reminder = new Vector(somePoint, orientation.getPoint());
        double sizeOfBaseOnCylinderSurface = Math.sqrt(Math.pow(reminder.sizeOfVector(), 2) - Math.pow(get_radius(), 2));

        //second, define new point: the point on the Cylinder surface which the vector from the orientation point to this point
        //will be orthogonal to the orientation vector.
        //since we squerd the reminder the new point is p0 + tv or po -tv,
        //where p0 is the orientation point, v is the orientation vector, and t is the sizeOfBaseOnCylinderSurface.
        Vector tv = orientation.getDirection().multiplyByScalar(sizeOfBaseOnCylinderSurface);
        Point3D pointBehind = Point3D.add(somePoint, tv.getVector());
        Point3D pointInFront = Point3D.subtract(somePoint, tv.getVector());

        // finally, one of the vectors from the orientation point to those point is the one we look for.
        // to determine which one is it, we check which vector is orthogonal to the orientation vector.
        Vector toPointBehind = new Vector(orientation.getPoint(), pointBehind);
        Vector toPointInFront = new Vector(orientation.getPoint(), pointInFront);

        if (Vector.dotProduct(orientation.getDirection(), toPointBehind) == 0)
            return toPointBehind.normal();
        else
            return toPointInFront.normal();
    }

    /**
     * return map of the Ray intersection points with the Cylinder.
     * no implementation, for now.
     *
     * @param myRay
     * @return
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {

        Map<Geometry, List<Point3D>> geometryListMap = new HashMap<>();
        List<Point3D> listOfIntersections = new ArrayList<Point3D>();

        Vector rayDirection = new Vector(myRay.getDirection().getVector());
        Vector cylinderDirection = new Vector(getOrientaion().getDirection().getVector());
        Point3D rayPoint = new Point3D(myRay.getPoint());
        Point3D cylinderPoint = new Point3D(getOrientaion().getPoint());

        double A = Math.pow(rayDirection.getVector().getX(), 2) +
                Math.pow(rayDirection.getVector().getY(), 2);

        double B = 2 * rayPoint.getX() * rayDirection.getVector().getX() +
                2 * rayPoint.getY() * rayDirection.getVector().getY();

        double C = Math.pow(rayPoint.getX(), 2) +
                Math.pow(rayPoint.getY(), 2) - Math.pow(get_radius(),2);

        double sqrtCom = Math.sqrt(Math.pow(B, 2) - 4 * A * C);

        if (sqrtCom < 0)
            return null;

        double t1 = (-1 * B + sqrtCom) / 2 * A;

        double t2 = (-1 * B - sqrtCom) / 2 * A;

        Point3D intersection1 = Point3D.add(rayPoint,rayDirection.multiplyByScalar(t1).getVector());

        Point3D intersection2 = Point3D.add(rayPoint,rayDirection.multiplyByScalar(t2).getVector());

        listOfIntersections.add(intersection1);
        listOfIntersections.add(intersection2);
        geometryListMap.put(this,listOfIntersections);
        return geometryListMap;
    }
}
