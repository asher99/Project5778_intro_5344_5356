package unitTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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

}