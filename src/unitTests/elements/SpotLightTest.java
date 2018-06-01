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
                49,new Color(0,20,100),new Material(1.7, 1,20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere in the spot light");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(20, 20, 20), 0.1));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(-2,1,5),
                1, 0.01, 0.025, // 1, 0.01, 0.1,
                new Color (255,255,255),
                new Vector(2, -1, 55));
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
                new Color(40, 40, 40), new Material(1, 1, 20));
        Triangle second = new Triangle(
                new Point3D(130, -130, -50),//top right
                new Point3D(-130, -130, -50),//top left
                new Point3D(-130, 130, -40),//bottom left
                new Color(40, 40, 40), new Material(1, 1, 20));
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
                1, 0.125, 0.0025,
                new Color(255, 125, 55), new Vector(0, 1, -26));
        myScene.addLightSource(mySpotLight);


        ImageWriter sceneWriter = new ImageWriter("triangles in the spot light", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

    @Test
    public void cylinder() {

        Cylinder c = new Cylinder(2, new Ray(new Point3D(0, 0, -100), new Vector(0, 1, 0)),
                new Color(100, 20, 20), new Material(1, 1, 20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("cylinder");
        myScene.setCameraScreenDistance(170);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(c);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(0, 0, -1),
                1, 0.125, 0.0025,
                new Color(255, 125, 55), new Vector(0, 1, -26));
        myScene.addLightSource(mySpotLight);


        ImageWriter sceneWriter = new ImageWriter("cylinder", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

}