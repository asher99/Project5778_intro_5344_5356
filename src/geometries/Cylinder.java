package geometries;

import primitives.Ray;
import primitives.Vector;

/**
 * class Cylinder for cylinder in space.
 * a cylinder have direction and points => Ray orientation.
 * we decided to implement ifinite cylinder.
 */
public class Cylinder extends RadialGeometry {

    Ray orientaion;

    //constructor
    public Cylinder(double myRadius, double myHeight, Ray myRay){
        _radius = myRadius;

        orientaion = myRay;
    }

    //getters
    public Ray getOrientaion() {
        return orientaion;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    //for now return null.
    @Override
    public Vector getNormal() {
        return null;
    }
}
