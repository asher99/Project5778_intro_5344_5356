package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class Sphere for a sphere in space.
 * sphere is represented by a point in space and radius.
 */
public class Sphere extends RadialGeometry {

    Point3D center;

    // constructor
    public Sphere(Point3D myPoint, double myRadius){
        center = myPoint;
        _radius = myRadius;
    }

    // getters
    public Point3D getCenter() {
        return center;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    // for now return null.
    @Override
    public Vector getNormal() {
        return null;
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
