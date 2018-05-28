package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import scene.Scene;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RenderTest {

    @Test
    public void calcColor() {
        Triangle first = new Triangle(
                new Point3D(-130, 130, -40),//bottom left
                new Point3D(130, 130, -40),//bottom right
                new Point3D(130, -130, -50),//top right
                new Color(40, 40, 40), new Material(0.15, 0.018, 1));
        Triangle second = new Triangle(
                new Point3D(130, -130, -50),//top right
                new Point3D(-130, -130, -50),//top left
                new Point3D(-130, 130, -40),//bottom left
                new Color(40, 40, 40), new Material(0.15, 0.018, 1));
        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("triangles in the spot light");
        myScene.setCameraScreenDistance(170);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(0, 0, -1),
                1, 0.0225, 0.0275,
                new Color(201, 225, 255), new Vector(0, 1, -26));
        myScene.addLightSource(mySpotLight);


        ImageWriter sceneWriter = new ImageWriter("neglect me", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        // myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();

        int i = 299;
        int j = 501;

        checkPoint(i, j, myScene, sceneWriter, myRender);

        i = 369;
        j = 501;

        checkPoint(i, j, myScene, sceneWriter, myRender);

        i = 401;
        j = 501;

        checkPoint(i, j, myScene, sceneWriter, myRender);

        i = 450;
        j = 501;

        checkPoint(i, j, myScene, sceneWriter, myRender);

        i = 501;
        j = 501;

        checkPoint(i, j, myScene, sceneWriter, myRender);


    }


    void checkPoint(int i, int j, Scene myScene, ImageWriter sceneWriter, Render myRender) {
        Ray ray = myScene.getSceneCamera().ConstractRaythroughPixel(sceneWriter.getNx(), sceneWriter.getNy(),
                i, j, myScene.getCameraScreenDistance(), sceneWriter.getWidth(), sceneWriter.getHeight());
        //find the intersections of the ray with the scene geometries.
        Map<Geometry, List<Point3D>> intersectionPoints = myScene.getShapesInScene().findIntersections(ray);

        // write to that pixel the right color.
        if (intersectionPoints.isEmpty())
            sceneWriter.writePixel(i, j, myScene.getSceneBackgroundColor());
        else {
            Map<Geometry, Point3D> closestPoint = myRender.getClosestPoint(intersectionPoints);
            Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
            sceneWriter.writePixel(i, j, myRender.calcColor(entry.getKey(), entry.getValue()).getColor());
        }
    }

    @Test
    public void occluded() {

        Triangle first = new Triangle(
                new Point3D(-130, 130, -40),//bottom left
                new Point3D(130, 130, -40),//bottom right
                new Point3D(130, -130, -50),//top right
                new Color(40, 40, 40), new Material(1, 1, 20));

        Triangle second = new Triangle(
                new Point3D(130, -130, -50),//top right
                new Point3D(-130, -130, -50),//top left
                new Point3D(-130, 130, -40),//bottom left
                new Color(40, 40, 40), new Material(1, 1, 20));

        Sphere middle = new Sphere(new Point3D(0, 0, -40),
                30, new Color(0, 20, 100), new Material(1, 2, 20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("shadows test");
        myScene.setCameraScreenDistance(70);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second, middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(0, 0, -1),
                1, 0.0125, 0.0025,
                new Color(255, 125, 55), new Vector(-5, 5, -13));
        myScene.addLightSource(mySpotLight);


        ImageWriter sceneWriter = new ImageWriter("shadows test", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        //myRender.renderImage();
        renderPixel(myRender,490,505);
        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();


    }

    public void renderPixel(Render mr,int i, int j) {
        Ray ray = mr.getScene().getSceneCamera().ConstractRaythroughPixel(mr.getImageWriter().getNx(), mr.getImageWriter().getNy(),
                i, j, mr.getScene().getCameraScreenDistance(), mr.getImageWriter().getWidth(), mr.getImageWriter().getHeight());

        //find the intersections of the ray with the scene geometries.
        Map<Geometry, List<Point3D>> intersectionPoints = mr.getScene().getShapesInScene().findIntersections(ray);

        // write to that pixel the right color.
        if (intersectionPoints.isEmpty())
            mr.getImageWriter().writePixel(i, j, mr.getScene().getSceneBackgroundColor());
        else {
            Map<Geometry, Point3D> closestPoint = mr.getClosestPoint(intersectionPoints);
            Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
            mr.getImageWriter().writePixel(i, j, mr.calcColor(entry.getKey(), entry.getValue()).getColor());
        }
    }

    @Test
    public void calcDiffusive() {
    }

    @Test
    public void calcSpecular() {
    }
}