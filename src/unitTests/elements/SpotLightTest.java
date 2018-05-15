package elements;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import primitives.Color;

import java.awt.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SpotLightTest {

    @Test
    public void spotLight() {
        Sphere middle = new Sphere(new Point3D(0, 0, -50),
                49, new Color(0, 0, 60), new Material(0.275, 0.0175, 1));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere in the spot light");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(120, 120, 120), 0.1));

       /*   SpotLight mySpotLight = new SpotLight(
                new Point3D(100,-100,-20),
                5,5,10,
                new Color (230,0,230),
                new Vector(-100,100,30));*/
        SpotLight mySpotLight = new SpotLight(
                new Point3D(-1, 1, -3),
                1, 2, 5.995,
                new Color(255, 255, 255),
                new Vector(2, -2, 30));
        myScene.addLightSource(mySpotLight);

        ImageWriter sceneWriter = new ImageWriter("sphere in the spot light", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

    @Test
    public void spotLight1() {

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
                1, 0.0125, 0.025,
                new Color(201, 225, 255), new Vector(0, 1, -26));
        myScene.addLightSource(mySpotLight);


        ImageWriter sceneWriter = new ImageWriter("triangles in the spot light", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }
}