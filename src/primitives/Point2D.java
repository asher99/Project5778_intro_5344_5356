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

    /**
     * build by values
     *
     * @param myX
     * @param myY
     */
    public Point2D(double myX, double myY) {
        x = new Coordinate(myX);
        y = new Coordinate(myY);
    }

    /**
     * build by Coordinate objects
     *
     * @param X
     * @param Y
     */
    public Point2D(Coordinate X, Coordinate Y) {
        x = X;
        y = Y;
    }

    /**
     * default Constructor
     */
    public Point2D(){}

    // ***************** Getters ********************** //

    /**
     * getter.
     * @return
     */
    public double getX() {
        return x.getCoord();
    }

    /**
     * getter.
     * @return
     */
    public double getY() {
        return y.getCoord();
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return '(' + x.toString() + ',' + y.toString() + ')';
    }

    /**
     * check if another ibject is equal to this one.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return x.equals(((Point2D) other).x) && y.equals(((Point2D) other).y);
    }
// ***************** Operations ******************** //

    /**
     * receive two points and return new one (x1-x2,y1-y2)
     *
     * @param a
     * @param b
     * @return Point2D
     */
    public static Point2D subtract(Point2D a, Point2D b) {
        //  if(a == null || b == null)
        //  throw  new Exception();
        Coordinate X = Coordinate.subtract(a.x, b.x);
        Coordinate Z = Coordinate.subtract(a.y, b.y);
        return new Point2D(X, Z);

    }

    /**
     * return the distance between two points using the pythgore theorem.
     *
     * @param a
     * @param b
     * @return double
     */
    public static double distance(Point2D a, Point2D b) {
        Point2D DPoint = subtract(a, b);
        return Math.sqrt(Math.pow(DPoint.getX(), 2) + Math.pow(DPoint.getY(), 2));
    }
}
