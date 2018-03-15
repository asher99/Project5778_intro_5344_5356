package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import primitives.Point3D;
import primitives.Vector;

public class Point3DTest {

    @Test
    public void equals() {
        Point3D a = new Point3D(2,1,1);
        Point3D b = new Point3D(2,1,1);
        Point3D c = new Point3D(1,1,1);
        Point3D d = new Point3D(1,0,0);
        assertFalse(c.equals(d));
        assertFalse(b.equals(c));
        assertTrue(a.equals(b));
    }

    @Test
    public void add() {
    }

    @Test
    public void substract() {
    }

    @Test
    public void distance() {
    }
}