package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
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

    // ***************** Constructors ********************** //

    /**
     * constructor
     * @param myRadius
     * @param myRay
     * @param e
     */
    public Cylinder(double myRadius, Ray myRay, Color e) {
        super(myRadius,e);
        _radius = myRadius;
        orientation = myRay;
    }

    /**
     * constructor with no color uses the default color.
     * @param myRadius
     * @param myRay
     */
    public Cylinder(double myRadius, Ray myRay) {
        super(myRadius);
        _radius = myRadius;
        orientation = myRay;
    }

    // ***************** Getters/Setters ********************** //

    /**
     * getter
     * @return
     */
    public Ray getOrientaion() {
        return orientation;
    }

    /**
     * getter
     * @return
     */
    public Color getEmission(){
        return super.getEmission();
    }

    /**
     * getter
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
     * @param myRay
     * @return
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {
        return null;
    }
}
