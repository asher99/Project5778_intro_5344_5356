package primitives;

/**
 *  class vector for a vector in 3D space.
 *  any vector has a size: the distance from the origin,
 *  and a direction: represent by the three coordinates combination.
 */
public class Vector {

    // any vector is based on a 3d point
    protected Point3D vector;

    // ***************** Constructor ********************** //
    // build vector by three coordinate values.
    public Vector(double x, double y, double z) {
        vector = new Point3D(x,y,z);
    }

    // build vector using an existing 3D point.
    public Vector(Point3D myPoint) {
        vector = myPoint;
    }

    // ***************** Operations ******************** //

    // execute a "subtract" operations on two vectors, and return the output vector.
    public Vector Vectorialsubtract(Vector a , Vector b){
      return new Vector(Point3D.subtract(a.vector,b.vector)) ;
    }

    // execute a "add" operations on two vectors, and return the output vector.
    public Vector VectorialAdd(Vector a , Vector b){
        return new Vector(Point3D.add(a.vector,b.vector)) ;
    }

    // multiply a vector by real scalar and return the output vector.
    public Vector multiplyByScalar(double scalar){
        return new Vector(
                vector.getX() * scalar,
                vector.getY() * scalar,
                vector.getZ() * scalar);
    }

    // execute a "dot product" operations on two vectors, and return the output scalar.
    public static double dotProduct(Vector a, Vector b){
        return  a.vector.getX() * b.vector.getX() +
                a.vector.getY() * b.vector.getY() +
                a.vector.getZ() * b.vector.getZ() ;
    }

    // execute a "cross product" operations on two vectors, and return the output vector.
    public static Vector crossProduct(Vector a , Vector b){
        return new Vector(a.vector.getY() * b.vector.getZ() - a.vector.getZ()* b.vector.getY(),
                          -(a.vector.getX() * b.vector.getZ() - a.vector.getZ()* b.vector.getX()),
                         a.vector.getX()* b.vector.getY() - a.vector.getY()*b.vector.getX());
    }

    // return the size of the vector. this is the squere root of X^2 + Y^2 + Z^2.
    public double sizeOfVector(){

        return Point3D.distance(vector, new Vector(0,0,0).vector);
    }

    /**
     * return the normal of the vector.
     * that means that the size of the returned vector is 1, but it keeps the original direction.
     * @return Vector
     */
    public Vector normal(){
        if(vector.getX() == 0 &&
                vector.getY() == 0 &&
                vector.getZ()== 0 )
            return new Vector(0,0,0);
        double size = sizeOfVector();
        return multiplyByScalar(1/size);
    }

    // return if a second vector has the same direction
    @Override
    public boolean equals(Object obj) {
        Vector otherVector = (Vector)obj;
        Vector thisVector = this.normal();

       return thisVector.vector.equals(otherVector.normal().vector);
    }

    @Override
    public String toString() {

        return vector.toString();
    }

    public Point3D getVector() {

        return vector;
    }
}
