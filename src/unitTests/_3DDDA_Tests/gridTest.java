package _3DDDA_Tests;

import geometries.Geometries;
import grid.Grid;
import org.junit.Test;
import primitives.Point3D;

public class gridTest {


    @Test
    public void findVoxel(){

        Geometries geos = new Geometries();

        Grid myGrid = new Grid(geos,new Point3D(-10,-10,-10),5,4);

        //System.out.println(myGrid.getGrid());


    }

}
