package geometries;

import primitives.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * class Geometry for geometrial shapes.
 */
public abstract class Geometry {

    // ***************** Constructors ********************** //
    //default constructor
    public Geometry(){};

    // copy constructor
    public Geometry(final Geometry g){};

    // ***************** Operations ******************** //
    // return the normal to a shape
    protected abstract Vector getNormal(Point3D somePoint);

    // receive a Ray and return all the points that Ray intersevt with the Geometry.
    protected abstract ArrayList<Point3D> findIntersections(Ray myRay);
}
