package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

/**
 * class Sphere for a sphere in space.
 * sphere is represented by a point in space and radius.
 */
public class Sphere extends RadialGeometry {

    Point3D center;

    // ***************** Constructors ********************** //

    public Sphere(Point3D myPoint, double myRadius){
        center = myPoint;
        _radius = myRadius;
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getCenter() {
        return center;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    // ***************** Operations ******************** //
    // check if a point is on the sphere surface by comaring the radius field to the distance from the point to the sphere center.
    // in case it is, return the vector from the center to the point - this is the normal to the tangent plane at that point.
    @Override
    public Vector getNormal(Point3D myPoint) {
        double dis_from_center = Point3D.distance(myPoint,center);
        Coordinate test = Coordinate.subtract(new Coordinate(dis_from_center),new Coordinate(_radius));
        if (test.equals(new Coordinate(0)))
            return new Vector(Point3D.subtract(myPoint,center));
        else return null;
    }


    // check if sphere are equals by comparing radius and the center points.
    @Override
    public boolean equals(Object obj) {
        Sphere otherSphere = (Sphere)obj;
        return ((center.equals(otherSphere.getCenter())) && (_radius==otherSphere._radius));
    }

    /*
    @Override
    public String toString() {
        return null;
    }
    */
}
