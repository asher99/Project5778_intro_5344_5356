package primitives;

import javafx.scene.layout.CornerRadii;

/**
 * class Coordinate for a single coordinate on a single axis.
 */
public class Coordinate {

    // the coordinate value
private double coord;

    // constant value for assumption of calculations
    private final static double EPSILON = 0.00001;

    // ***************** Constructor ********************** //
    public Coordinate(double newCoord){
        this.coord = newCoord;
    }

    // kind of copy constructor
    public Coordinate(Coordinate newCoord){
        coord = newCoord.getCoord();
    }

    // ***************** Getters ********************** //
    public double getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return String.valueOf(coord);
    }

    public boolean equals(Coordinate other) {
        if(this == other) return true;
        if(other == null) return false;
        if(!(other instanceof Coordinate)) return false;
        return (coord - other.coord < EPSILON) ;
    }

    // ***************** Operations ******************** //
    // add the value of two coordinates on the same axis and return the new coordinate.
    public static Coordinate add(Coordinate a, Coordinate b){

        return new Coordinate(a.coord + b.coord);
    }

    // substract the value of two coordinates on the same axis and return the new coordinate.
    public static Coordinate substract(Coordinate a, Coordinate b){

        return new Coordinate(a.coord - b.coord);
    }
}
