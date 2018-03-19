package elements;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

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

    /**
     * a Ray that goes throw the spicifc pixel
     *
     * @param Nx
     * @param Ny
     * @param i
     * @param j
     * @param screenDistance
     * @param screenHight
     * @return Ray
     */
    public Ray throghPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenHight) {
        return null;
    }
}
