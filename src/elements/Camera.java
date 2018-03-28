package elements;

import primitives.Point2D;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

import java.awt.*;

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
        vUp = up.normal();
        vTo = to.normal();
        vRight = (Vector.crossProduct(up, to)).normal();
    }

    /**
     * a Ray that goes throw the spicifc pixel
     *
     * @param Nx             - Number of Pixels on X axis
     * @param Ny             - Number of Pixels on Y axis
     * @param i              - index of pixel on X axis
     * @param j              - index of pixel on Y axis
     * @param screenDistance - distance of Matrix from the camera point.
     * @param screenHeight   - height of matrix
     * @param screenWidth    - width of matrix
     * @return Ray
     */
    public Ray throghPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenHeight, double screenWidth) {

        // pointCenter is the point that represent the center of the matrix. pointCenter = p0 + d*vTo.
        Point3D pointCenter = Point3D.add(p0, (vTo.multiplyByScalar(screenDistance)).getVector());

        // finding height and width of pixel.
        double pixelHeight = screenHeight / Ny;
        double pixelWidth = screenWidth / Nx;

        // finding Pij
        Point3D temp = centerOfPixel(i,j,Nx,Ny,pixelHeight,pixelWidth,pointCenter);

        // finding the direction of the ray.
        Vector vectorTowardsPixel = new Vector(Point3D.subtract(temp,p0));

        return new Ray(p0,vectorTowardsPixel.normal());
    }


    Point3D centerOfPixel(int i, int j, int Nx, int Ny, double pHeight, double pWidth, Point3D centerOfMatrix){

        double xPosition = 0, yPosition = 0;

        if (Nx % 2 == 0){
            xPosition = (i- Nx/2)*pWidth - pWidth/2;
        }
        else{
            xPosition = (i- Nx/2)*pWidth;
        }

        if (Ny % 2 == 0){
            yPosition = (j- Ny/2)*pHeight - pHeight/2;
        }
        else{
            yPosition = (j- Ny/2)*pHeight;
        }

        // finding the vectors in x axis and y axis.
        Vector vUpMovment = vUp.multiplyByScalar(-yPosition);
        Vector vRightMovment = vRight.multiplyByScalar(xPosition);

        // we add the sum of the vectors to the center of the matrix.
        return Point3D.add(Point3D.add(centerOfMatrix,vUpMovment.getVector()),vRightMovment.getVector());
    }
}
