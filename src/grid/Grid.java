package grid;

import geometries.Geometries;
import geometries.Geometry;
import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * Class Grid for a 3D grid in the scene.
 * The Grid made of many 3d cells - Voxels.
 * Also, size of each voxel edge is common to all Voxels and stored in the grid object.
 */
public class Grid {

    // hash map that holds all voxels of the scene
    HashMap<Point3D, Voxel> grid;
    Geometries backgroundGeometries;

    // the origin of the grid (starting point)
    Point3D origin;

    // is the size of each voxel to construct the voxels a constant
    int delta;

    // store how many Voxels are on the length of the axis.
    int voxelsOnAxis;

// ***************** Constructors ********************** //

    /**
     * construct grid
     *
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
        backgroundGeometries = new Geometries();
    }

    // ***************** Getters/Setters ********************** //

    public HashMap<Point3D, Voxel> getGrid() {
        return grid;
    }

    /**
     * get origin of the grid.
     *
     * @return
     */
    public Point3D getOrigin() {
        return origin;
    }

    // ***************** Operations ******************** //

    /**
     * initialize the grid hash-map.
     *
     * @return
     */
    public HashMap<Point3D, Voxel> voxelisation() {

        HashMap<Point3D, Voxel> voxelHash = new HashMap<>(voxelsOnAxis * voxelsOnAxis * voxelsOnAxis);

        for (int z = 0; z < voxelsOnAxis; z++) {
            for (int y = 0; y < voxelsOnAxis; y++) {
                for (int x = 0; x < voxelsOnAxis; x++) {
                    Point3D index = new Point3D(x * delta, y * delta, z * delta);
                    Point3D key = Point3D.add(origin, index);
                    Voxel value = new Voxel(key, delta, new Geometries());
                    voxelHash.put(key, value);
                }
            }
        }
        return voxelHash;
    }


    /**
     * receive a point in space, and return the appropriate Voxel.
     *
     * @param p
     * @return
     */
    public Point3D findVoxel(Point3D p) {
        Point3D offset = Point3D.subtract(p, origin);
        int x = (int) (offset.getX() / delta);
        int y = (int) (offset.getY() / delta);
        int z = (int) (offset.getZ() / delta);

        Point3D index = new Point3D(origin.getX() + x * delta, origin.getY() + y * delta, origin.getZ() + z * delta);
        return index;
    }


    /**
     * receive a ray and return the track of it: list of voxels the ray intersect.
     *
     * @param inRay
     * @return
     */
    public Map<Geometry, Point3D> rayTrace(Ray inRay) {

        Map<Geometry, Point3D> intersections = new HashMap<Geometry, Point3D>();
        Voxel current = grid.get(findVoxel(inRay.getPoint()));
        Point3D next = inRay.getPoint();

        // when the ray get out if the grid - the 'current' voxel is null.
        while (!(current == null)) {

            intersections = current.ClosestIntersection(inRay);

            // if we found intersection in one of the voxels, the tracing is over.
            if (!(intersections == null)) {
                return intersections;
            }

            //next = Vector.VectorialAdd(new Vector(next),inRay.getDirection().multiplyByScalar(delta)).getVector();
            //current = grid.get(findVoxel(next));
            next = new Point3D(next.getX() + inRay.getDirection().getVector().getX() * (delta / 10),
                    next.getY() + inRay.getDirection().getVector().getY() * (delta / 10),
                    next.getZ() - inRay.getDirection().getVector().getZ() * (delta / 10));

            current = grid.get(findVoxel(new Point3D(next.getX(), next.getY(), -next.getZ())));
        }

        return null;
        // return backgroundIntersection(inRay)
    }


    /**
     * set background Geometry.
     *
     * @param geos
     */
    public void setBackgroundGeometries(Geometry... geos) {

        for (Geometry g : geos) {
            backgroundGeometries.addGeometry(g);
        }
    }

    /**
     * return background Geometries
     *
     * @return
     */
    public Geometries getBackgroundGeometries() {
        return backgroundGeometries;
    }
}
