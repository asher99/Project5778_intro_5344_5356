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
                49,new Color(0,0,20),new Material());

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere in the spot light");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

     /*       SpotLight mySpotLight = new SpotLight(
                new Point3D(100,-100,-20),
                5,5,10,
                new Color (230,0,230),
                new Vector(-100,100,30));*/
    SpotLight mySpotLight = new SpotLight(
                new Point3D(-2,2,-20),
                5,5,10,
                new Color (200,0,200),
                new Vector(2,-2,30));
        myScene.addLightSource(mySpotLight);

        ImageWriter sceneWriter = new ImageWriter("sphere in the spot light",1000,1000,1000,1000);
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
                new Point3D(-100,100,-35),
                new Point3D(100,100,-35),
                new Point3D(-100,-100,-65),
                new Color(20,20,20),new Material());
        Triangle second = new Triangle(
                new Point3D(-100,-100,-65),
                new Point3D(100,-100,-65),
                new Point3D(100,100,-35),
                new Color(20,20,20),new Material());
        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("triangles in the spot light");
        myScene.setCameraScreenDistance(185);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first,second);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0,0,0), 0.1));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(10,10,-10),
                5,10,0,
                new Color (200,0,0), new Vector(0,1,-30));
        myScene.addLightSource(mySpotLight);

        ImageWriter sceneWriter = new ImageWriter("triangles in the spot light",1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }
}