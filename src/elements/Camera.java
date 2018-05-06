package elements;

import primitives.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * class Camera.
 * represent a camera in the scene.
 * the camera has location (point in space) and orientation ( vector up, vector to).
 */
public class Camera {

    Point3D p0;
    Vector vUp;
    Vector vTo;
    Vector vRight;
    // ***************** Constructor ********************** //

    /**
     * constructor.
     * get the point in the 3D scene where the camera is,
     * and also two vectors of the camera orientation.
     * the third is calculated automatically.
     * <p>
     * we use the method "setCameraLocation"
     *
     * @param point
     * @param up
     * @param to
     */
    public Camera(Point3D point, Vector up, Vector to) {
        setCameraLocation(point, up, to);
    }

    /**
     * a Ray that goes throw the spicifc pixel
     *
     * @param Nx             - Number of Pixels on X axis
     * @param Ny             - Number of Pixels on Y axis
     * @param i              - index of pixel on X axis, left to right.
     * @param j              - index of pixel on Y axis, up to down.
     * @param screenDistance - distance of Matrix from the camera point.
     * @param screenHeight   - height of matrix
     * @param screenWidth    - width of matrix
     * @return Ray from the camera position thorugh a pixel (i,j).
     */
    public Ray ConstractRaythroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenHeight, double screenWidth) {

        // pointCenter is the point that represent the center of the matrix. pointCenter = p0 + d*vTo.
        Point3D pointCenter = Point3D.add(p0, (vTo.multiplyByScalar(screenDistance)).getVector());

        // finding height and width of pixel.
        double pixelHeight = screenHeight / Ny;

        double pixelWidth = screenWidth / Nx;

        // finding Pij
        Point3D temp = centerOfPixel(i, j, Nx, Ny, pixelHeight, pixelWidth, pointCenter);

        // finding the direction of the ray.
        Vector vectorTowardsPixel = new Vector(Point3D.subtract(temp, p0));

        return new Ray(p0, vectorTowardsPixel.normal());
    }


    /**
     * calculated the center of the pixel in which the ray pass.
     *
     * @param i              index of pixel in the horizontal axis, right to left.
     * @param j              index of pixel in the horizontal axis, up to down.
     *                       both i and j starts from 1.
     * @param Nx             number of pixels in the horizontal axis.
     * @param Ny             number of pixels in the vertcal axis.
     * @param pHeight        piexl height
     * @param pWidth         pixel width
     * @param centerOfMatrix the point in space that represent the center of the matrix.
     * @return a point in space which is the center of pixel (i,j).
     */
    public Point3D centerOfPixel(int i, int j, int Nx, int Ny, double pHeight, double pWidth, Point3D centerOfMatrix) {

        double xPosition = 0, yPosition = 0;

        xPosition = (i - (Nx * 0.5)) * pWidth - pWidth * 0.5;
        yPosition = (j - (Ny * 0.5)) * pHeight - pHeight * 0.5;

       /* if (Nx % 2 == 0) {
            xPosition = (i - Nx * 0.5) * pWidth - pWidth * 0.5;
        } else {
            xPosition = (i - Nx * 0.5) * pWidth;
        }

        if (Ny % 2 == 0) {
            yPosition = (j - Ny * 0.5) * pHeight - pHeight * 0.5;
        } else {
            yPosition = (j - Ny * 0.5) * pHeight;
        }*/

        // finding the vectors in x axis and y axis.
        Vector vUpMovment = vUp.multiplyByScalar(-1 * yPosition);
        Vector vRightMovment = vRight.multiplyByScalar(xPosition);

        // we add the sum of the vectors to the center of the matrix.
        return Point3D.add(Point3D.add(centerOfMatrix, vUpMovment.getVector()), vRightMovment.getVector());

    }

    /*************getters/setters**********************/

    /**
     * this is the setter for all the camera position parameters.
     * it is wrong to let the user set only one field:
     * for example: if we set only the vUp, there are limitless possibilities for the vTo and vRight.
     * we can't ignore changing them - because we must keep the view plane vectors orthogonal.
     *
     * @param myP0
     * @param myVup
     * @param myVto
     */
    public void setCameraLocation(Point3D myP0, Vector myVup, Vector myVto) {
        if (myVup.sizeOfVector() == 0 || myVto.sizeOfVector() == 0)
            throw new ArithmeticException("the Vectors cannot be zero length.");
        if (Vector.dotProduct(myVup, myVto) != 0)
            throw new ArithmeticException("the Vectors are not otrhgonal!");

        p0 = myP0;
        vUp = myVup.normal();
        vTo = myVto.normal();
        vRight = (Vector.crossProduct(myVup, myVto)).normal();
    }

    /**
     * getter
     *
     * @return
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getter
     *
     * @return
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getter
     *
     * @return
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * return the camera position.
     *
     * @return
     */
    public Point3D getP0() {
        return p0;
    }
}
