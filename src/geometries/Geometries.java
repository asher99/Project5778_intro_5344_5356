package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * List of Geometry objects.
 */
public class Geometries extends Geometry {

    private List<Geometry> geometries = new ArrayList<Geometry>();

    /**
     * find the intersections of the ray with all Geometries.
     * @param ray
     * @return
     */
    public List<Point3D> findIntersections(Ray ray){
        List<Point3D> listOfIntersections = new ArrayList<Point3D>();

        for (Geometry g :geometries ) {

            List<Point3D> temp = g.findIntersections(ray);
            if(temp != null) {
                for (Point3D point :temp) {
                    listOfIntersections.add(point);
                }
            }
        }
        return listOfIntersections;
    }

    /**
     * add geometry 'g' to the list.
     * @param g
     */
    public void addGeometry(Geometry g){
        geometries.add(g);
    }

    public List<Geometry> getGeometries() {
        return geometries;
    }
}
