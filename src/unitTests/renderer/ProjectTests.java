package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.LimitedCylinder;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;
import scene.Scene;

public class ProjectTests {

    @Test
    public void newtonCradle() {

        Plane floor = new Plane(new Point3D(0, 5, -50), new Vector(0, -1, 0),
                new Color(25, 25, 100), new Material(1, 1, 0, 1, 20));

        Plane mirror = new Plane(new Point3D(70, 0, 0), new Vector(-1, 0, 0),
                new Color(100, 100, 100), new Material(1, 1, 0, 0, 5));


        Triangle base1 = new Triangle(new Point3D(50, 4, 0), new Point3D(-50, 4, 0), new Point3D(50, 4, -100)
                , new Color(51,51,51), new Material(1, 1,0,1, 1));

        Triangle base2 = new Triangle(new Point3D(50, 4, -100), new Point3D(-50, 4, -100), new Point3D(-50, 4, 0)
                , new Color(51,51,51), new Material(1, 1,0 ,1,1));



        LimitedCylinder side1 = new LimitedCylinder(3, new Point3D(47, -30, -30),
                new Ray(new Point3D(47, 4, -97), new Vector(0, 1, 0))
                , new Color(0, 0, 150), new Material(1, 1, 0, 0, 5));

        LimitedCylinder side2 = new LimitedCylinder(3, new Point3D(-37, -30, -30),
                new Ray(new Point3D(-47, 4, -97), new Vector(0, 1, 0))
                , new Color(0, 0, 150), new Material(1, 1, 0, 0, 5));

        LimitedCylinder side3 = new LimitedCylinder(3, new Point3D(-47, -30, -3),
                new Ray(new Point3D(-47, 4, -6), new Vector(0, 1, 0))
                , new Color(0, 0, 150), new Material(1, 1, 0, 0, 5));

        LimitedCylinder side4 = new LimitedCylinder(3, new Point3D(47, -30, -3),
                new Ray(new Point3D(47, 4, -3), new Vector(0, 1, 0))
                , new Color(0, 0, 150), new Material(1, 1, 0, 0, 5));

        LimitedCylinder bar1 = new LimitedCylinder(3, new Point3D(-47, -30, -3),
                new Ray(new Point3D(47, -30, -3), new Vector(1, 0, 0))
                , new Color(0, 0, 150), new Material(1, 1, 0, 0, 5));

        LimitedCylinder bar2 = new LimitedCylinder(7, new Point3D(-47, -30, -97),
                new Ray(new Point3D(47, -30, -97), new Vector(1, 0, 0))
                , new Color(0, 0, 150), new Material(1, 1, 0, 0, 5));

        Sphere ball1 = new Sphere(new Point3D(-40,-15,-47),10,new Color(192,192,192),new Material(1,1,1,0,20));

        Sphere ball2 = new Sphere(new Point3D(-20,-15,-47),10,new Color(192,192,192),new Material(1,1,1,0,20));

        Sphere ball3 = new Sphere(new Point3D(0,-15,-47),10,new Color(192,192,192),new Material(1,1,1,0,20));

        Sphere ball4 = new Sphere(new Point3D(20,-15,-47),10,new Color(192,192,192),new Material(1,1,1,0,20));

        Sphere ball5 = new Sphere(new Point3D(40,-15,-47),10,new Color(192,192,192),new Material(1,1,1,0,20));

        Camera camera = new Camera(/*new Point3D(0, -40, -25),
                new Vector(1, 0, 0),
                new Vector(0, 1, 0));
                new Point3D(0, 0, 20),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));*/
        new Point3D(-170, -70, -47),
                new Vector(-1, -1, 0),
                new Vector(1, -1, 0));

        Scene myScene = new Scene("newton Cradle");
        myScene.setCameraScreenDistance(70);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(/**/floor, mirror, base1, base2,
                side1, side2, side3, side4,bar1,bar2,
                ball1,ball2,ball3,ball4,ball5);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(0, -100, -50),
                1, 0.075, 0.000008,
                new Color(255, 255, 255), new Vector(0, 1, 0));

        //DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40, 0, 0), new Vector(1.5, -3, -1));
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
