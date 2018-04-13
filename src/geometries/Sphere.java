package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

/**
 * class Sphere for a sphere in space.
 * sphere is represented by a point in space and radius.
 */
public class Sphere extends RadialGeometry {

    Point3D center;

    // ***************** Constructors ********************** //

    public Sphere(Point3D myPoint, double myRadius) {
        center = myPoint;
        _radius = myRadius;
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getCenter() {
        return center;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    // ***************** Operations ******************** //
    // check if a point is on the sphere surface by comaring the radius field to the distance from the point to the sphere center.
    // in case it is, return the vector from the center to the point - this is the normal to the tangent plane at that point.
    @Override
    public Vector getNormal(Point3D myPoint) {
        double dis_from_center = Point3D.distance(myPoint, center);
        Coordinate test = Coordinate.subtract(new Coordinate(dis_from_center), new Coordinate(_radius));
        if (test.equals(new Coordinate(0)))
            return new Vector(Point3D.subtract(myPoint, center)).normal();
        else return null;
    }


    // check if sphere are equals by comparing radius and the center points.
    @Override
    public boolean equals(Object obj) {
        Sphere otherSphere = (Sphere) obj;
        return ((center.equals(otherSphere.getCenter())) && (_radius == otherSphere._radius));
    }

    /**
     *
     * @param myRay a ray that may intersect the Sphere.
     * @return an ArrayList of all intersection points between the ray and sphere.
     */
    @Override
    public ArrayList<Point3D> findIntersections(Ray myRay) {

        ArrayList<Point3D> listOfIntersections = new ArrayList<Point3D>();

        Vector u = new Vector( myRay.getPoint(),center);
        double tm = Vector.dotProduct(myRay.getDirection(), u);
        double d = Math.sqrt(Math.pow(u.sizeOfVector(), 2) - Math.pow(tm, 2));

        // if there is no intersections
        if (d > _radius)
            return null;
        // if there is one intersection
        if (new Coordinate(d).equals(new Coordinate(_radius)))
            listOfIntersections.add(Point3D.add(myRay.getPoint(), myRay.getDirection().multiplyByScalar(tm).getVector())) ;

        else if (d < _radius) {
            double th = Math.sqrt(Math.pow(_radius, 2) - Math.pow(d, 2));

            double t1 = tm + th;
            double t2 = tm - th;

            if(t1 >= 0)
                listOfIntersections.add(Point3D.add(myRay.getPoint(),
                                                    myRay.getDirection().multiplyByScalar(t2).getVector()));
            if(t2 >= 0)
                listOfIntersections.add(Point3D.add(myRay.getPoint(),
                                                    myRay.getDirection().multiplyByScalar(t1).getVector()));

        }
        return  listOfIntersections;
    }

    /*
    @Override
    public String toString() {
        return null;
    }
    */
}
