package grid;

import geometries.Geometries;
import primitives.Point3D;

/**
 *  class Voxel for a cell in the 3D grid of the scene.
 *  The voxel have an 3D index and a list of geometries in the voxel.
 *  Also, it have the ability to iterate over those geometries, and find their
 *  intersections point, normal in a specific point etc.
 *
 *  indexing the Voexls:
 *      we will use point3D - the origin of that voxel;
 *      with the grid 'delta' - we can identify our voxel with start and finish points.
 */
public class Voxel {

    Geometries voxelGeometries;
    Point3D voxelOrigin;
    Point3D voxelFinish;

}
