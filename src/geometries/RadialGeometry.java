package geometries;

import primitives.Vector;

/**
 * class RadialGeometry for Shapes identified by a radius.
 */
public abstract class RadialGeometry extends Geometry {

    protected double _radius;

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

    //getter
    public double get_radius() {
        return _radius;
    }

    //override the "getNormal" with corresponding abstract method.
    @Override
    public abstract Vector getNormal();

}
