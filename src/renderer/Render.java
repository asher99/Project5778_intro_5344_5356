package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
               ArrayList<Point3D> intersectionPoints = scene.getShapesInScene().findIntersections(ray);

               // write to that pixel the right color.
               if (intersectionPoints.isEmpty())
                   imageWriter.writePixel(i,j,scene.getSceneBackgroundColor());
               else {
                   Point3D closestPoint = getClosestPoint(intersectionPoints);
                   imageWriter.writePixel(i,j,calcColor(closestPoint).getColor());
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
     * for now, just return the only "ambient light" in the scene.
     * @param p
     * @return
     */
    public Color calcColor(Point3D p){
        return scene.getSceneAmbientLight().getIntensity();

       /* Random rn = new Random();
        int red = rn.nextInt();
        int green = rn.nextInt();
        int blue = rn.nextInt();

        Color output = new Color(red,green,blue);
        return output; */
    }

    /**
     * @param list
     * @return
     */
    public Point3D getClosestPoint(ArrayList<Point3D> list){

        Point3D closestPoint = list.get(0);

        for (Point3D p: list) {
            if (Point3D.distance(scene.getSceneCamera().getP0(),closestPoint) > Point3D.distance(scene.getSceneCamera().getP0(),p))
                closestPoint = p;
        }

        return closestPoint;
    }

    public List<Point3D> getSceneRayIntersections(Ray ray){
        return null;
    }


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
