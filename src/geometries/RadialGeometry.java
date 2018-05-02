package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
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
    public RadialGeometry(double myRadius, Color e){
        super(e);
        _radius = myRadius;
    }

    // constructor with no color uses the defualt color.
    public RadialGeometry(double myRadius){
        super();
        _radius = myRadius;
    }

    //copy constructor
    public RadialGeometry(final RadialGeometry g,Color e){
        super(e);
        _radius = g._radius;
    }

    // ***************** Getters/Setters ********************** //
    public double get_radius() {

        return _radius;
    }

    public Color getEmission(){
        return super.getEmission();
    }

    // ***************** Operations ******************** //
    //override the "getNormal" with corresponding abstract method.
    @Override
    public abstract Vector getNormal(Point3D somePoint);

    // receive a Ray and return all the points that Ray intersevt with the Geometry.
    public abstract List<Point3D> findIntersections(Ray myRay);


}
