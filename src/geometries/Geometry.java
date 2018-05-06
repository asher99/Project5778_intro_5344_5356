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
    protected Color emission;

    /**
     * the Geometry material.
     */
    protected Material material;

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
    public Geometry(Color e, Material m) {
        emission = new Color(e);
        material = m;
    }

    /**
     * copy constructor
     *
     * @param g
     */
    public Geometry(final Geometry g) {
        emission = new Color(g.getEmission());
        material = g.getMaterial();
    }

    // ***************** Operations ******************** //

    /**
     * getter
     * @return
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * getter
     * @return
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter
     *
     * @param emission
     */
    public void setEmission(Color emission) {
        this.emission = emission;
    }

    /**
     * setter
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
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


}
