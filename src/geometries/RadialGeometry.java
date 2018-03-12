package geometries;

public abstract class RadialGeometry extends Geometry {

    protected double _radius;

    public RadialGeometry(double myRadius){
        _radius = myRadius;
    }

    public RadialGeometry(final RadialGeometry g){
        _radius = g._radius;
    }

    public double get_radius() {
        return _radius;
    }
}
