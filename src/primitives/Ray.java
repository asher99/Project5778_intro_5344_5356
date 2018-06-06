package primitives;

/**
 * class Ray.
 * ray in space is determined by a point and a direction.
 * the direction is normal vector.
 * the point is the ray origin.
 */
public class Ray {

    // Ray orientation
    protected Vector direction;
    //Ray origin
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
     * check if a point is on the ray.
     * @param otherPoint
     * @return
     */
    public boolean isOnRay(Point3D otherPoint){
        Vector offset = new Vector(point,otherPoint);
        return direction.equals(offset.normal());
    }

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
