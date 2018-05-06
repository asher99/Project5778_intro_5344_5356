package geometries;

import primitives.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class Geometry for geometrical shapes.
 * this is an abstract class include features shared by all kinds of shapes in space.
 */
public abstract class Geometry {


    /**
     * represent Geometry color
     */
    public Color emission;

    // ***************** Constructors ********************** //

    /**
     * default constructor
     */
    public Geometry() {
        emission = new Color();
    }

    /**
     * constructor
     *
     * @param e
     */
    public Geometry(Color e) {
        emission = new Color(e);
    }

    /**
     * default constructor
     *
     * @param g
     */
    public Geometry(final Geometry g) {
        emission = new Color(g.emission);
    }

    // ***************** Operations ******************** //

    /**
     * get
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * return the normal to a shape
     */
    protected Vector getNormal(Point3D somePoint) {
        return null;
    }

    /**
     * receive a Ray and return all the points that Ray intersevt with the Geometry.
     */
    public abstract Map<Geometry, List<Point3D>> findIntersections(Ray myRay);

    /**
     * setter
     *
     * @param emission
     */
    public void setEmission(Color emission) {
        this.emission = emission;
    }
}
