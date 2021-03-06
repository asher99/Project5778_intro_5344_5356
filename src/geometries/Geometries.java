package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class Geometries.
 * this is actually a class that represent a List of Geometry objects.
 */
public class Geometries extends Geometry {

    private List<Geometry> geometries = new ArrayList<Geometry>();

    /**
     * find the intersections of the ray with all Geometries.
     *
     * @param ray
     * @return Map of all intersection points by the Geometries.
     */
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> geometriesListMap = new HashMap<>();

        for (Geometry g : geometries) {

            Map<Geometry, List<Point3D>> temp = g.findIntersections(ray);
            if (temp != null) {
                geometriesListMap.putAll(temp);
            }
        }
        return geometriesListMap;
    }


    /**
     * add geometry 'g' to the list.
     * @param g
     */
    public void addGeometry(Geometry g) {
        geometries.add(g);
    }

    /**
     * getter
     * @return
     */
    public List<Geometry> getGeometries() {
        return geometries;
    }

    /**
     * To String
     * @return
     */
    @Override
    public String toString() {
        String string = new String();
        for (Geometry geo:getGeometries()) {
            string += (geo.toString() + '\n');
        }

        return string;
    }
}
