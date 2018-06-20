package grid;

import geometries.*;
import primitives.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class Voxel for a cell in the 3D grid of the scene.
 * The voxel have an 3D index and a list of geometries in the voxel.
 * Also, it have the ability to iterate over those geometries, and find their
 * intersections point, normal in a specific point etc.
 * <p>
 * indexing the Voexls:
 * we will use point3D - the origin of that voxel;
 * with the grid 'delta' - we can identify our voxel with start and finish points.
 */
public class Voxel {

    // fields:
    Geometries voxelGeometries;
    Point3D voxelOrigin;
    int delta;
    Point3D voxelFinish;

    // ***************** Constructors ********************** //
    public Voxel(Point3D origin, int del, Geometries geos) {

        voxelGeometries = geos;
        voxelOrigin = origin;
        delta = del;

        voxelFinish = new Point3D(voxelOrigin.getX() + delta, voxelOrigin.getY() + delta, voxelOrigin.getZ() + delta);
    }

    // ***************** Getters/Setters ********************** //


    // ***************** Operations ******************** //

    /**
     * add multiple Geometries to the Voxel Geometries.
     *
     * @param geometries
     */
    public void addGeometries(Geometry... geometries) {

        for (Geometry geo : geometries) {
            voxelGeometries.addGeometry(geo);
        }
    }


    /**
     * return if a voxel is empty from Geometries.
     *
     * @return
     */
    public boolean isEmpty() {
        return (voxelGeometries == null);
    }

    /**
     * receive a point in space and check if it's inside the voxel.
     *
     * @param p
     * @return
     */
    public boolean inVoxel(Point3D p) {
        return (voxelOrigin.getX() <= p.getX() && p.getX() <= voxelFinish.getX()
                && voxelOrigin.getY() <= p.getY() && p.getY() <= voxelFinish.getY()
                && voxelOrigin.getZ() <= p.getZ() && p.getZ() <= voxelFinish.getZ());
    }

    /**
     * return intersections point within Voxel.
     *
     * @param inRay - point value of 'inRay' is the intersection point of the ray and the voxel.
     * @return
     */
    public Map<Geometry, Point3D> ClosestIntersection(Ray inRay) {
        Map<Geometry, List<Point3D>> intersections = voxelGeometries.findIntersections(inRay);
        if (intersections.isEmpty())
            return null;
        else {
            return getClosestPoint(inRay.getPoint(), intersections);
        }
    }

    /**
     * return the closest point to some point in a list.
     *
     * @param p
     * @param intersections
     * @return
     */
    private Map<Geometry, Point3D> getClosestPoint(Point3D p, Map<Geometry, List<Point3D>> intersections) {

        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        for (HashMap.Entry<Geometry, List<Point3D>> pair : intersections.entrySet()) {
            for (Point3D point : pair.getValue()) {
                if (p.distance(p, point) < distance) {
                    minDistancePoint.clear(); // make it empty
                    minDistancePoint.put(pair.getKey(), new Point3D(point));
                    distance = p.distance(p, point);
                }
            }
        }
        return minDistancePoint;
    }


    public Point3D nextVoxelOrigin(Ray inRay) {
        /*Point3D offset = new Point3D(inRay.getPoint().getX() + delta,inRay.getPoint().getY() + delta,inRay.getPoint().getZ() + delta);
         */
        return null;
    }

    @Override
    public String toString() {
        return "Voxel origin: " + voxelOrigin.toString() +
                "\nVoxel Geometries: " + voxelGeometries.toString() +
                "\nVoxel end: " + voxelFinish.toString();
    }
}
