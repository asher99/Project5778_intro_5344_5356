package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Geometries extends Geometry {

    private List<Geometry> geometries = new ArrayList<Geometry>();

    public List<Point3D> findIntersections(Ray ray){
        List<Point3D> listOfIntersections = new ArrayList<Point3D>();

        for (Geometry g :geometries ) {
            for (Point3D point:g.findIntersections(ray)) {
                listOfIntersections.add(point);
            }
        }
        return listOfIntersections;
    }

    public void addGeometry(Geometry g){
        geometries.add(g);
    }

}
