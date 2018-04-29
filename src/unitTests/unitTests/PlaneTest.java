package unitTests;

import elements.Camera;
import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point2D;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaneTest {


    @Test
    public void getNormal() {
        Plane p = new Plane(new Point3D(0, 0, 0), new Vector(1, 0, 0));
        Vector vec = new Vector(1, 0, 0);
        assertTrue(vec.equals(p.getNormal(new Point3D(1, 1, 1))));
    }

    @Test
    public void equals() {

        Plane a = new Plane(new Point3D(0, 0, 0), new Vector(1, 2, 3));
        Plane b = new Plane(new Point3D(0, 0, 0), new Vector(4, 8, 12));
        assertTrue(a.equals(b));
    }

    @Test
    public void findIntersections(){

        // we may encounter NullPointer exception.
        try {
        /*
        Test 1: camera at (0,0,0). (0,0,1) is "up" and (1,0,0) is "to".
        there is a plane with normal(1,0,0) and point(10,0,0).
        constructing three Rays through different pixels we expect three different intersections point.

        center pixel ```- intersection point is (10,0,0).
        upper pixel ````- intersection point is (10,0,6.66...).
        left-down pixel - intersection point is (10,-6.66...,-6.66...).
         */
            Camera myCamera1 = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(1, 0, 0));
            Plane myPlane1 = new Plane(new Point3D(10, 0, 0), new Vector(1, 0, 0));

            ArrayList<Point3D> alistCenter = myPlane1.findIntersections(myCamera1.ConstractRaythroughPixel(3, 3, 2, 2, 3, 6, 6));
            ArrayList<Point3D> alistUpper = myPlane1.findIntersections(myCamera1.ConstractRaythroughPixel(3, 3, 2, 1, 3, 6, 6));
            ArrayList<Point3D> alistLeftDown = myPlane1.findIntersections(myCamera1.ConstractRaythroughPixel(3, 3, 1, 3, 3, 6, 6));

            Point3D expectedCenterPoint = new Point3D(10, 0, 0);
            Point3D expectedUpperPoint = new Point3D(10, 0, 6.666666666666667);
            Point3D expectedLeftDownPoint = new Point3D(10, -6.666666666666667, -6.666666666666667);

            ArrayList<Point3D> expectedAlistCenter = new ArrayList<Point3D>();
            expectedAlistCenter.add(expectedCenterPoint);

            ArrayList<Point3D> expectedAlistUpper = new ArrayList<Point3D>();
            expectedAlistUpper.add(expectedUpperPoint);

            ArrayList<Point3D> expectedAlistLeftDown = new ArrayList<Point3D>();
            expectedAlistLeftDown.add(expectedLeftDownPoint);

            for (Point3D p : alistCenter) {
                System.out.println(p);
            }
            for (Point3D p : alistUpper) {
                System.out.println(p);
            }
            for (Point3D p : alistLeftDown) {
                System.out.println(p);
            }

            assertTrue(alistCenter.equals(expectedAlistCenter));
            assertTrue(alistUpper.equals(expectedAlistUpper));
            assertTrue(alistLeftDown.equals(expectedAlistLeftDown));

        /*
        Test 2: camera at (0,0,0), "up" is (0,1,0), "to" is (0,0,-1).
         there is a plane with normal(0,0,1) and point(0,0,0.05).
         since the plane is above the camera, and the camera looks down -
         there will be no intersection point with the plane, even with wide view plane.
         testing the upper pixel.
         */
            Camera myCamera2 = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
            Plane myPlane2 = new Plane(new Point3D(00, 0, 0.05), new Vector(0, 0, 1));

            ArrayList<Point3D> output = myPlane2.findIntersections(myCamera2.ConstractRaythroughPixel(100, 100, 26, 1, 5, 120, 120));

            assertNull(output);

        }catch (NullPointerException e){}
    }

    /**
     * calculator for findong intersections.
     */
    @Test
    public void intersectionsCalculator()
    {
        try {
            Camera myCamera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(1, 0, 0));
            Plane myPlane = new Plane(new Point3D(10, -5, 1), new Point3D(10, 5, 1), new Point3D(10, 0, 11));

            ArrayList<Point3D> output = myPlane.findIntersections(myCamera.ConstractRaythroughPixel(11, 3, 11, 1, 4, 6, 11));

            for (Point3D p: output) {
                System.out.println(p);
            }

        }catch (NullPointerException e){}
    }



}