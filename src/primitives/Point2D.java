package primitives;

public class Point2D {
    private Coordinate x;
    private Coordinate y;

    // ***************** Constructor ********************** //
    public Point2D(double myX, double myY){
        setX(myX);
        setY(myY);

    }

    // ***************** Getters/Setters ********************** //
    public void setX(double X) {
        this.x = new Coordinate(X);
    }

    public void setY(double Y) {
        this.y = new Coordinate(Y);
    }

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
    public boolean equals(Point2D other) {
        return x.equals(other.x) && y.equals(other.y) ;
    }
// ***************** Operations ******************** //

}
