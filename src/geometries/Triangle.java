package geometries;

import primitives.*;

/**
 * class Triangle for triangle in space.
 * the triangle is represnted by three points in space.
 */
public class Triangle extends Plain{

    private Point3D a;
    private Point3D b;
    private Point3D c;

    // ***************** Constructors ********************** //
    //constructor
    public Triangle(Point3D myA, Point3D myB, Point3D myC)
    {
        super(myA, myB, myC);
        a = myA;
        b = myB;
        c = myC;


    }

    // ***************** Getters/Setters ********************** //
    public Point3D getA() {
        return a;
    }

    public Point3D getB() {
        return b;
    }

    public Point3D getC() {
        return c;
    }

    // ***************** Operations ******************** //
    // for now return now.
    @Override
    protected Vector getNormal(Point3D somePoint) {
        return super.getNormal(somePoint);
    }

    /*
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
    */
}
