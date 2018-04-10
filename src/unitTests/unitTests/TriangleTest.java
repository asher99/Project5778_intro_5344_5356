package unitTests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
}