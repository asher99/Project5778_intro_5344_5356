package primitives;
import java.util.Objects;

/**
 * class point3D for point in the space.
 * this class EXTENDS the point2D class.
 */
public class Point3D extends Point2D {

    //additional coordinate for the z axis.
    private Coordinate z;

    // ***************** Constructor ********************** //

    /**
     * build by three values for coordinates
     *
     * @param myX
     * @param myY
     * @param myZ
     */
    public Point3D(double myX, double myY, double myZ) {
        super(myX, myY);
        z = new Coordinate(myZ);
    }

    /**
     * build a point from a Vector
     *
     * @param v
     */
    public Point3D(Vector v) {
        super(v.vector.x, v.vector.y);
        z = v.vector.z;
    }

    /**
     * "copy" constructor.
     *
     * @param p
     */
    public Point3D(Point3D p) {
        super.x = new Coordinate(p.getX());
        super.y = new Coordinate(p.getY());
        this.z = new Coordinate(p.getZ());
    }

    // ***************** Getters ********************** //

    /**
     * getter
     *
     * @return double
     */
    public double getZ() {
        return z.getCoord();
    }

    // ***************** Operations ******************** //

    /**
     * check if another object is equal to this one.
     *
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other) && z.equals(((Point3D) other).z);
    }

    /**
     * overriding hashMap.
     * @return
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.x.getCoord(), super.y.getCoord(), z.getCoord());
    }

    /**
     * toString.
     *
     * @return String
     */
    @Override
    public String toString() {
        return '(' + x.toString() + ',' + y.toString() + ',' + z.toString() + ')';
    }

    /**
     * receive two points and return new one (x1+x2,y1+y2,z1+z2).
     *
     * @param a
     * @param b
     * @return
     */
    public static Point3D add(Point3D a, Point3D b) {
        Coordinate X = Coordinate.add(a.x, b.x);
        Coordinate Y = Coordinate.add(a.y, b.y);
        Coordinate Z = Coordinate.add(a.z, b.z);
        Point3D newPoint = new Point3D(X.getCoord(), Y.getCoord(), Z.getCoord());
        return newPoint;
    }

    /**
     * receive two points and return new one (x1-x2,y1-y2,z1-z2).
     *
     * @param a
     * @param b
     * @return
     */
    public static Point3D subtract(Point3D a, Point3D b) {
        //  if(a == null || b == null)
        //  throw  new Exception();
        Coordinate X = Coordinate.subtract(a.x, b.x);
        Coordinate Y = Coordinate.subtract(a.y, b.y);
        Coordinate Z = Coordinate.subtract(a.z, b.z);
        Point3D newPoint = new Point3D(X.getCoord(), Y.getCoord(), Z.getCoord());
        return newPoint;
    }

    /**
     * return the distance between two points using the pythgore theorem.
     *
     * @param a
     * @param b
     * @return
     */
    public static double distance(Point3D a, Point3D b) {
        Point3D DPoint = subtract(a, b);
        return Math.sqrt(Math.pow(DPoint.getX(), 2) + Math.pow(DPoint.getY(), 2) + Math.pow(DPoint.getZ(), 2));
    }
}
