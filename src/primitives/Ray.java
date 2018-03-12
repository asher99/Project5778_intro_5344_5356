package primitives;

/**
 * class Ray.
 * ray in space is determined by a point and a direction.
 * the direction is normal vector.
 */
public class Ray {

    protected Vector direction;
    protected Point3D point;

    //build by vector and point.
    public Ray(Point3D myPoint, Vector myVector){

        point = myPoint;
        direction = myVector.normal();
    }

    // ***************** Getters ********************** //
    public Vector getDirection() {
        return direction;
    }

    public Point3D getPoint() {
        return point;
    }

    // ***************** Operations ******************** //
    @Override
    public String toString() {
        return point.toString() + " + k" + direction.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Ray otherRay = (Ray)obj;
        return (this.point.equals(otherRay.point) && this.direction.equals(otherRay.direction));
    }
}
