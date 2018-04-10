package unitTests;

import elements.Camera;
import org.junit.Test;
import primitives.*;

import java.lang.Math.*;

import static org.junit.Assert.*;

public class CameraTest {

    @Test
    public void throughPixel() {

       /* Point3D cameraPosition = new Point3D(0, 0, 0);
        Vector up = new Vector(0, 0, 1);
        Vector to = new Vector(1, 0, 0);
        Camera myCamera = new Camera(cameraPosition, up, to);
        Ray myRay = myCamera.ConstractRaythroughPixel(3, 3, 0, 0, 2, 6, 6);

        System.out.println(myRay);
        Point3D a = new Point3D(0, 0, 0);
        double sqrtOf3 = Math.sqrt(3);
        Vector vec = new Vector(sqrtOf3/3, sqrtOf3/3, sqrtOf3/3);
        Ray myTestRay = new Ray(a, vec);

        assertTrue(myRay.equals(myTestRay));*/

        // second test for even number
        Point3D cameraPosition2 = new Point3D(7, 1, -3);
        Vector up2 = new Vector(6, 0, 3);
        Vector to2 = new Vector(1, 2, -2);
        Camera myCamera2 = new Camera(cameraPosition2, up2, to2);
        Ray myRay2 = myCamera2.ConstractRaythroughPixel(40, 40, 8, 39, 10, 12, 12);

        System.out.println(myRay2);
        Point3D b = new Point3D(7, 1, -3);
        Vector vec2 = new Vector(-0.04259754999708888,0.3216671361046757,-0.9458941284755191);
        Ray myTestRay2 = new Ray(b, vec2);



        assertTrue(myRay2.equals(myTestRay2));

    }


    @Test
    public void CenterOfPixel() {
       /* // test 1
        int Nx = 40;
        int Ny = 40;
        int i = 8;
        int j = 39;
        double pixelHeight = 12.0 / Nx;
        double pixelWidth = 12.0 / Nx;
        Point3D cameraPosition2 = new Point3D(7, 1, -3);
        Vector up2 = new Vector(6, 0, 3);
        Vector to2 = new Vector(1, 2, -2);
        Camera myCamera2 = new Camera(cameraPosition2, up2, to2);

        Point3D pointCenter = new Point3D(17, 21, -23);
        Point3D temp = myCamera2.centerOfPixel(i, j, Nx, Ny, pixelHeight, pixelWidth, pointCenter);
        Point3D myPoint = new Point3D(13.153963078700363, 18.204915028125264, -27.718103432524558);
        assertTrue(myPoint.equals(temp));
*/
        // test 2
        Camera myCamera3 = new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(1,0,0));
        Point3D output = myCamera3.centerOfPixel(0,0,3,3,2,2,new Point3D(2,0,0));
        System.out.println(output);

    }

}