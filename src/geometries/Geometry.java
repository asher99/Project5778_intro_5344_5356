package geometries;

import primitives.*;

/**
 * class Geometry for geometrial shapes.
 */
public abstract class Geometry {

    //default constructor
    public Geometry(){};

    // copy constuctor
    public Geometry(final Geometry g){};

    // return the normal to a shape
    protected abstract Vector getNormal();
}
