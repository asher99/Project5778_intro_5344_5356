package primitives;

/**
 * class Ray.
 * ray in space is determined by a point and a direction.
 * the direction is normal vector.
 */
public class Ray {

    protected Vector direction;
    protected Point3D point;

    /**
     * build by vector and point.
     *
     * @param myPoint
     * @param myVector
     */
    public Ray(Point3D myPoint, Vector myVector) {

        point = myPoint;
        direction = myVector.normal();
    }

    // ***************** Getters ********************** //

    /**
     * getter
     *
     * @return
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * getter.
     *
     * @return
     */
    public Point3D getPoint() {
        return point;
    }

    // ***************** Operations ******************** //

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {

        return point.toString() + " + k" + direction.toString();
    }

    /**
     * check if another object is equal to this one.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Ray otherRay = (Ray) obj;
        return (this.point.equals(otherRay.point) && this.direction.equals(otherRay.direction));
    }
}
