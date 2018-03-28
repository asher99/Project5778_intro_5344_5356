/**
 * Targil1 example
 * By Asher Alexander & Zvei Eliezer Nir
 * Email: asherb94@gmail.com   zveialiezer04@gmail.com
 * ID: 206195356            316525344
 */


import com.sun.source.util.SourcePositions;
import primitives.*;
import geometries.*;

public class mainTargil1 {

    public static void main(String[] args) {

        try {

            Vector vector1 = new Vector(0, 1, 5.5);
            Vector vector2 = new Vector(0, 1, 5.5);

            // equals Example
            System.out.println(vector1.equals(vector2));
            System.out.println(vector1.toString());
            System.out.println(vector2.toString());

            // cross product Example
            Vector vector3 = Vector.crossProduct(vector1, vector2);
            System.out.println(vector3.toString());

            // multiply by scalar Example
            Vector vector4 = new Vector(5, 0.5, 3);
            System.out.println(vector4.multiplyByScalar(1.6));

            // size of vector Example
            System.out.println(vector1.sizeOfVector());

            // dot product Example
            System.out.println(Vector.dotProduct(vector1, vector4));


            Triangle myTriangle = new Triangle(new Point3D(1, 0, 0),
                    new Point3D(0, 3, 3),
                    new Point3D(6, 0, 1));

            System.out.println(myTriangle);


            Plane myPlain = new Plane(new Point3D(18, 4, 13), new Vector(2, 0, 6));

            Sphere mySphere = new Sphere(new Point3D(0, 0, 5), 5);

            Cylinder myCylinder = new Cylinder(2,
                    new Ray(new Point3D(1, 1, 5),
                            new Vector(2, 2, 0)));

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}
