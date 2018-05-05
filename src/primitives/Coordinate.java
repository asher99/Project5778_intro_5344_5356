package primitives;

import javafx.scene.layout.CornerRadii;


/**
 * class Coordinate for a single coordinate on a single axis.
 */

public class Coordinate {

    // the coordinate value
    private double coord;

    // It is binary, equivalent to ~1/1,000,000 in decimal (6 digits)
    private static final int ACCURACY = -20;

    /**
     * double store format: seee eeee eeee (1.)mmmm … mmmm
     * 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
     *
     * @param num
     * @return int
     */
    private static int getExp(double num) {
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }


    // ***************** Constructor ********************** //

    /**
     * constructor
     * @param newCoord
     */
    public Coordinate(double newCoord) {
        double expo = getExp(newCoord);
        this.coord = (expo < ACCURACY) ? 0.0 : newCoord;
        //this.coord = newCoord;
    }

    /**
     * kind of copy constructor
     * @param newCoord
     */
    public Coordinate(Coordinate newCoord) {
        coord = newCoord.getCoord();
    }

    // ***************** Getters ********************** //

    /**
     * getter
     * @return
     */
    public double getCoord() {
        return coord;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(coord);
    }

    /**
     * check if two Coordinates are equal.
     *
     * @param other
     * @return boolean
     */
    public boolean equals(Coordinate other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Coordinate)) return false;
        return subtract(this, other).coord == 0;
    }

    // ***************** Operations ******************** //

    /**
     * receive two Coordinate object, and return a new object.
     * the new Coordinate "coord" field is the subtract of the original "coord" fields.
     *
     * @param a
     * @param b
     * @return
     */
    public static Coordinate subtract(Coordinate a, Coordinate b) {
        int otherExp = getExp(b.coord);
        int thisExp = getExp(a.coord);

        // if other is too small relatively to our coordinate return the original coordinate
        if (otherExp - thisExp < ACCURACY) return a;

        // if our coordinate is too small relatively to other return negative of other
        if (thisExp - otherExp < ACCURACY) return new Coordinate(-b.coord);
        double result = a.coord - b.coord;

        int resultExp = getExp(result);     // if the result is too small tell that it is zero
        return resultExp - thisExp < ACCURACY ? new Coordinate(0.0) : new Coordinate(result);
    }

    /**
     * receive two Coordinate object, and return a new object.
     * the new Coordinate "coord" field is the subtract of the original "coord" fields.
     *
     * @param a
     * @param b
     * @return
     */
    public static Coordinate add(Coordinate a, Coordinate b) {
        int otherExp = getExp(b.coord);
        int thisExp = getExp(a.coord);

        // if other is too small relatively to our coordinate return the original coordinate
        if (otherExp - thisExp < ACCURACY) return a;

        // if our coordinate is too small relatively to other return the other
        if (thisExp - otherExp < ACCURACY) return b;
        double result = a.coord + b.coord;

        int resultExp = getExp(result);         // if the result is too small tell that it is zero
        return resultExp - thisExp < ACCURACY ? new Coordinate(0.0) : new Coordinate(result);
    }
}
