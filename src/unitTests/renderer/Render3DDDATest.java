package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.LimitedCylinder;
import geometries.Sphere;
import geometries.Triangle;
import grid.Grid;
import org.junit.Test;
import primitives.*;
import scene.Scene;

import static org.junit.Assert.*;

public class Render3DDDATest {
    @Test
    public void gridedProject() {



        Color ballColor = new Color(128, 128, 128);
        Material ballMaterial = new Material(1, 1, 0.65, 0, 20);

        Sphere ball1 = new Sphere(new Point3D(-40, -20, -30), 10, ballColor, ballMaterial);

        Sphere ball2 = new Sphere(new Point3D(-30, -70, -30), 10, ballColor, ballMaterial);

        Sphere ball3 = new Sphere(new Point3D(40, -20, -30), 10, ballColor, ballMaterial);

        Sphere ball4 = new Sphere(new Point3D(40, -70, -30), 10, ballColor, ballMaterial);

        Sphere ball5 = new Sphere(new Point3D(40, -70, -50), 10, ballColor, ballMaterial);

        Camera camera = new Camera(
                new Point3D(0, -170, -10),
                //new Vector(0, -1, 0),
               // new Vector(0, 0, -1));
                new Vector(0, 0, -1),
                new Vector(0, 1, 0));


        Geometries geos = new Geometries();
        Grid myGrid = new Grid(geos, new Point3D(115, 0, 81), -50, 5);


        myGrid.getGrid().get(new Point3D(-35, 0, -19)).addGeometries(ball1);
        myGrid.getGrid().get(new Point3D(-35, -50, -19)).addGeometries(ball2);

        myGrid.getGrid().get(new Point3D(65, 0, -19)).addGeometries(ball3);
        myGrid.getGrid().get(new Point3D(65, -50, -19)).addGeometries(ball4,ball5);

        Scene myScene = new Scene("rendere 3DDDA 1");
        myScene.setCameraScreenDistance(420);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries();

        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));



        PointLight myPointLight = new PointLight(
                new Point3D(0, -100, -50),
                1, 0.0076, 0.0001, new Color(201, 226, 255));


        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("rendere 3DDDA 1", 1000, 1000, 1000, 1000);
        Render3DDDA myRender = new Render3DDDA();
        myRender.setScene(myScene);
        myRender.getScene().setGrid(myGrid);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage3DDDA();
        //myRender.renderImage();
        myRender.getImageWriter().writeToimage();

    }
}