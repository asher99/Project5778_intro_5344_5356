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

// ***************** Constructors ********************** //

    public Grid(Geometries geo, Point3D org, int d) {
        grid = voxelisation(geo);

        origin = org;

        delta = d;

    }

    // ***************** Getters/Setters ********************** //

    // ***************** Operations ******************** //

    /**
     * gets a list of geometries of the scene and adds them to the voxels
     * and returns a map of all the voxels that build the scene
     *
     * @param geo
     * @return
     */
    public HashMap<Point3D, Voxel> voxelisation(Geometries geo) {
        return null;    //  To Do
    }
    

}
