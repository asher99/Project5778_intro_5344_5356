package primitives;

import javax.print.attribute.standard.MediaSize;

/**
 * class point2D for a point in the plain.
 */
public class Point2D {
    // the point 2 coordinates
    protected Coordinate x;
    protected Coordinate y;

    // ***************** Constructor ********************** //
    // build by values
    public Point2D(double myX, double myY){
        x = new Coordinate(myX);
        y = new Coordinate(myY);
    }

    //build by Coordinate objects
    public Point2D(Coordinate X, Coordinate Y){
        x = X;
        y = Y;
    }

    // ***************** Getters ********************** //
    public double getX() {
        return x.getCoord();
    }

    public double getY() {
        return y.getCoord();
    }

    @Override
    public String toString() {
        return '(' + x.toString() + ',' + y.toString() + ')';
    }

    @Override
    public boolean equals(Object other) {
        return x.equals(((Point2D)other).x) && y.equals(((Point2D)other).y) ;
    }
// ***************** Operations ******************** //

    // receive two points and return new one (x1-x2,y1-y2)
    public Point2D substract(Point2D a, Point2D b){
      //  if(a == null || b == null)
          //  throw  new Exception();
        Coordinate X = Coordinate.substract(a.x,b.x);
        Coordinate Z = Coordinate.substract(a.y,b.y);
        Point2D newPoint = new Point2D(X,Z);
        return  newPoint;
    }

    //return the distance between two points using the pythgore theorem.
    public double distance(Point2D a, Point2D b){
        Point2D DPoint = substract(a,b);
        return Math.sqrt(Math.pow(DPoint.getX(),2) + Math.pow(DPoint.getY(),2));
    }
}
