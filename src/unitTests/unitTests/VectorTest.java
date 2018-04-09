package unitTests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void vectorialsubtract() {

        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,4,6);
        Vector c = Vector.Vectorialsubtract(b,a);
        assertTrue(c.equals(a));

    }

    @Test
    public void vectorialAdd() {
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,4,6);
        Vector c = Vector.VectorialAdd(a,a);
        assertTrue(c.equals(b));
    }

    @Test
    public void multiplyByScalar() {

        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,4,6);
        Vector c = a.multiplyByScalar(2);
        assertTrue(c.equals(b));

        Vector e = new Vector(3,6,9);
        Vector f = e.multiplyByScalar(0.33333333333);
        Vector g = new Vector(2,3,4);

        System.out.println(f.toString());
        assertTrue(f.equals(a));
        assertTrue(f.equals(b));
        assertFalse(f.equals(g));


    }

    @Test
    public void dotProduct() {

        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,-4,6);
        Vector c = new Vector(-1,0.5,3);
        assertTrue(Vector.dotProduct(a,b) == 12 );
        assertTrue(Vector.dotProduct(c,b) == 14 );

        Vector e = new Vector(6,0,3);
        Vector f = new Vector(1,2,-2);
        assertTrue(Vector.dotProduct(e,f) == 0 );


    }

    @Test
    public void crossProduct() {

        Vector a = new Vector(1,-2,3);
        Vector b = new Vector(2,-4,6);
        Vector c = Vector.crossProduct(a,b);
        Vector d = new Vector(0,0,0);

        assertTrue(c.equals(d));

    }

    @Test
    public void sizeOfVector() {

        Vector a = new Vector(0,-3,4);
        assertTrue(a.sizeOfVector()== 5);

    }

    @Test
    public void normal() {
       // Vector a = new Vector(0,0,0);
        Vector b = new Vector(1,0,0);
        Vector c = new Vector(1,2,-2);
        Vector d = new Vector(0.33333333,0.666666,-0.666666);

       // assertTrue(a.normal().equals(a));
        assertTrue(b.normal().equals(b));
        assertTrue(c.normal().equals(d));

    }

    @Test
    public void equals() {
    }
}