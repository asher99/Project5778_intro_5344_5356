package geometries;

import primitives.*;

public class Plain extends Geometry {

    Point3D point;
    Vector orthonormal;

    // the following variables represents the values in the plain equation: "Ax + By + Cz + D = 0"
    double Avalue;
    double Bvalue;
    double Cvalue;
    double Dvalue;

    public Plain(Point3D myPoint, Vector myOrthonormal){
        point = myPoint;
        orthonormal = myOrthonormal;

        Avalue = orthonormal.getVector().getX();
        Bvalue = orthonormal.getVector().getY();
        Cvalue = orthonormal.getVector().getZ();
        findD();
    }

    public Vector getOrthonormal() {
        return orthonormal;
    }

    public Point3D getPoint() {
        return point;
    }

    @Override
    protected Vector getNormal(Object myPoint) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        Plain otherPlain = (Plain) obj;
        boolean sameDirection =  (this.orthonormal.equals(otherPlain.orthonormal));
        boolean samePlain = satisfyEquation(otherPlain.point);

        return (sameDirection && samePlain);
    }

    private void findD(){
        Dvalue = -1*(point.getX()*Avalue + point.getY()*Bvalue + point.getZ()*Cvalue);
    }

    public boolean satisfyEquation(Point3D myPoint){
        return ((point.getX()*Avalue + point.getY()*Bvalue + point.getZ()*Cvalue + Dvalue) == 0);
    }
}
