package renderer;

import elements.LightSource;
import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.*;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class Render. implementation of the graphic renderer.
 */
public class Render {

    //fields
    private Scene scene;
    private ImageWriter imageWriter;

/**************** operations *******************/

    /**
     * the main method of the project.
     * rendering the scene with its background, lights and geometries to image.
     */
    public void renderImage() {

        // foreach pixel i,j:
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = imageWriter.getNy() - 1; j > 0; j--) {

                // construct ray through that pixel
                Ray ray = scene.getSceneCamera().ConstractRaythroughPixel(imageWriter.getNx(), imageWriter.getNy(),
                        i, j, scene.getCameraScreenDistance(), imageWriter.getWidth(), imageWriter.getHeight());

                //find the intersections of the ray with the scene geometries.
                Map<Geometry, List<Point3D>> intersectionPoints = scene.getShapesInScene().findIntersections(ray);

                // write to that pixel the right color.
                if (intersectionPoints.isEmpty())
                    imageWriter.writePixel(i, j, scene.getSceneBackgroundColor());
                else {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
                    imageWriter.writePixel(i, j, calcColor(entry.getKey(), entry.getValue()).getColor());
                }
            }
        }
    }

    /**
     * print grid on the image.
     *
     * @param interval - interval between grid lines.
     */
    public void printGrid(double interval) {
        for (int i = 0; i <= imageWriter.getNx()-1; i++) {
            for (int j = imageWriter.getNy() - 1; j > 0; j--) {
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, java.awt.Color.WHITE);
            }
        }
    }

    /**
     * calculate color in a point.
     * using Phong Reflectance model:
     * ambient light.
     * emission light
     * diffusion light
     * specular light
     *
     * @param p
     * @return
     */
    public Color calcColor(Geometry geo, Point3D p) {

        //ambient light
        Color color = scene.getSceneAmbientLight().getIntensity();

        //emission light
        color.add(geo.getEmission());

        //prepare for iterating over all light sources.
        //identify the material, get the normal vector in the checked point.
        Vector n = geo.getNormal(p);
        int nShininess = geo.getMaterial().getnShininess();
        double kd = geo.getMaterial().getKd();
        double ks = geo.getMaterial().getKs();

        // foreach light source in the scene: add the diffusion and specular lights.
        for (LightSource lightSource : scene.getSceneLightSources()) {
            Vector l = lightSource.getL(p);
            //Vector v = new Vector(Point3D.subtract(scene.getSceneCamera().getP0(), p));
            Vector v = new Vector(scene.getSceneCamera().getP0(), p);

            // check if the Diffusion and Specular components are in the
            // same side of the tangent surface as the light source.
            // if true - return the scaled color.
            // if false - return just a (0,0,0) color that can't change the result in the rendering procedure.
            if ((Vector.dotProduct(l, n) > 0 && Vector.dotProduct(v, n) > 0) || (Vector.dotProduct(l, n) < 0 && Vector.dotProduct(v, n) < 0)) {
                  if (!occluded(l, p, geo)) {
                    Color lightIntensity = lightSource.getIntensity(p);
                    color.add(calcDiffusive(kd, l, n, v, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * @param intersectionPoints
     * @return
     */
    public Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Point3D p = getScene().getSceneCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        for (HashMap.Entry<Geometry, List<Point3D>> pair : intersectionPoints.entrySet()) {
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

    /**
     * calculate the Diffusive effect in Phong's model.
     *
     * @param kd             - the material diffusive factor.
     * @param l              - vector from the light source to the object.
     * @param n              - the normal vector to the geometry in the intersection point.
     * @param v              - the Vector from the Camera to the intersection point.
     * @param lightIntensity - the light source intensity.
     * @return scale lightIntensity by: Kd * dotProduct(l,n)
     */
    public Color calcDiffusive(double kd, Vector l, Vector n, Vector v, Color lightIntensity) {
        Color result = new Color(lightIntensity);
        double scalingFactor = kd * Vector.dotProduct(l.normal(), n.normal());

        if (scalingFactor < 0)
            scalingFactor = scalingFactor * -1;


        result.scale(scalingFactor);
        return result;
    }


    /**
     * calculate the Specular effect in Phong's model.
     *
     * @param ks             - the material specular factor.
     * @param l              - vector from the light source to the object
     * @param n              - the normal vector to the geometry in the intersection point.
     * @param v              - the Vector from the Camera to the intersection point.
     * @param nShininess     - the material shininess.
     * @param lightIntensity - the light source intensity.
     * @return scale lightIntensity by: Ks* dotProduct(-v,r)^nShininess.
     * where r is: l − 2* dotProduct(l⋅n)n
     */
    public Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        Color result = new Color(lightIntensity);

        // calculating "Ks* dotProduct(-v,r)^nShininess" and 'r' itself.
        double temp = -2 * Vector.dotProduct(l.normal(), n.normal());
        Vector nComponent = n.multiplyByScalar(temp);
        Vector r = new Vector(l.getVector(), nComponent.getVector());
        double scalingFactor = ks * Math.pow(Vector.dotProduct(v.multiplyByScalar(-1).normal(), r.normal()), nShininess);


        result.scale(scalingFactor);
        return result;
    }

    /**
     *
     * @param l     - vector from the light source to the object
     * @param p     - intersection point between the ray and the Geometry.
     * @param geo   - Geometry.
     * @return
     */
    private boolean occluded(Vector l, Point3D p, Geometry geo) {
        Vector lightDirection = l.normal().multiplyByScalar(-1); // from point to light source

        /*Vector normal = geo.getNormal(p);
        Vector epsVector = normal.multiplyByScalar(Vector.dotProduct(normal, lightDirection) > 0 ? 2 : -2);
        Point3D geometryPoint = Point3D.add(p, epsVector.getVector());*/

        Ray lightRay = new Ray(/*geometryPoint*/p, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = scene.getShapesInScene().findIntersections(lightRay);
        if(intersectionPoints.isEmpty())
            return false;
        /*else if(intersectionPoints.containsKey(geo)) {
            intersectionPoints.remove(geo);
            return !intersectionPoints.isEmpty();
       }
        else return !intersectionPoints.isEmpty();*/
        else {

            if(intersectionPoints.containsKey(geo)) {
                intersectionPoints.remove(geo);
                if (intersectionPoints.isEmpty())
                    return false;
            }

            // check if someone block the way:
            // if the vector between the point on the Geometry and the intersection point
            // equals to the ray direction vector.
            boolean isSomeoneBlockMyWay = false;
            for (HashMap.Entry<Geometry, List<Point3D>> pair : intersectionPoints.entrySet()) {
                for (Point3D point : pair.getValue()) {
                    Vector offset = new Vector(lightRay.getPoint(),point);
                    if(lightRay.getDirection().equals(offset.normal())) {
                        isSomeoneBlockMyWay = true;
                        break;
                    }
                }
            }
            return isSomeoneBlockMyWay;
        }
    }

    /**
     * builds a map of intersection points by shapes in Scene
     * @param ray - the Ray we build the map for.
     * @return
     */
    /*public Map<Geometry,List<Point3D>> getSceneRayIntersections(Ray ray){

        Map<Geometry,List<Point3D>> intersectionPoint = new HashMap<Geometry, List<Point3D>>();
        for (Geometry geo: scene.getShapesInScene().getGeometries()) {
            List<Point3D> geometryIntersectionPoints = geo.findIntersections(ray);
            intersectionPoint.put(geo,geometryIntersectionPoints);
        }
        return intersectionPoint;
    }*/


    /**
     * render a specific pixel.
     * this method is used for debugging, enabling check what exactly get wrong in a certain pixel
     * @param i
     * @param j
     */
    public void renderPixel(int i, int j) {
        Ray ray = this.scene.getSceneCamera().ConstractRaythroughPixel(this.imageWriter.getNx(), this.imageWriter.getNy(),
                i, j, this.scene.getCameraScreenDistance(), this.imageWriter.getWidth(), this.imageWriter.getHeight());

        //find the intersections of the ray with the scene geometries.
        Map<Geometry, List<Point3D>> intersectionPoints = this.scene.getShapesInScene().findIntersections(ray);

        // write to that pixel the right color.
        if (intersectionPoints.isEmpty())
            this.imageWriter.writePixel(i, j, this.scene.getSceneBackgroundColor());
        else {
            Map<Geometry, Point3D> closestPoint = this.getClosestPoint(intersectionPoints);
            Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
            this.imageWriter.writePixel(i, j, this.calcColor(entry.getKey(), entry.getValue()).getColor());
        }
    }


    /****************setters/getters********************/

    /**
     * setter
     *
     * @param imageWriter
     */
    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }

    /**
     * setter
     *
     * @param scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * getter
     *
     * @return
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * getter
     *
     * @return
     */
    public ImageWriter getImageWriter() {
        return imageWriter;
    }
}
