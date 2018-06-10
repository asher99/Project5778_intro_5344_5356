package renderer;

import elements.*;
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
                new Color(25, 25, 25), new Material(1, 1, 0, 0, 20));

        Plane mirror = new Plane(new Point3D(80, -100, 0), new Vector(1, -1, 0),
                new Color(100, 100, 100), new Material(1, 1, 0, 0, 5));


        Triangle base1 = new Triangle(new Point3D(50, 4, 0), new Point3D(-50, 4, 0), new Point3D(50, 4, -100)
                , new Color(0, 0, 0), new Material(1, 1, 0, 0, 1));

        Triangle base2 = new Triangle(new Point3D(50, 4, -100), new Point3D(-50, 4, -100), new Point3D(-50, 4, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0, 0, 1));

        Color cylinderColor = new Color(0, 0, 150);
        Material cylinderMaterial = new Material(1, 1, 0, 0, 20);

        LimitedCylinder side1 = new LimitedCylinder(3, new Point3D(47, -160, -97),
                new Ray(new Point3D(47, 4, -97), new Vector(0, 1, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder side2 = new LimitedCylinder(3, new Point3D(-47, -160, -97),
                new Ray(new Point3D(-47, 4, -97), new Vector(0, 1, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder side3 = new LimitedCylinder(3, new Point3D(-47, -160, -6),
                new Ray(new Point3D(-47, 4, -6), new Vector(0, 1, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder side4 = new LimitedCylinder(3, new Point3D(47, -160, -6),
                new Ray(new Point3D(47, 4, -6), new Vector(0, 1, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder bar1 = new LimitedCylinder(3, new Point3D(-47, -160, -3),
                new Ray(new Point3D(47, -160, -6), new Vector(1, 0, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder bar2 = new LimitedCylinder(3, new Point3D(-47, -160, -97),
                new Ray(new Point3D(47, -160, -97), new Vector(1, 0, 0))
                , cylinderColor, cylinderMaterial);

        Color ballColor = new Color(128, 128, 128);

        Material ballMaterial = new Material(1, 1, 1, 0, 20);

        Sphere ball1 = new Sphere(new Point3D(-40, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball2 = new Sphere(new Point3D(-20, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball3 = new Sphere(new Point3D(0, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball4 = new Sphere(new Point3D(20, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball5 = new Sphere(new Point3D(40, -15, -47), 10, ballColor, ballMaterial);

        Color stringColor = new Color(128, 128, 128);

        Material stringMaterial = new Material(1, 1, 0, 0, 20);

        // strings on left side
        LimitedCylinder string11 = new LimitedCylinder(1, new Point3D(-40, -160, -97),
                new Ray(new Point3D(-40, -15, -47), new Vector(0, 29, 10))
                , stringColor, stringMaterial);

        LimitedCylinder string21 = new LimitedCylinder(1, new Point3D(-20, -160, -97),
                new Ray(new Point3D(-20, -15, -47), new Vector(0, 29, 10))
                , stringColor, stringMaterial);

        LimitedCylinder string31 = new LimitedCylinder(1, new Point3D(0, -160, -97),
                new Ray(new Point3D(0, -15, -47), new Vector(0, 29, 10))
                , stringColor, stringMaterial);

        LimitedCylinder string41 = new LimitedCylinder(1, new Point3D(20, -160, -97),
                new Ray(new Point3D(20, -15, -47), new Vector(0, 29, 10))
                , stringColor, stringMaterial);

        LimitedCylinder string51 = new LimitedCylinder(1, new Point3D(40, -160, -97),
                new Ray(new Point3D(40, -15, -47), new Vector(0, 29, 10))
                , stringColor, stringMaterial);

        LimitedCylinder string12 = new LimitedCylinder(1, new Point3D(-20, -160, -3),
                new Ray(new Point3D(-20, -15, -47), new Vector(0, 145, -44))
                , stringColor, stringMaterial);

        LimitedCylinder string22 = new LimitedCylinder(1, new Point3D(-40, -160, -3),
                new Ray(new Point3D(-40, -15, -47), new Vector(0, 145, -44))
                , stringColor, stringMaterial);

        LimitedCylinder string32 = new LimitedCylinder(1, new Point3D(0, -160, -3),
                new Ray(new Point3D(0, -15, -47), new Vector(0, 145, -44))
                , stringColor, stringMaterial);

        LimitedCylinder string42 = new LimitedCylinder(1, new Point3D(20, -160, -3),
                new Ray(new Point3D(20, -15, -47), new Vector(0, 145, -44))
                , stringColor, stringMaterial);

        LimitedCylinder string52 = new LimitedCylinder(1, new Point3D(40, -160, -3),
                new Ray(new Point3D(40, -15, -47), new Vector(0, 145, -44))
                , stringColor, stringMaterial);

        Camera camera = new Camera(/*new Point3D(0, -40, -25),
                new Vector(1, 0, 0),
                new Vector(0, 1, 0));
                new Point3D(0, 0, 20),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));*/
                new Point3D(-200, -50, -47),
                new Vector(-1, -1, 0),
                new Vector(1, -1, 0));

        Scene myScene = new Scene("newton Cradle");
        myScene.setCameraScreenDistance(220);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(/**/floor, mirror, base1, base2,
                side1, side2, side3, side4, bar1, bar2,
                ball1, ball2, ball3, ball4, ball5,
                string11, string21, string31, string41, string51,
                string12, string22, string32, string42, string52);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(-60, -100, -150),
                1, 0.00075, 0.00008,
                new Color(255, 255, 255), new Vector(0, -1, 1));

        PointLight myPointLight = new PointLight(
                new Point3D(0, -100, -50), 1, 0.076, 0.00008, new Color(255, 0, 255));


        // DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40, 0, 0), new Vector(1.5, -3, -1));
        myScene.addLightSource(mySpotLight);
        // myScene.addLightSource(myDirectionalLight);
        // myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("newton Cradle", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.renderPixel(450, 450);
        //myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();
    }

    @Test
    public void newtonCradle2() {

        Plane floor = new Plane(new Point3D(0, 5, -50), new Vector(0, -1, 0),
                new Color(20, 20, 20), new Material(1, 1, 0, 0, 10));

        Plane mirror = new Plane(new Point3D(0, 0, -200), new Vector(0, 0, 1),
                new Color(0, 0, 200), new Material(1, 1, 0, 0, 10));

        Color ballSilver = new Color(128, 128, 128);
        Material ballMaterial = new Material(1, 1, 0, 1, 20);

        Sphere ball1 = new Sphere(new Point3D(-50, -21, -47), 10, ballSilver, ballMaterial);

        Sphere ball2 = new Sphere(new Point3D(-20, -15, -47), 10, ballSilver, ballMaterial);

        Sphere ball3 = new Sphere(new Point3D(0, -15, -47), 10, ballSilver,ballMaterial );

        Sphere ball4 = new Sphere(new Point3D(20, -15, -47), 10, ballSilver, ballMaterial);

        Sphere ball5 = new Sphere(new Point3D(40, -15, -47), 10, ballSilver, ballMaterial);

        Color stringColor = new Color(211, 211, 211);

        Material stringMaterial = new Material(1, 1, 0, 0, 20);
        LimitedCylinder string11 = new LimitedCylinder(1, new Point3D(-40, -130, -97),
                new Ray(new Point3D(-50, -30, -47), new Vector(-1, 80, 5))
                , stringColor, stringMaterial);

        LimitedCylinder string21 = new LimitedCylinder(1, new Point3D(-20, -130, -97),
                new Ray(new Point3D(-20, -24, -47), new Vector(0, 1, 0))
                , stringColor, stringMaterial);

        LimitedCylinder string31 = new LimitedCylinder(1, new Point3D(0, -130, -97),
                new Ray(new Point3D(0, -24, -47), new Vector(0, 1, 0))
                , stringColor, stringMaterial);

        LimitedCylinder string41 = new LimitedCylinder(1, new Point3D(20, -130, -97),
                new Ray(new Point3D(20, -24, -47), new Vector(0, 1, 0))
                , stringColor, stringMaterial);

        LimitedCylinder string51 = new LimitedCylinder(1, new Point3D(40, -130, -97),
                new Ray(new Point3D(40, -24, -47), new Vector(0, 1, 0))
                , stringColor, stringMaterial);


        Camera camera = new Camera(
                new Point3D(0, -15, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));


        Scene myScene = new Scene("row of balls1");
        myScene.setCameraScreenDistance(130);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(/**/floor, mirror,
                ball1, ball2, ball3, ball4, ball5,
                string11, string21, string31, string41, string51
        );
        myScene.setSceneAmbientLight(new AmbientLight(new Color(20, 20, 20), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(-60, -40, 10),
                1, 0.0075, 0.0008,
                new Color(255, 255, 255), new Vector(0, 1, -1));

        PointLight myPointLight = new PointLight(
                new Point3D(0, -100, 0), 1, 0.076, 0.0008,
                new Color(255, 255, 255));


        // DirectionalLight myDirectionalLight = new DirectionalLight(new Color(40, 0, 0), new Vector(1.5, -3, -1));
        //myScene.addLightSource(mySpotLight);
        // myScene.addLightSource(myDirectionalLight);
        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("row of balls1", 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        //myRender.renderPixel(450, 450);
        //myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();
    }
}
