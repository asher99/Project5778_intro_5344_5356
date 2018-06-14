package _3DDDA_Tests;

import geometries.Geometries;
import geometries.Triangle;
import grid.Grid;
import grid.Voxel;
import org.junit.Test;
import org.omg.CORBA.portable.ValueOutputStream;
import primitives.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void rayTrace(){

        Geometries geos = new Geometries();
        Grid myGrid = new Grid(geos,new Point3D(-10,-10,-10),5,4);

        Ray testRay1 = new Ray(new Point3D(-20,0,1),new Vector(1,1,1));
        Ray testRay2 = new Ray(new Point3D(9,9,9),new Vector(1,0,0));
        Ray testRay3 = new Ray(new Point3D(5,5,8),new Vector(0,0,-1));
        Ray testRay4 = new Ray(new Point3D(-8,-8,-8),new Vector(1,1,1));
        Ray testRay5 = new Ray(new Point3D(8,0,0),new Vector(1,1,0));
        Ray testRay6 = new Ray(new Point3D(8,0,0),new Vector(1,10,0));

        List voxelList1 = myGrid.rayTrace(testRay1);
        List voxelList2 = myGrid.rayTrace(testRay2);
        List voxelList3 = myGrid.rayTrace(testRay3);
        List voxelList4 = myGrid.rayTrace(testRay4);
        List voxelList5 = myGrid.rayTrace(testRay5);
        List voxelList6 = myGrid.rayTrace(testRay6);

        System.out.println(voxelList1);
        System.out.println(voxelList2);
        System.out.println(voxelList3);
        System.out.println(voxelList4);
        System.out.println(voxelList5);
        System.out.println(voxelList6);


    }

}
