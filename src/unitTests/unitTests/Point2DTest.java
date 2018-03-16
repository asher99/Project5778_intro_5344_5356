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
    public void subtract() {
        Point2D a = new Point2D(1,1);
        Point2D b = new Point2D(2,2);
        Point2D c = new Point2D(2,2);
        Point2D d = Point2D.subtract(b,c);
        assertTrue(d.getX() == 0.0 && d.getY() == 0.0);
    }

    @Test
    public void distance() {
        Point2D a = new Point2D(1,1);
        Point2D b = new Point2D(2,2);
        Point2D c = new Point2D(2,2);
        assertTrue(Point2D.distance(b,c)==0 );
        assertTrue(Point2D.distance(a,c)== Math.sqrt(2));



    }
}