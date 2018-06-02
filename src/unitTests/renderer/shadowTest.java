package renderer;

import elements.*;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import scene.Scene;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class shadowTest {

    @Test
    public void triangle_hide_sphere(){
        Sphere middle = new Sphere(new Point3D(0, 0, -50),
                49,new Color(0,20,100),new Material(1.5, 1,20));

        Triangle triangle = new Triangle(new Point3D(0,1,-0.99),new Point3D(-2,5,-0.99),new Point3D(2,5,-0.99),
               new Color(255,0,0),new Material(1,1,20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere and triangle");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle,triangle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(20, 20, 20), 0.1));

        PointLight myPointLight = new PointLight(
                new Point3D(-2,1,5),
                1, 0.01, 0.025, // 1, 0.01, 0.1,
                new Color (255,255,255));
        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("sphere and triangle",640,640,640,640);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        //myRender.renderPixel(300,500);
        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }


    @Test
    public void ball_on_triangles() {

        Triangle first = new Triangle(
                new Point3D(-130, 130, -25),//bottom left
                new Point3D(130, 130, -25),//bottom right
                new Point3D(130, -130, -35),//top right
                new Color(40, 40, 40), new Material(4, 0.2, 5));

        Triangle second = new Triangle(
                new Point3D(130, -130, -35),//top right
                new Point3D(-130, -130, -35),//top left
                new Point3D(-130, 130, -25),//bottom left
                new Color(40, 40, 40), new Material(4, 0.2, 5));

        Sphere middle = new Sphere(new Point3D(0, 0, -30),
                20, new Color(0, 20, 100), new Material(1, 1, 20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("shadows test");
        myScene.setCameraScreenDistance(70);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(first, second,middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(12, 12, 0),
                1, 0.00075, 0.0008,
                new Color(255, 255, 255), new Vector(-12, -12, -15));

        DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40,0,0),new Vector(1.5,-3,-1));
        myScene.addLightSource(mySpotLight);
        myScene.addLightSource(myDirectionalLight);



        ImageWriter sceneWriter = new ImageWriter("shadows test", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.renderPixel(450,450);
        //myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();


    }
}
