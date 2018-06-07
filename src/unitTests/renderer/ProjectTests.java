package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.LimitedCylinder;
import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import scene.Scene;

public class ProjectTests {

    @Test
    public void newtonCradle(){

        Plane floor = new Plane(new Point3D(0,5,-50),new Vector(0,-1,0),
                new Color(200,200,200),new Material(1,1,0,0,5));

        Plane mirror = new Plane(new Point3D(0,0,-100),new Vector(0,0,1),
                new Color(255,0,0),new Material(1,1,0,0,5));


        Triangle base1 = new Triangle(new Point3D(40,4.9,-3),new Point3D(-40,4.9,-3),new Point3D(40,4,-80)
                ,new Color(225,255,0),new Material(1,1,1));

        Triangle base2 = new Triangle(new Point3D(40,4.9,-80),new Point3D(-40,4,-80),new Point3D(-40,4.9,-3)
                ,new Color(225,0,255),new Material(1,1,1));

        LimitedCylinder side1 = new LimitedCylinder(3,new Point3D(37,-70,-30),
                new Ray(new Point3D(37,4.01,-77),new Vector(0,1,0))
                ,new Color(0,255,0),new Material(1,1,0,0,5));

        LimitedCylinder side2 = new LimitedCylinder(3,new Point3D(-37,-70,-30),
                new Ray(new Point3D(-37,4.01,-77),new Vector(0,1,0))
                ,new Color(0,255,0),new Material(1,1,0,0,5));

        /*LimitedCylinder side3 = new LimitedCylinder(3,new Point3D(38,-70,-30),
                new Ray(new Point3D(37,4.01,-77),new Vector(0,1,0))
                ,new Color(0,255,0),new Material(1,1,0,0,5));

        LimitedCylinder side4 = new LimitedCylinder(3,new Point3D(38,-70,-30),
                new Ray(new Point3D(37,4.01,-77),new Vector(0,1,0))
                ,new Color(0,255,0),new Material(1,1,0,0,5));*/


        Camera camera = new Camera(new Point3D(0, -20, -25),
                new Vector(1, 0, 0),
                new Vector(0, 1, 0));

        Scene myScene = new Scene("newton Cradle");
        myScene.setCameraScreenDistance(130);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(floor,mirror,base1,base2,side1,side2);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(12, 12, 0),
                1, 0.00075, 0.0008,
                new Color(255, 255, 255), new Vector(-12, -12, -15));

        DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40, 0, 0), new Vector(1.5, -3, -1));
        //myScene.addLightSource(mySpotLight);
        //myScene.addLightSource(myDirectionalLight);


        ImageWriter sceneWriter = new ImageWriter("newton Cradle", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.renderPixel(450, 450);
        //myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();
    }
}
