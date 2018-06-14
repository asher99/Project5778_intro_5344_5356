package _3DDDA_Tests;

import geometries.Geometries;
import geometries.Triangle;
import grid.Grid;
import grid.Voxel;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;

import java.util.HashMap;

public class gridTest {


    @Test
    public void findVoxel(){

        Geometries geos = new Geometries();

        Grid myGrid = new Grid(geos,new Point3D(-10,-10,-10),5,4);
        Point3D a = myGrid.findVoxel(new Point3D(-18,19,13));

        Voxel v = myGrid.getGrid().get(a);
        System.out.println(v);


        /*HashMap<Point3D,Voxel> hashMap = new HashMap<>();
        Voxel v = new Voxel(new Point3D(0,0,0),200,geos);

        hashMap.put(new Point3D(0,0,0),v);
        Voxel test = hashMap.get(new Point3D(0,0,0));

        System.out.println(test);*/





    }

}
