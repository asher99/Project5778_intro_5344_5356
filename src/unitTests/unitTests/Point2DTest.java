package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import primitives.Point2D;

public class Point2DTest {


    @Test
    public void equals() {

        Point2D a = new Point2D(1,1);
        Point2D b = new Point2D(2,2);
        Point2D c = new Point2D(2,2);

        assertTrue( b.equals(c));
        assertFalse(a.equals(b));
    }

    @Test
    public void substract() {
    }

    @Test
    public void distance() {
    }
}