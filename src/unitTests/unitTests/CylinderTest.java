package unitTests;

import geometries.Cylinder;
import org.junit.Test;

import static org.junit.Assert.*;

import primitives.*;


public class CylinderTest {

    @Test
    public void getNormal() {

        /*
        test 1. the cylinder is the Z axis with radius of 2.
        test point on surface when Y=0 and X >0, expect for (1,0,0).
        in this test, the point is "in front".
         */
        Ray cylinderOrientation1 = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
        Cylinder cylinder1 = new Cylinder(2, cylinderOrientation1);

        Vector testVector1 = cylinder1.getNormal(new Point3D(2, 0, 5));
        Vector expectedVector1 = new Vector(1, 0, 0);

        System.out.println("test1 output:");
        System.out.println(testVector1);
        assertTrue(testVector1.equals(expectedVector1));

        /*
        test 2. the cylinder is parallel (above) to the Y axis with radius of 3.
        but it is not intersect with any axis.
        testing a point in the bottom of the cylinder, expect for (0,0,-1).
        in this test, the point is "behind".
         */
        Ray cylinderOrientation2 = new Ray(new Point3D(2, 10, 4), new Vector(0, 5, 0));
        Cylinder cylinder2 = new Cylinder(3, cylinderOrientation2);

        Vector testVector2 = cylinder2.getNormal(new Point3D(2, -179, 1));
        Vector expectedVector2 = new Vector(0, 0, -1);

        System.out.println("\ntest2 output:");
        System.out.println(testVector2);
        assertTrue(testVector2.equals(expectedVector2));
    }

    @Test
    public void findIntersections(){

    }
}
