package renderer;

import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.*;

/**
 *  Class Render. implementation of the graphic renderer.
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
    public void renderImage(){

        // foreach pixel i,j:
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = imageWriter.getNy()-1; j > 0; j--) {

                // construct ray through that pixel
               Ray ray = scene.getSceneCamera().ConstractRaythroughPixel(imageWriter.getNx(),imageWriter.getNy(),
                       i,j,scene.getCameraScreenDistance(),imageWriter.getWidth(),imageWriter.getHeight());

               //find the intersections of the ray with the scene geometries.
              Map<Geometry,List<Point3D>> intersectionPoints = scene.getShapesInScene().findIntersections(ray);

               // write to that pixel the right color.
               if (intersectionPoints.isEmpty())
                   imageWriter.writePixel(i,j,scene.getSceneBackgroundColor());
               else {
                   Map<Geometry,Point3D> closestPoint = getClosestPoint(intersectionPoints);
                   Map.Entry<Geometry,Point3D> entry = closestPoint.entrySet().iterator().next();
                   imageWriter.writePixel(i,j,calcColor(entry.getKey(),entry.getValue()).getColor());
               }
            }
        }
    }

    /**
     * print grid on the image.
     * @param interval - interval between grid lines.
     */
    public void printGrid(double interval){
        for (int i = 0; i <= 499; i++){
            for (int j = 499; j > 0; j--){
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i,j, java.awt.Color.WHITE);
            }
        }
    }

    /**
     * for now, just return the only "ambient light" in the scene and color of the geometry.
     * @param p
     * @return
     */
    public Color calcColor(Geometry geo, Point3D p) {
        Color color = scene.getSceneAmbientLight().getIntensity();
        color.add(geo.getEmission());
        return color;
    }

    /**
     * @param intersectionPoints
     * @return
     */
    public Map<Geometry,Point3D> getClosestPoint(Map<Geometry,List<Point3D>> intersectionPoints){

        double distance = Double.MAX_VALUE;
        Point3D p0 = scene.getSceneCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        for (HashMap.Entry<Geometry,List<Point3D>> pair: intersectionPoints.entrySet()) {
            for (Point3D point : pair.getValue()) {
                if (p0.distance(p0,point) < distance) {
                    minDistancePoint.clear(); // make it empty
                    minDistancePoint.put(pair.getKey(), new Point3D(point));
                    distance = p0.distance(p0,point);
                }
            }
        }
        return minDistancePoint;
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


    /****************setters/getters********************/
    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }
}
