package unitTests;

import elements.Camera;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void getNormal() {

        Triangle A = new Triangle(
                new Point3D(1, 1, 1),
                new Point3D(2, 3, 5),
                new Point3D(4, 1, 3));
        Vector v = A.getNormal(new Point3D(1, 1, 1));
        Vector test = new Vector(-4, -10, 6);

        assertTrue(v.equals(test.normal()));
    }

    @Test
    public void equals() {
        Triangle a = new Triangle(
                new Point3D(1, 2, 3),
                new Point3D(2, 3, 5),
                new Point3D(4, 6, 7));
        Triangle b = new Triangle(
                new Point3D(1, 2, 3),
                new Point3D(4, 6, 7),
                new Point3D(2, 3, 5));

        assertTrue(a.equals(b));
    }

    @Test
    public void findIntersections(){
        /*
        Testing: Camera at (0,0,0), "up" is (0,0,1) and "to is (1,0,0). [looking to X axis]
        Triangle is at (10,-5,1), (10,5,1), (10,0,11).
        we inspect two pixels:
        Test i:     a Ray comes from the center pixel should go beneath the triangle and return empty list.
        Test ii:    a Ray comes from upper pixel should intersect with the triangle at (10,0,5).
        Test iii:   a Ray comes from the pixels in the edge of the matrix should miss the triangle too.

        Note: in Plane a Ray comes from the center pixel intersect at (10,0,0) and a Ray comes from the pixels in the edge of the matrix at (10,12.5,5).
         */
        Camera myCamera = new Camera(new Point3D(0,0,0),new Vector(0,0,1),new Vector(1,0,0));
        Triangle myTriangle = new Triangle(new Point3D(10,-5,1),new Point3D(10,5,1),new Point3D(10,0,11));

       ArrayList<Point3D> alistThroughCenter   = myTriangle.findIntersections(myCamera.ConstractRaythroughPixel(11, 3, 6, 2, 4, 6, 11));
       ArrayList<Point3D> alistThroughUpCenter = myTriangle.findIntersections(myCamera.ConstractRaythroughPixel(11, 3, 6, 1, 4, 6, 11));
       ArrayList<Point3D> alistThroughUpEdge   = myTriangle.findIntersections(myCamera.ConstractRaythroughPixel(11, 3, 11, 1, 4, 6, 11));

       assertNull(alistThroughCenter);
       assertNull(alistThroughUpEdge);

        for (Point3D p:alistThroughUpCenter) {
            System.out.println(p);
        }

        ArrayList<Point3D> expected = new ArrayList<Point3D>();
        expected.add(new Point3D(10,0,5));

        assertTrue(alistThroughUpCenter.equals(expected));
    }
}