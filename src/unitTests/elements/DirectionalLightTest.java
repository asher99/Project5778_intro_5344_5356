package elements;

import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.*;
import scene.*;

import static org.junit.Assert.*;

public class DirectionalLightTest {

    @Test
    public void directionalight() {


        Triangle first = new Triangle(
                new Point3D(-130, 130, -40),//bottom left
                new Point3D(130, 130, -40),//bottom right
                new Point3D(130, -130, -50),//top right
                new Color(20, 20, 20), new Material(0.015, 0.005, 0));
        Triangle second = new Triangle(
                new Point3D(130, -130, -50),//top right
                new Point3D(-130, -130, -50),//top left
                new Point3D(-130, 130, -40),//bottom left
                new Color(20, 20, 20), new Material(0.015, 0.005, 0));
        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("triangles in the sun");
        myScene.setCameraScreenDistance(170);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        DirectionalLight myDirectionalLight = new DirectionalLight(
                new Color(255, 0, 0),
                new Vector(0, 1, -39));
        myScene.addLightSource(myDirectionalLight);


        ImageWriter sceneWriter = new ImageWriter("triangles in the sun",1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

    @Test
    public void directionalight1() {

        Sphere middle = new Sphere(new Point3D(0, 0, -50),
                49,new Color(0,0,0),new Material(1, 1,1));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere in the sun");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(20, 20, 20), 0));

        DirectionalLight myDirectionalLight = new DirectionalLight(
                new Color(20, 0, 0),
                new Vector(-3, 3, -3));
        myScene.addLightSource(myDirectionalLight);

        ImageWriter sceneWriter = new ImageWriter("sphere in the sun",1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }
}