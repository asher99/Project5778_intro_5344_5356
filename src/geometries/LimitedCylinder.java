package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class Limited Cylinder for a cylinder with an end in space.
 * a cylinder have direction and points => Ray orientation.
 * and a top point.
 */
public class LimitedCylinder extends Cylinder {

    private Point3D top;

    // ***************** Constructors ********************** //

    public LimitedCylinder(double myRadius, Point3D tp, Ray myRay, Color e, Material m) {
        super(myRadius, myRay, e, m);
        top = tp;

    }

    // ***************** Getters/Setters ********************** //

    /**
     * getter
     *
     * @return
     */
    @Override
    public double get_radius() {
        return super.get_radius();
    }

    /**
     * getter
     *
     * @return
     */
    @Override
    public Ray getOrientaion() {
        return super.getOrientaion();
    }

    /**
     * getter
     *
     * @return
     */
    public Point3D getTop() {
        return top;
    }

    /**
     * getter
     *
     * @return
     */
    @Override
    public Material getMaterial() {
        return super.getMaterial();
    }

    /**
     * setter
     */
    public void setTop(Point3D top) {
        this.top = top;
    }

    /**
     * setter
     */
    @Override
    public void setMaterial(Material material) {
        super.setMaterial(material);
    }


    // ***************** Operations ******************** //

    /**
     * a function that checks if a given point is on the cylinder
     *
     * @param point
     * @return
     */
    private boolean isOnCylinder(Point3D point) {
        // the vector from bottom to the point
        Vector vec1 = new Vector(Point3D.subtract(orientation.getPoint(), point));

        // the vector from the top to point
        Vector vec2 = new Vector(Point3D.subtract(top, point));

        // the vectors to the edges of cylinder
        Vector vec3 = new Vector(Point3D.subtract(orientation.getPoint(), point));

        Vector vec4 = new Vector(Point3D.subtract(top, point));

        double Vec3dot = Vector.dotProduct(vec3, orientation.getDirection());

        double Vec4dot = Vector.dotProduct(vec4, orientation.getDirection());
        if (Vec3dot == 0)
            if (_radius - vec3.sizeOfVector() >= 0)
                return true;
        if (Vec4dot == 0)
            if (_radius - vec4.sizeOfVector() >= 0)
                return true;

        if (Vector.dotProduct(vec1, orientation.getDirection()) > 0
                && Vector.dotProduct(vec2, orientation.getDirection()) < 0)
            return true;
        return false;
    }

    /**
     * getter
     *
     * @return
     */
    @Override
    public Vector getNormal(Point3D somePoint) {
        if (!isOnCylinder(somePoint))
            return null;
        return super.getNormal(somePoint);
    }

    /**
     * return map of the Ray intersection points with the Cylinder.
     *
     * @param myRay
     * @return
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray myRay) {
        //Cylinder endlessC = new Cylinder(_radius, orientation, emission, material);
        Map<Geometry, List<Point3D>> geometryListMap = new HashMap<Geometry, List<Point3D>>();
        List<Point3D> listOfIntersections = new ArrayList<Point3D>();

        Plane plane1 = new Plane(orientation.getPoint(), orientation.getDirection());

        Plane plane2 = new Plane(top, orientation.getDirection());

        Map<Geometry, List<Point3D>> temp1 = plane1.findIntersections(myRay);

        Map<Geometry, List<Point3D>> temp2 = plane2.findIntersections(myRay);

        if(temp1 != null)
        for (HashMap.Entry<Geometry, List<Point3D>> pair : temp1.entrySet()) {
            for (Point3D p1 : pair.getValue()) {
                if (new Vector(Point3D.subtract(orientation.getPoint(), p1)).sizeOfVector() <= _radius && p1!=null)
                    listOfIntersections.add(p1);
            }
        }

        if(temp2 != null)
        for (HashMap.Entry<Geometry, List<Point3D>> pair : temp2.entrySet()) {
            for (Point3D p2 : pair.getValue()) {
                if (new Vector(Point3D.subtract(orientation.getPoint(), p2)).sizeOfVector() <= _radius && p2!=null)
                    listOfIntersections.add(p2);
            }
        }

        geometryListMap = super.findIntersections(myRay);

        for (HashMap.Entry<Geometry, List<Point3D>> pair : geometryListMap.entrySet()) {
            for (Point3D p : pair.getValue()) {
                if (isOnCylinder(p))
                    listOfIntersections.add(p);
            }
        }
        if(listOfIntersections.isEmpty())
            return null;
        geometryListMap.put(this, listOfIntersections);
        return geometryListMap;
    }
}

