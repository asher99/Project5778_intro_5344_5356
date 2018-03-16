package geometries;

import primitives.*;

import java.net.PortUnreachableException;

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

    // constructor. receive three points, set one point as the class member "point"
    // and use the three points to calculate the "orthonormal" member.
    public Plain(Point3D myPoint1, Point3D myPoint2, Point3D myPoint3){

        //if(linearlyDipendent(myPoint1,myPoint2)||linearlyDipendent(myPoint1,myPoint3)||linearlyDipendent(myPoint2,myPoint3))

        point = myPoint1;
        Vector vec1 = new Vector(Point3D.subtract(myPoint3,myPoint2));
        Vector vec2 = new Vector(Point3D.subtract(myPoint2,myPoint1));
        orthonormal = Vector.crossProduct(vec1,vec2);
    }

    // ***************** Getters/Setters ********************** //
    public Vector getOrthonormal() {
        return orthonormal;
    }

    public Point3D getPoint() {
        return point;
    }


    // ***************** Operations ******************** //

    // the Normal is the "orthonormal" class member.
    @Override
    protected Vector getNormal(Point3D somePoint) {
        return orthonormal;
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

    // check if two points in space are lineraly dipendent.
    boolean linearlyDipendent(Point3D point1, Point3D point2){
        double xDiff = point1.getX() - point2.getX();
        double yDiff = point1.getY() - point2.getY();
        double zDiff = point1.getZ() - point2.getZ();

        if (xDiff==yDiff&&yDiff==zDiff)
            return true;
        else return false;
    }

    /*
    @Override
    public String toString() {
        return null;
    }
    */
}
