package grid;

import geometries.Geometries;
import primitives.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Grid for a 3D grid in the scene.
 * The Grid made of many 3d cells - Voxels.
 * Also, size of each voxel edge is common to all Voxels and stored in the grid object.
 */
public class Grid {

    // hash map that holds all voxels of the scene
    HashMap<Point3D, Voxel> grid;

    // the origin of the grid (starting point)
    Point3D origin;

    // is the size of each voxel to construct the voxels a constant
    int delta;

    // store how many Voxels are on the length of the axis.
    int voxelsOnAxis;

// ***************** Constructors ********************** //

    /**
     * construct grid
     * @param geo
     * @param org
     * @param d
     * @param n
     */
    public Grid(Geometries geo, Point3D org, int d, int n) {

        origin = org;
        delta = d;
        voxelsOnAxis = n;
        grid = voxelisation();
    }

    // ***************** Getters/Setters ********************** //

    public HashMap<Point3D, Voxel> getGrid() {
        return grid;
    }


    // ***************** Operations ******************** //

    /**
     * initialize the grid hash-map.
     * @return
     */
    public HashMap<Point3D, Voxel> voxelisation() {

        HashMap<Point3D,Voxel> voxelHash = new HashMap<>();

        for (int z = 0; z <voxelsOnAxis; z++){
            for (int y = 0; y <voxelsOnAxis; y++){
                for (int x = 0; x <voxelsOnAxis; x++){
                    Point3D index = new Point3D(x*delta, y*delta, z*delta);
                    Point3D key = Point3D.add(origin,index);
                    voxelHash.put(key, new Voxel(origin,delta,new Geometries()));
                }
            }
        }
        return voxelHash;
    }


    /**
     * receive a point in space, and return the appropriate Voxel.
     * @param p
     * @return
     */
    public Point3D findVoxel(Point3D p){
        Point3D offset = Point3D.subtract(p,origin);
        int x = (int)(offset.getX() / delta);
        int y = (int)(offset.getY() / delta);
        int z = (int)(offset.getZ() / delta);

        return new Point3D(origin.getX() + x*delta, origin.getY() + y*delta, origin.getZ() + z*delta);
    }
    

}
