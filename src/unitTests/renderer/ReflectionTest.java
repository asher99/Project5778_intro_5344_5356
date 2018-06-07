package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class ReflectionTest {

    @Test
    public void reflection() {

        Triangle first = new Triangle(
                new Point3D(-130, 130, -25),//bottom left
                new Point3D(130, 130, -25),//bottom right
                new Point3D(130, -130, -35),//top right
                new Color(100, 0, 0), new Material(2, 0.2, 1, 0, 5));

        Triangle second = new Triangle(
                new Point3D(130, -130, -35),//top right
                new Point3D(-130, -130, -35),//top left
                new Point3D(-130, 130, -25),//bottom left
                new Color(0, 100, 0), new Material(2, 0.2, 1, 0, 5));

        Sphere middle = new Sphere(new Point3D(0, 0, -10),
                8, new Color(0, 20, 100), new Material(0.4, 0.1, 0, 0.7, 20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("reflection test");
        myScene.setCameraScreenDistance(70);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second, middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(12, 12, 0),
                1, 0.00075, 0.0008,
                new Color(255, 255, 255), new Vector(-12, -12, -15));

        DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40, 0, 0), new Vector(1.5, -3, -1));
        myScene.addLightSource(mySpotLight);
        //myScene.addLightSource(myDirectionalLight);


        ImageWriter sceneWriter = new ImageWriter("reflection test", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.renderPixel(504, 497);
        myRender.renderPixel(503, 497);
        //myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();


    }

    @Test
    public void refraction() {

        Triangle first = new Triangle(
                new Point3D(-130, 130, -25),//bottom left
                new Point3D(130, 130, -25),//bottom right
                new Point3D(130, -130, -35),//top right
                new Color(100, 0, 0), new Material(2, 0.2, 1, 0, 5));

        Triangle second = new Triangle(
                new Point3D(130, -130, -35),//top right
                new Point3D(-130, -130, -35),//top left
                new Point3D(-130, 130, -25),//bottom left
                new Color(0, 100, 0), new Material(2, 0.2, 1, 0, 5));

        Sphere middle = new Sphere(new Point3D(0, -50, -22),
                10, new Color(0, 20, 100), new Material(0.4, 1, 0, 0, 20));

        Camera camera = new Camera(new Point3D(0, 0, 50),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("refraction test");
        myScene.setCameraScreenDistance(200);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second, middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(12, 12, 0),
                1, 0.00075, 0.0008,
                new Color(255, 255, 255), new Vector(-12, -12, -15));

        DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40, 0, 0), new Vector(1.5, -3, -1));
        myScene.addLightSource(mySpotLight);
        //myScene.addLightSource(myDirectionalLight);


        ImageWriter sceneWriter = new ImageWriter("refraction test", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        //myRender.renderPixel(500, 610);
        //myRender.renderPixel(503, 497);
        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();


    }

    @Test
    public void twoTriangles(){

        double z = -12;
        double z2 = -8;
        double z3 = -4;

        Triangle back = new Triangle(
                new Point3D(-100,-100,z),
                new Point3D(-100,100,z),
                new Point3D(100,0,z),
                new Color(255,0,0),
                new Material(2,0.2,0,1,10));

        Triangle middle = new Triangle(
                new Point3D(-50,-50,z2),
                new Point3D(-50,50,z2),
                new Point3D(50,0,z2),
                new Color(0,0,255),
                new Material(2,0.2,0,1,10));

        Triangle front = new Triangle(
                new Point3D(-10,-10,z3),
                new Point3D(-10,10,z3),
                new Point3D(10,0,z3),
                new Color(0,255,0),
                new Material(2,0.2,0,1,10));

        Camera cm = new Camera(new Point3D(0,0,0), new Vector(1,0,0), new Vector(0,0,-1));

        Scene scene = new Scene("Two Triangles");
        scene.setSceneAmbientLight(new AmbientLight(new Color(0,0,0),1));
        scene.setCameraScreenDistance(50);
        scene.setSceneCamera(cm);
        scene.addGeometries(back,middle,front);
        scene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));

        ImageWriter writer = new ImageWriter("Two Triangles",1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(scene);
        myRender.setImageWriter(writer);


        myRender.renderImage();
        //myRender.printGrid(100);
        myRender.renderPixel(500,500);
        myRender.getImageWriter().writeToimage();


    }
}
