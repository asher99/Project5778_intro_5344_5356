package _3DDDA_Tests;

import geometries.Geometries;
import geometries.Triangle;
import grid.Grid;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;

public class gridTest {


    @Test
    public void findVoxel(){

        Geometries geos = new Geometries();

        Grid myGrid = new Grid(geos,new Point3D(-10,-10,-10),5,4);

        //System.out.println(myGrid.getGrid());

        Triangle a = new Triangle(
                new Point3D(0, 0, 0),//bottom left
                new Point3D(2, 0, 0),//bottom right
                new Point3D(0, 2, 0),//top right
                new Color(40, 40, 40), new Material(1, 1, 20));




        myGrid.getGrid().get(new Point3D(0,0,0)).addGeometries(a);


    }

}
