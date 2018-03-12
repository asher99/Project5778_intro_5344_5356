import com.sun.source.util.SourcePositions;
import primitives.Vector;

public class mainTargil1 {

    public static void main(String[]args){

        Vector vector1 = new Vector(0,1,5.5);
        Vector vector2 = new Vector (0,1,5.5);

        // equals Example
        System.out.println(vector1.equals(vector2));
        System.out.println(vector1.toString());
        System.out.println(vector2.toString());

        // cross product Example
        Vector vector3 = crossProduct(vector1,vector2);
        System.out.println(vector3);

        Vector vector4 = new Vector(5,0.5,3);
        System.out.println(vector4.multiplyByScalar(1.6));
    }
}
