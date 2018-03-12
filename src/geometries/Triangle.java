package geometries;

import primitives.*;

public class Triangle extends Geometry{

    private Point3D a;
    private Point3D b;
    private Point3D c;

    public Triangle(Point3D myA, Point3D myB, Point3D myC)
    {
        a = myA;
        b = myB;
        c = myC;
    }

    public Point3D getA() {
        return a;
    }

    public Point3D getB() {
        return b;
    }

    public Point3D getC() {
        return c;
    }

    @Override
    protected Vector getNormal(Object myPoint) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
