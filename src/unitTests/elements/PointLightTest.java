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
                49,new Color(0,0,100),new Material());

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere with a point light");
        myScene.setCameraScreenDistance(60);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        PointLight mySpotLight = new PointLight(
                new Point3D(-2,2,-2),
                1,3,1,
                new Color (255,0,255));
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
                new Point3D(-100,-100,-60),
                new Point3D(-100,100,-40),
                new Point3D(100,100,-40),
                new Color(20,20,20),new Material());
        Triangle second = new Triangle(
                new Point3D(-100,-100,-60),
                new Point3D(100,-100,-60),
                new Point3D(100,100,-40),
                new Color(20,20,20),new Material());
        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("triangles with a point light");
        myScene.setCameraScreenDistance(100);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first,second);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        PointLight mySpotLight = new PointLight(
                new Point3D(10,10,-30),
                5,1,1,
                new Color (200,0,0));
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