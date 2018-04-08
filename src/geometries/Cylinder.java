package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class Cylinder for cylinder in space.
 * a cylinder have direction and points => Ray orientation.
 * we decided to implement infinite cylinder.
 */
public class Cylinder extends RadialGeometry {

    Ray orientaion;

    // ***************** Constructors ********************** //
    //constructor
    public Cylinder(double myRadius, Ray myRay){
        _radius = myRadius;
        orientaion = myRay;
    }

    // ***************** Getters/Setters ********************** //
    public Ray getOrientaion() {
        return orientaion;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    // ***************** Operations ******************** //
    //for now return null.
    @Override
    public Vector getNormal(Point3D somePoint) {
        return null;
    }
}
