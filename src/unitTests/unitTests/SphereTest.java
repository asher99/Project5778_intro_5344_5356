package unitTests;

import elements.Camera;
import geometries.Sphere;
import org.junit.Assert;
import org.junit.Test;
import primitives.Point2D;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getCenter() {
    }

    @Test
    public void get_radius() {
    }

    @Test
    public void getNormal() {
    }

    @Test
    public void equals() {
    }

    @Test
    public void findIntersections(){
        /*
        Test 1:
        camera at origin, Vto = (0,0,-1), distance from view plane is 1, and the matrix is 3X3.
        Sphere center at (0,0,-3) and its radius is 1.
        testing ray (1,1) [the center of matrix], expecting two points: (0,0,-2),(0,0,-4).
         */
        Camera myCamera1 = new Camera(new Point3D(0,0,0),new Vector(1,0,0),new Vector(0,0,-1));
        Sphere mySphere1 = new Sphere(new Point3D(0,0,-3),1);
        ArrayList<Point3D> outputList1 = mySphere1.findIntersections(myCamera1.ConstractRaythroughPixel(3,3,1,1,1,3,3));
        ArrayList<Point3D> expectedOutput1 = new ArrayList<Point3D>();
        expectedOutput1.add(new Point3D(0,0,-2));
        expectedOutput1.add(new Point3D(0,0,-4));

        for (Point3D point:outputList1) {
            System.out.println(point);
        }
        assertTrue(expectedOutput1.equals(outputList1));

        /*
        Test 2:

         */
    }
}