package primitives;

public class Vector {

    protected Point3D vector;

    // ***************** Constructor ********************** //
    public Vector(double x, double y, double z) {
        vector = new Point3D(x,y,z);
    }

    public Vector(Point3D vec) {
        vector = vec;
    }

    // ***************** Operations ******************** //
    public Vector add(Vector a , Vector b){
        return null;
    }

    public Vector VectorialSubstract(Vector a , Vector b){
      return new Vector(Point3D.substract(a.vector,b.vector)) ;
    }

    public Vector VectorialAdd(Vector a , Vector b){
        return new Vector(Point3D.add(a.vector,b.vector)) ;
    }

    public Vector multiplyByScalar(double scalar){
        return new Vector(
                vector.getX() * scalar,
                vector.getY() * scalar,
                vector.getZ() * scalar);
    }

    public double dotProduct(Vector a, Vector b){
        return  a.vector.getX() * b.vector.getX() +
                a.vector.getY() * b.vector.getY() +
                a.vector.getZ() * b.vector.getZ() ;
    }

    public Vector crossProduct(Vector a , Vector b){
        return new Vector(a.vector.getY() * b.vector.getZ() - a.vector.getZ()* b.vector.getY(),
                          -(a.vector.getX() * b.vector.getZ() - a.vector.getZ()* b.vector.getX()),
                         a.vector.getX()* b.vector.getY() - a.vector.getY()*b.vector.getX());
    }

    public double sizeOfVector(){
        return Point3D.distance(vector, new Vector(0,0,0).vector);
    }

    /**
     * return the normal of the vector.
     * that means that the size of the returned vector is 1, but it keeps the original direction.
     * @return Vector
     */
    public Vector normal(){
        double size = sizeOfVector();
        return multiplyByScalar(1/size);
    }

}
