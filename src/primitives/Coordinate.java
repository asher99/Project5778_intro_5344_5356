package primitives;

import javafx.scene.layout.CornerRadii;

public class Coordinate {

private double coord;

    // constant value for assumption of calculations
    private final static double EPSILON = 0.00001;

    // ***************** Constructor ********************** //
    public Coordinate(double newCoord){
        this.coord = newCoord;
    }

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
    public static Coordinate add(Coordinate a, Coordinate b){

        return new Coordinate(a.coord + b.coord);
    }

    public static Coordinate substract(Coordinate a, Coordinate b){

        return new Coordinate(a.coord - b.coord);
    }
}
