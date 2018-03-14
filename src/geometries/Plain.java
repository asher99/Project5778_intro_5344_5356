package geometries;

import primitives.*;

/**
 * class Plain for a plain in space.
 * the plaine is represented by a point and an orthogonal vector to the point.
 * to have even better image of the plain, we collect the data for the plain equation:
 * "Ax + By + Cz + D = 0".
 * A,B,C is from the orthogonal vector.
 * for D we placed A,B,C along with the point coordinates in the equation.
 *
 * Now, we can place any point in the equation, and see if it satisfy the equation -> and include in the plain.
 */
public class Plain extends Geometry {

    Point3D point;
    Vector orthonormal;

    // the following variables represents the values in the plain equation: "Ax + By + Cz + D = 0"
    double Avalue;
    double Bvalue;
    double Cvalue;
    double Dvalue;

    // ***************** Constructors ********************** //
    // constructor. set values also for A,B,C and call the method for finding D.
    public Plain(Point3D myPoint, Vector myOrthonormal){
        point = myPoint;
        orthonormal = myOrthonormal;

        Avalue = orthonormal.getVector().getX();
        Bvalue = orthonormal.getVector().getY();
        Cvalue = orthonormal.getVector().getZ();
        findD();
    }

    public Plain(Point3D myPoint1, Point3D myPoint2, Point3D myPoint3){
        point = myPoint1;
        orthonormal = getNormal();
    }

    // ***************** Getters/Setters ********************** //
    public Vector getOrthonormal() {
        return orthonormal;
    }

    public Point3D getPoint() {
        return point;
    }

    // for now return null;
    @Override
    protected Vector getNormal() {
        return null;
    }

    // we check if the vectors are the same.
    // we check if the other plain point satisfy the equation.
    // if both conditions exist - it is the same plain.
    @Override
    public boolean equals(Object obj) {
        Plain otherPlain = (Plain) obj;
        boolean sameDirection =  (this.orthonormal.equals(otherPlain.orthonormal));
        boolean samePlain = satisfyEquation(otherPlain.point);

        return (sameDirection && samePlain);
    }

    // find the value of D from the equation: "D = -Ax -By -Cz"
    private void findD(){
        Dvalue = -1*(point.getX()*Avalue + point.getY()*Bvalue + point.getZ()*Cvalue);
    }

    // check if point (x,y,z) satisfy the equation: "Ax + By + Cz + D = 0".
    public boolean satisfyEquation(Point3D myPoint){
        return ((point.getX()*Avalue + point.getY()*Bvalue + point.getZ()*Cvalue + Dvalue) == 0);
    }

    /*
    @Override
    public String toString() {
        return null;
    }
    */
}
