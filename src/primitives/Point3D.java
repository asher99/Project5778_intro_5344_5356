package primitives;

public class Point3D extends Point2D {

    Coordinate z;

    // ***************** Constructor ********************** //

    public Point3D(double myX, double myY,double myZ){
        super(myX,myY);
        z = new Coordinate(myZ);
    }

    // ***************** Getters ********************** //

    public double getZ() {
        return z.getCoord();
    }

    // ***************** Operations ******************** //

    @Override
    public boolean equals(Object other) {
        return   super.equals(other) && z.equals(((Point3D)other).z) ;
    }

    @Override
    public String toString() {
        return '(' + x.toString() + ',' + y.toString() + ',' + z.toString() + ')';
    }

    public Point3D substract(Point3D a, Point3D b){
        //  if(a == null || b == null)
        //  throw  new Exception();
        Coordinate X = Coordinate.substract(a.x,b.x);
        Coordinate Y = Coordinate.substract(a.y,b.y);
        Coordinate Z = Coordinate.substract(a.z,b.z);
        Point3D newPoint = new Point3D(X.getCoord(),Y.getCoord(),Z.getCoord());
        return  newPoint;
    }

    public double distance(Point3D a, Point3D b){
        Point3D DPoint = substract(a,b);
        return Math.sqrt(Math.pow(DPoint.getX(),2) + Math.pow(DPoint.getY(),2) + Math.pow(DPoint.getZ(),2));
    }
}
