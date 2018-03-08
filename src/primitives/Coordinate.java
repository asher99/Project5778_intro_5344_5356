package primitives;


public class Coordinate {

private double coord;

// constant value for assumption of calculations
final static double EPSILON = 0.00001;

    public Coordinate(double newCoord){
        this.coord = newCoord;
    }

    public double getCoord() {
        return coord;
    }

    public void setCoord(double coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return String.valueOf(Coord);
    }

    @Override
    public boolean equals(Coordinate other) {
        return (coord - other.coord < EPSILON) ;
    }
}
