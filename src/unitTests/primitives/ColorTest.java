package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest {

    @Test
    public void add() {
        // test1: add multiple colors, all values are legal, no overflow of 255 is expected.
        Color color1 = new Color(0,0,0);
        Color color2 = new Color(30,100,150);
        Color color3 = new Color(60,0,0);
        Color color4 = new Color(80,125,25);

        Color expectedColor = new Color(170,225,175);
        color1.add(color2,color3,color4);

        assertTrue(color1.equals(expectedColor));
        System.out.println("Test1 succeeded");

        // test2: test if we treat overflows over 255 correctly.
        Color color5 = new Color(255,0,0);
        color1.add(color5);

        Color expectedOverflowColor = new Color(255,225,175);

        assertTrue(color1.equals(expectedOverflowColor));
        System.out.println("Test2 succeeded");

    }

    @Test
    public void scale() {
        // Test1
        Color color1 = new Color(30,30,30);
        Color expectedColor1 = new Color(90,90,90);
        color1.scale(3);

        assertTrue(color1.equals(expectedColor1));
        System.out.println("Test1 succeeded");

        // Test2, check for overflows.
        Color color2 = new Color(50,100,150);
        Color expectedColor2 = new Color(100,200,255);
        color2.scale(2);

        assertTrue(color2.equals(expectedColor2));
        System.out.println("Test2 succeeded");

    }

    @Test
    public void reduce() {
        // Test1
        Color color1 = new Color(255,255,255);
        Color expectedColor1 = new Color(125,125,125);
        color1.reduce(130);

        assertTrue(color1.equals(expectedColor1));
        System.out.println("Test1 succeeded");

        //Test, check for underflows.
        Color color2 = new Color(50,100,150);
        Color expectedColor2 = new Color(0,0,50);
        color2.reduce(100);

        assertTrue(color2.equals(expectedColor2));
        System.out.println("Test2 succeeded");


    }

    @Test
    public void equals() {

        Color color1 = new Color(100,0,255);
        Color color2 = new Color(100,0,255);
        Color color3 = new Color(0,255,255);

        assertTrue(color1.equals(color2));
        assertTrue(color2.equals(color1));
        assertFalse(color1.equals(color3));
        System.out.println("Tests succeeded");

    }
}