package primitives;

import javax.print.attribute.standard.MediaSize;

public class Point2D {
    private Coordinate x;
    private Coordinate y;

    // ***************** Constructor ********************** //
    public Point2D(double myX, double myY){
        x = new Coordinate(myX);
        y = new Coordinate(myY);

    }

    // ***************** Getters ********************** //
    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
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

}
