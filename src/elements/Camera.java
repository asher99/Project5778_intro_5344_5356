package elements;

import primitives.Point3D;
import primitives.Vector;

/**
 * camera class
 */
public class Camera {

    Point3D p0;
    Vector vUp;
    Vector vTo;
    Vector vRight;

    // ***************** Constructor ********************** //
    public Camera(Point3D point, Vector up, Vector to) {
        if (Vector.dotProduct(up, to) != 0 || up.sizeOfVector() == 0 || to.sizeOfVector() == 0)
                throw new ArithmeticException("the Vectors are not otrhgonal!");
            p0 = point;
            vUp = up;
            vTo = to;
            vRight = Vector.crossProduct(up, to);
    }
}
