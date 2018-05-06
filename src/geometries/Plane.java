package geometries;

import primitives.*;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class plane for a plane in space.
 * the planee is represented by a point and an orthogonal vector to the point.
 * to have even better image of the plane, we collect the data for the plane equation:
 * "Ax + By + Cz + D = 0".
 * A,B,C is from the orthogonal vector.
 * for D we placed A,B,C along with the point coordinates in the equation.
 * <p>
 * Now, we can place any point in the equation, and see if it satisfy the equation -> and include in the plane.
 */
public class Plane extends Geometry {

    Point3D point;
    Vector orthonormal;

    // the following variables represents the values in the plane equation: "Ax + By + Cz + D = 0"
    double aValue;
    double bValue;
    double cValue;
    double dValue;

    // ***************** Constructors ********************** //

    /**
     * constructor.
     * set values also for A,B,C and call the method for finding D.
     *
     * @param myOrthonormal
     * @param myPoint
     * @param emission
     * @param m
     */
    public Plane(Point3D myPoint, Vector myOrthonormal, Color emission, Material m) {
        super(emission, m);
        point = myPoint;
        orthonormal = myOrthonormal.normal();

        aValue = orthonormal.getVector().getX();
        bValue = orthonormal.getVector().getY();
        cValue = orthonormal.getVector().getZ();
        findD();
    }

    /**
     * constructor with default emission.
     * set values also for A,B,C and call the method for finding D.
     *
     * @param myPoint
     * @param myOrthonormal
     */
    public Plane(Point3D myPoint, Vector myOrthonormal) {
        super(new Color(255, 255, 255), new Material());
        point = myPoint;
        orthonormal = myOrthonormal.normal();

        aValue = orthonormal.getVector().getX();
        bValue = orthonormal.getVector().getY();
        cValue = orthonormal.getVector().getZ();
        findD();
    }

    /**
     * constructor. receive three points, set one point as the class member "point"
     * and use the three points to calculate the "orthonormal" member.
     *
     * @param myPoint1
     * @param myPoint2
     * @param myPoint3
     * @param emission
     * @param m
     */
    public Plane(Point3D myPoint1, Point3D myPoint2, Point3D myPoint3, Color emission, Material m) {
        super(emission,m);
        //if(linearlyDipendent(myPoint1,myPoint2)||linearlyDipendent(myPoint1,myPoint3)||linearlyDipendent(myPoint2,myPoint3))

        point = myPoint1;
        Vector vec1 = new Vector(Point3D.subtract(myPoint3, myPoint2));
        Vector vec2 = new Vector(Point3D.subtract(myPoint2, myPoint1));
        orthonormal = Vector.crossProduct(vec1, vec2).normal();
    }

    /**
     * constructor. receive three points, set one point as the class member "point"
     * and use the three points to calculate the "orthonormal" member.
     *
     * @param myPoint1
     * @param myPoint2
     * @param myPoint3
     */
    public Plane(Point3D myPoint1, Point3D myPoint2, Point3D myPoint3) {
        super();
        //if(linearlyDipendent(myPoint1,myPoint2)||linearlyDipendent(myPoint1,myPoint3)||linearlyDipendent(myPoint2,myPoint3))

        point = myPoint1;
        Vector vec1 = new Vector(Point3D.subtract(myPoint3, myPoint2));
        Vector vec2 = new Vector(Point3D.subtract(myPoint2, myPoint1));
        orthonormal = Vector.crossProduct(vec1, vec2).normal();
    }
    // ***************** Getters/Setters ********************** //

    /**
     * getter
     *
     * @return
     */
    public Vector getOrthonormal() {

        return orthonormal;
    }

    /**
     * getter
     *
     * @return
     */
    public Point3D getPoint() {
        return point;
    }

    /**
     * getter
     *
     * @return
     */
    public Color getEmission() {
        return super.getEmission();
    }

    // ***************** Operations ******************** //

    /**
     * the Normal is the "orthonormal" class member.
     *
     * @param somePoint
     * @return
     */
    @Override
    public Vector getNormal(Point3D somePoint) {
        return orthonormal;
    }

    /**
     * we check if the vectors are the same.
     * we check if the other plane point satisfy the equation.
     * if both conditions exist - it is the same plane.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Plane otherPlane = (Plane) obj;
        boolean sameDirection = (this.orthonormal.equals(otherPlane.orthonormal));
        boolean samePlane = satisfyEquation(otherPlane.point);

        return (sameDirection && samePlane);
    }

    /**
     * find the value of D from the equation: "D = -Ax -By -Cz"
     */
    private void findD() {

        dValue = -1 * (point.getX() * aValue + point.getY() * bValue + point.getZ() * cValue);
    }

    /**
     * check if point (x,y,z) satisfy the equation: "Ax + By + Cz + D = 0".
     *
     * @param myPoint
     * @return
     */
    public boolean satisfyEquation(Point3D myPoint) {
        return ((point.getX() * aValue + point.getY() * bValue + point.getZ() * cValue + dValue) == 0);
    }

    /**
     * check if two points in space are lineraly dipendent.
     *
     * @param point1
     * @param point2
     * @return
     */
    boolean linearlyDipendent(Point3D point1, Point3D point2) {
        double xDiff = point1.getX() - point2.getX();
        double yDiff = point1.getY() - point2.getY();
        double zDiff = point1.getZ() - point2.getZ();

        if (xDiff == yDiff && yDiff == zDiff)
            return true;
        else return false;
    }

    /**
     * @param myRay a ray that may intersect the Plane.
     * @return an Map entry of the intersection point between the ray and Plane, if exist. the key is this Geometry.
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {

        double scalart = (Vector.dotProduct(orthonormal, new Vector(myRay.getPoint(), point))) / (Vector.dotProduct(orthonormal, myRay.getDirection()));
        if (scalart >= 0) {
            Point3D intersectionPoint = Point3D.add(myRay.getPoint(), myRay.getDirection().multiplyByScalar(scalart).getVector());
            List<Point3D> result = new ArrayList<Point3D>();
            result.add(intersectionPoint);
            Map<Geometry, List<Point3D>> geometryListMap = new HashMap<>();
            geometryListMap.put(this, result);
            return geometryListMap;
        } else return null;
    }
}
