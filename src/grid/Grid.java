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

    // represent the camera position.
    Point3D viewPoint;

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
        backgroundGeometries = new Geometries();
    }

    // ***************** Getters/Setters ********************** //

    public HashMap<Point3D, Voxel> getGrid() {
        return grid;
    }

    /**
     * get origin of the grid.
     * @return
     */
    public Point3D getOrigin() {
        return origin;
    }

    // ***************** Operations ******************** //

    /**
     * initialize the grid hash-map.
     * @return
     */
    public HashMap<Point3D, Voxel> voxelisation() {

        HashMap<Point3D,Voxel> voxelHash = new HashMap<>(voxelsOnAxis*voxelsOnAxis*voxelsOnAxis);

        for (int z = 0; z <voxelsOnAxis; z++){
            for (int y = 0; y <voxelsOnAxis; y++){
                for (int x = 0; x <voxelsOnAxis; x++){
                    Point3D index = new Point3D(x*delta, y*delta, z*delta);
                    Point3D key = Point3D.add(origin,index);
                    Voxel value = new Voxel(key,delta,new Geometries());
                    voxelHash.put(key, value);
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

        Point3D index = new Point3D(origin.getX() + x*delta, origin.getY() + y*delta, origin.getZ() + z*delta);
        return  index;
    }

    /**
     * receive a ray and return the track of it: list of voxels the ray intersect.
     * @param inRay
     * @return
     */
    public Map<Geometry,Point3D> rayTrace(Ray inRay){

        Map<Geometry,Point3D> intersections = new HashMap<Geometry,Point3D>();
        //Map<Geometry,Point3D> intersectionsUp = new HashMap<Geometry,Point3D>();
       // Map<Geometry,Point3D> intersectionsDown = new HashMap<Geometry,Point3D>();
        Voxel current = grid.get(findVoxel(inRay.getPoint()));
        Point3D next = inRay.getPoint();

        // when the ray get out if the grid - the 'current' voxel is null.
        while (!(current == null)){

            intersections = current.ClosestIntersection(inRay);

            // if we found intersection in one of the voxels, the tracing is over.
            if(!(intersections == null)){
                    return intersections;
            }

            next = new Point3D(next.getX()-inRay.getDirection().getVector().getX()*(delta/2),
                    next.getY() - inRay.getDirection().getVector().getY()*(delta/2),
                    next.getZ() - inRay.getDirection().getVector().getZ()*(delta/2));

            current = grid.get(findVoxel(new Point3D(next.getX(),next.getY(),next.getZ())));
        }

        return null;
    }


    /**
     * set background Geometry.
     * @param geos
     */
    public void setBackgroundGeometries(Geometry... geos){

        for ( Geometry g :geos) {
            backgroundGeometries.addGeometry(g);
        }
    }

    /**
     * return background Geometries
     * @return
     */
    public Geometries getBackgroundGeometries() {
        return backgroundGeometries;
    }


    /**
     * return the voxel above voxel.
     * @param v
     * @return
     */
    public Voxel up(Voxel v){
        return grid.get(Point3D.add(v.voxelOrigin, new Point3D(0,delta,0)));
    }

    /**
     * return the voxel under voxel.
     * @param v
     * @return
     */
    public Voxel down(Voxel v){
        return grid.get(Point3D.add(v.voxelOrigin, new Point3D(0,-delta,0)));
    }

    /**
     * return the closest intersection
     * @param maps
     * @return
     */
    public Map<Geometry,Point3D> closest(Map<Geometry,Point3D>... maps){

        Map<Geometry,Point3D> closest = null;
        Map.Entry<Geometry,Point3D> tempPair = null;
        Point3D tempPoint;
        double tempDist = Double.MAX_VALUE;
        for (Map<Geometry,Point3D> m: maps){
            if (m != null) {
                tempPair = m.entrySet().iterator().next();
                if (Point3D.distance(tempPair.getValue(), viewPoint) < tempDist) {
                    tempDist = Point3D.distance(tempPair.getValue(), viewPoint);
                    closest = m;
                }
            }
        }
        return closest;
    }

    /**
     * setting the point of view.
     * @param viewPoint
     */
    public void setViewPoint(Point3D viewPoint) {
        this.viewPoint = viewPoint;
    }
}
