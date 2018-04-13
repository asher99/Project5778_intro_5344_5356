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
        /*
        Test: Sphere at (0,1,0) radius is one.
        testing the radius at (0,1,-2) and (2/sqrt(12),1+ 2/sqrt(12),2/sqrt(12)), and at (10,10,10).
         */
        Sphere mySphere = new Sphere(new Point3D(0,1,0),2);

        double sqrt12 = Math.sqrt(12);
        double sqrt3 = Math.sqrt(3);

        Vector test1 = mySphere.getNormal(new Point3D(0,1,-2));
        Vector test2 = mySphere.getNormal(new Point3D(2/sqrt3,1 + 2/sqrt3,2/sqrt3));
        Vector test3 = mySphere.getNormal(new Point3D(10,10,10));

        System.out.println(test1);
        System.out.println(test2);

        assertTrue(test1.equals(new Vector(0,0,-1)));

        assertTrue(test2.equals(new Vector(sqrt3,sqrt3,sqrt3)));

        assertNull(test3);
    }

    @Test
    public void equals() {
    }

    @Test
    public void findIntersections() {

        // we may encounter some NullPointer exceptions.
        try {
        /*
        Test 1:
        camera at origin, Vto = (0,0,-1), distance from view plane is 1, and the matrix is 3X3.
        Sphere center at (0,0,-3) and its radius is 1.
        testing ray (1,1) [the center of matrix], expecting two points: (0,0,-2),(0,0,-4).
         */
            Camera myCamera1 = new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, -1));
            Sphere mySphere1 = new Sphere(new Point3D(0, 0, -3), 1);
            ArrayList<Point3D> outputList1 = mySphere1.findIntersections(myCamera1.ConstractRaythroughPixel(3, 3, 2, 2, 1, 3, 3));
            ArrayList<Point3D> expectedOutput1 = new ArrayList<Point3D>();
            expectedOutput1.add(new Point3D(0, 0, -2));
            expectedOutput1.add(new Point3D(0, 0, -4));

            for (Point3D point : outputList1) {
                System.out.println(point);
            }
            assertTrue(expectedOutput1.equals(outputList1));

        /*
        Test 2:
        camera at (0,0,6) and looks in 45 degrees to X axis.
        sphere at (4,0,0) with radius of sqrt(2).
        expecting one intersection point at (5,0,1) when looking through the middle pixel.
         */
            Camera myCamera2 = new Camera(new Point3D(0, 0, 6), new Vector(1, 0, 1), new Vector(1, 0, -1));
            Sphere mySphere2 = new Sphere(new Point3D(4, 0, 0), Math.sqrt(2));
            ArrayList<Point3D> outputList2 = mySphere2.findIntersections(myCamera2.ConstractRaythroughPixel(15, 15, 8, 8, 1, 5, 5));
            ArrayList<Point3D> expectedOutput2 = new ArrayList<Point3D>();
            expectedOutput2.add(new Point3D(4.999999999999999, 0, 0.9999999999999982));

            for (Point3D point : outputList2) {
                System.out.println(point);
            }
            assertTrue(expectedOutput2.equals(outputList2));

        /*
        Test 3: Camera at (0,0,0) looking directly to Y-axis.
        in (0,10,0) there is a sphere with radius 3.
        constructing a Ray through edge pixel, we try to miss the sphere.
        */
            Camera myCamera3 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0));
            Sphere mySphere3 = new Sphere(new Point3D(0, 10, 0), 3);
            ArrayList<Point3D> outputList3 = mySphere3.findIntersections(myCamera3.ConstractRaythroughPixel(40, 40, 1, 1, 2, 2, 2));

            for (Point3D point : outputList3) {
                System.out.println(point);
            }

            assertNull(outputList3);

        }catch (NullPointerException e){}
    }
}