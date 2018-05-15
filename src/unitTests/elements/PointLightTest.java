package elements;

import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import static org.junit.Assert.*;

public class PointLightTest {

    @Test
    public void pointLight() {
       Sphere middle = new Sphere(new Point3D(0, 0, -50),
                49,new Color(0,0,100),new Material(0.275, 0.1075,1));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere with a point light");
        myScene.setCameraScreenDistance(60);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(120, 120, 120), 0.1));

        PointLight mySpotLight = new PointLight(
                new Point3D(-1.5,1.5,-1),
                1, 1.7, 0.1,
                new Color (255,255,255));
        myScene.addLightSource(mySpotLight);

        ImageWriter sceneWriter = new ImageWriter("sphere with a point light",1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

    @Test
    public void pointLight1() {


        Triangle first = new Triangle(
                new Point3D(-130, 130, -40),//bottom left
                new Point3D(130, 130, -40),//bottom right
                new Point3D(130, -130, -50),//top right
                new Color(20, 20, 20), new Material());
        Triangle second = new Triangle(
                new Point3D(130, -130, -50),//top right
                new Point3D(-130, -130, -50),//top left
                new Point3D(-130, 130, -40),//bottom left
                new Color(20, 20, 20), new Material());
        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("triangles in the spot light");
        myScene.setCameraScreenDistance(170);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        PointLight mySpotLight = new PointLight(
                new Point3D(0, 0, -1),
                0.1, 0.05, 0.01,
                new Color(255, 150, 150));
        myScene.addLightSource(mySpotLight);


        ImageWriter sceneWriter = new ImageWriter("triangles with a point light",1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

}