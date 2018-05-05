package primitives;

/**
 * class vector for a vector in 3D space.
 * any vector has a size: the distance from the origin,
 * and a direction: represent by the three coordinates combination.
 */
public class Vector {

    // any vector is based on a 3d point
    protected Point3D vector;

    //  a static object, represent the zero-vector.
    public static Vector zeroVector = new Vector(0, 0, 0);

    // ***************** Constructor ********************** //

    /**
     * builds vector by three coordinate values.
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        vector = new Point3D(x, y, z);
    }

    /**
     * build vector using an existing 3D point.
     *
     * @param myPoint
     */
    public Vector(Point3D myPoint) {
        if (myPoint.getX() == 0 &&
                myPoint.getY() == 0 &&
                myPoint.getZ() == 0)
            throw new ArithmeticException("a vector cannot be zero vector!");

        vector = myPoint;
    }

    /**
     * constract a Vector object by Two 3D points.
     * @param startPoint
     * @param finishPoint
     */
    public Vector(Point3D startPoint, Point3D finishPoint){
        vector = Point3D.subtract(finishPoint,startPoint);
    }

    // ***************** Operations ******************** //

    /**
     * execute a "subtract" operations on two vectors, and return the output vector.
     *
     * @param a
     * @param b
     * @return Vector
     */
    public static Vector Vectorialsubtract(Vector a, Vector b) {

        return new Vector(Point3D.subtract(a.vector, b.vector));
    }

    /**
     * execute a "add" operations on two vectors, and return the output vector.
     *
     * @param a
     * @param b
     * @return Vector
     */
    public static Vector VectorialAdd(Vector a, Vector b) {

        return new Vector(Point3D.add(a.vector, b.vector));
    }

    /**
     * multiply a vector by real scalar and return the output vector.
     *
     * @param scalar
     * @return Vector
     */
    public Vector multiplyByScalar(double scalar) {
        return new Vector(
                vector.getX() * scalar,
                vector.getY() * scalar,
                vector.getZ() * scalar);
    }

    /**
     * execute a "dot product" operations on two vectors, and return the output scalar.
     *
     * @param a
     * @param b
     * @return double (dot product)
     */
    public static double dotProduct(Vector a, Vector b) {
        return a.vector.getX() * b.vector.getX() +
                a.vector.getY() * b.vector.getY() +
                a.vector.getZ() * b.vector.getZ();
    }

    /**
     * execute a "cross product" operations on two vectors, and return the output vector.
     *
     * @param a
     * @param b
     * @return Vector
     */
    public static Vector crossProduct(Vector a, Vector b) {

        if(a == null || b == null)
            throw new NullPointerException("one of the vectors is null");

        return new Vector(a.vector.getY() * b.vector.getZ() - a.vector.getZ() * b.vector.getY(),
                -(a.vector.getX() * b.vector.getZ() - a.vector.getZ() * b.vector.getX()),
                a.vector.getX() * b.vector.getY() - a.vector.getY() * b.vector.getX());
    }

    /**
     * return the size of the vector. this is the squere root of X^2 + Y^2 + Z^2.
     *
     * @return double
     */
    public double sizeOfVector() {

        return Point3D.distance(vector, new Vector(0, 0, 0).vector);
    }

    /**
     * calculate the "distance" between vectors as the distance between the vectors 3D points members.
     * @param v1
     * @param v2
     * @return
     */
    public static double distance(Vector v1, Vector v2){
        return Point3D.distance(v1.vector,v2.vector);
    }

    /**
     * return the normal of the vector.
     * that means that the size of the returned vector is 1, but it keeps the original direction.
     *
     * @return Vector
     */
    public Vector normal() {
        double size = sizeOfVector();
        return multiplyByScalar(1 / size);
    }

    /**
     * return if a second vector has the same direction
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        Vector otherVector = (Vector) obj;
        Vector thisVector = this.normal();

        return thisVector.vector.equals(otherVector.normal().vector);
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {

        return vector.toString();
    }

    // ***************** Getters ********************** //
    /**
     * getter
     * @return
     */
    public Point3D getVector() {

        return vector;
    }
}
