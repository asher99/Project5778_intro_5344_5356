package geometries;

import primitives.*;

/**
 * class RadialGeometry for Shapes identified by a radius.
 */
public abstract class RadialGeometry extends Geometry {

    protected double _radius;

    // ***************** Constructors ********************** //
    //default Constructor
    public RadialGeometry(){
        _radius = 0.0000;
    }

    //constructor
    public RadialGeometry(double myRadius){
        _radius = myRadius;
    }

    //copi constructor
    public RadialGeometry(final RadialGeometry g){
        _radius = g._radius;
    }

    // ***************** Getters/Setters ********************** //
    public double get_radius() {
        return _radius;
    }

    // ***************** Operations ******************** //
    //override the "getNormal" with corresponding abstract method.
    @Override
    public abstract Vector getNormal(Point3D somePoint);

}
