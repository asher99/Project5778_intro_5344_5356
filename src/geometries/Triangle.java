package geometries;

import primitives.*;

import java.util.ArrayList;

/**
 * class Triangle for triangle in space.
 * the triangle is represnted by three points in space.
 */
public class Triangle extends Plane {

    private Point3D a;
    private Point3D b;
    private Point3D c;

    // ***************** Constructors ********************** //
    public Triangle(Point3D myA, Point3D myB, Point3D myC) {
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
    public Vector getNormal(Point3D somePoint) {
        return super.getNormal(somePoint);
    }


    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Triangle) || obj == null)
            return false;

        // checks if all points in both triangles are equal in every possible combination
        return (a.equals(((Triangle) obj).a) && b.equals(((Triangle) obj).b) && c.equals(((Triangle) obj).c)) ||
                (a.equals(((Triangle) obj).a) && b.equals(((Triangle) obj).c) && c.equals(((Triangle) obj).b)) ||
                (a.equals(((Triangle) obj).b) && b.equals(((Triangle) obj).c) && c.equals(((Triangle) obj).a)) ||
                (a.equals(((Triangle) obj).b) && b.equals(((Triangle) obj).a) && c.equals(((Triangle) obj).c)) ||
                (a.equals(((Triangle) obj).c) && b.equals(((Triangle) obj).b) && c.equals(((Triangle) obj).a)) ||
                (a.equals(((Triangle) obj).c) && b.equals(((Triangle) obj).a) && c.equals(((Triangle) obj).b));
    }

    @Override
    public String toString() {
        return a.toString() + "," + b.toString() + "," + a.toString() + ",";
    }

    // receive a Ray and return all the points that Ray intersevt with the Geometry.
    public ArrayList<Point3D> findIntersections(Ray myRay){
        return null;
    }
}
