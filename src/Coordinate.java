


public class Coordinate {

public double coord;

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
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
