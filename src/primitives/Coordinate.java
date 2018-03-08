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

    // ***************** Getters/Setters ********************** //
    public double getCoord() {
        return coord;
    }

    public void setCoord(double coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return String.valueOf(coord);
    }

    public boolean equals(Coordinate other) {
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
