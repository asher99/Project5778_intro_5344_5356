package renderer;

import elements.*;
import geometries.*;
import grid.Grid;
import org.junit.Test;
import primitives.*;
import scene.Scene;

public class ProjectTests {

    @Test
    public void newtonCradle() {

        //Color woodColor = new Color(168, 119, 90);
        Color woodColor = new Color(70, 49, 33);

        Triangle floor1 = new Triangle(new Point3D(-750, 0, -300), new Point3D(-750, 0, 100), new Point3D(750, 0, 100)
                , woodColor, new Material(1, 1, 0, 0, 1));

        Triangle floor2 = new Triangle(new Point3D(750, 0, 100), new Point3D(750, 0, -300), new Point3D(-750, 0, -300)
                , woodColor, new Material(1, 1, 0, 0, 1));

        //Color wallColor = new Color(46, 139, 87);
        Color wallColor = new Color(15, 46, 29);

        Triangle mirror1 = new Triangle(new Point3D(950, 10, -300), new Point3D(-950, -1000, -300), new Point3D(-950, 10, -300)
                , wallColor, new Material(1, 1, 0, 0, 1));

        Triangle mirror2 = new Triangle(new Point3D(950, 10, -300), new Point3D(950, -1000, -300), new Point3D(-950, -1000, -300)
                , wallColor, new Material(1, 1, 0, 0, 1));

        Triangle base1 = new Triangle(new Point3D(50, -4, 0), new Point3D(-50, -4, 0), new Point3D(50, -4, -100)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Triangle base2 = new Triangle(new Point3D(50, -4, -100), new Point3D(-50, -4, -100), new Point3D(-50, -4, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Triangle baseFront1 = new Triangle(new Point3D(50, -4, 0), new Point3D(-50, -4, 0), new Point3D(-50, 0, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Triangle baseFront2 = new Triangle(new Point3D(50, -4, 0), new Point3D(50, 0, 0), new Point3D(-50, 0, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Color cylinderColor = new Color(154, 154, 154);
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

        LimitedCylinder bar1 = new LimitedCylinder(2.5, new Point3D(-47, -160, -6),
                new Ray(new Point3D(47, -160, -6), new Vector(1, 0, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder bar2 = new LimitedCylinder(2.1, new Point3D(-47, -160, -97),
                new Ray(new Point3D(47, -160, -97), new Vector(1, 0, 0))
                , cylinderColor, cylinderMaterial);

        Color ballColor = new Color(128, 128, 128);

        Material ballMaterial = new Material(1, 1, 0.65, 0, 20);

        Sphere ball1 = new Sphere(new Point3D(-40, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball2 = new Sphere(new Point3D(-20, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball3 = new Sphere(new Point3D(0, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball4 = new Sphere(new Point3D(20, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball5 = new Sphere(new Point3D(40, -15, -47), 10, ballColor, ballMaterial);

        Color stringColor = new Color(128, 128, 128);

        Material stringMaterial = new Material(1, 1, 0, 0, 20);

        // strings on left side
        LimitedCylinder string11 = new LimitedCylinder(1, new Point3D(-40, -160, -96),
                new Ray(new Point3D(-40, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string21 = new LimitedCylinder(1, new Point3D(-20, -160, -96),
                new Ray(new Point3D(-20, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string31 = new LimitedCylinder(1, new Point3D(0, -160, -96),
                new Ray(new Point3D(0, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string41 = new LimitedCylinder(1, new Point3D(20, -160, -96),
                new Ray(new Point3D(20, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string51 = new LimitedCylinder(1, new Point3D(40, -160, -96),
                new Ray(new Point3D(40, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string12 = new LimitedCylinder(1, new Point3D(-20, -160, -5),
                new Ray(new Point3D(-20, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string22 = new LimitedCylinder(1, new Point3D(-40, -160, -5),
                new Ray(new Point3D(-40, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string32 = new LimitedCylinder(1, new Point3D(0, -160, -5),
                new Ray(new Point3D(0, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string42 = new LimitedCylinder(1, new Point3D(20, -160, -5),
                new Ray(new Point3D(20, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string52 = new LimitedCylinder(1, new Point3D(40, -160, -5),
                new Ray(new Point3D(40, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        Camera camera = new Camera(
                new Point3D(0, -70, 80),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("newton Cradle2");
        myScene.setCameraScreenDistance(2200);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(floor1, floor2, mirror1, mirror2, base1, base2,
                baseFront1,baseFront2,
                /**/side1, side2, side3, side4, bar1, bar2,
                ball1, ball2, ball3, ball4, ball5/**/,
                string11, string21, string31, string41, string51,
                string12, string22, string32, string42, string52);

        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(-60, -100, -150),
                1, 0.0075, 0.0008,
                new Color(255, 255, 255), new Vector(0, -1, 1));

        PointLight myPointLight = new PointLight(
                new Point3D(0, -100, -50),
                1, 0.0076, 0.0001, new Color(201, 226, 255));

         //myScene.addLightSource(mySpotLight);
        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("newton Cradle2", 5000, 5000, 5000, 5000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
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

        Sphere ball3 = new Sphere(new Point3D(0, -15, -47), 10, ballSilver, ballMaterial);

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

    @Test
    public void gridedProject() {

        //Color woodColor = new Color(168, 119, 90);
        Color woodColor = new Color(70, 49, 33);

        Triangle floor1 = new Triangle(new Point3D(-750, 0, -300), new Point3D(-750, 0, 100), new Point3D(750, 0, 100)
                , woodColor, new Material(1, 1, 0, 0, 1));

        Triangle floor2 = new Triangle(new Point3D(750, 0, 100), new Point3D(750, 0, -300), new Point3D(-750, 0, -300)
                , woodColor, new Material(1, 1, 0, 0, 1));

        //Color wallColor = new Color(46, 139, 87);
        Color wallColor = new Color(15, 46, 29);

        Triangle mirror1 = new Triangle(new Point3D(950, 10, -300), new Point3D(-950, -1000, -300), new Point3D(-950, 10, -300)
                , wallColor, new Material(1, 1, 0, 0, 1));

        Triangle mirror2 = new Triangle(new Point3D(950, 10, -300), new Point3D(950, -1000, -300), new Point3D(-950, -1000, -300)
                , wallColor, new Material(1, 1, 0, 0, 1));

        Triangle base1 = new Triangle(new Point3D(50, -4, 0), new Point3D(-50, -4, 0), new Point3D(50, -4, -100)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Triangle base2 = new Triangle(new Point3D(50, -4, -100), new Point3D(-50, -4, -100), new Point3D(-50, -4, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Triangle baseFront1 = new Triangle(new Point3D(50, -4, 0), new Point3D(-50, -4, 0), new Point3D(-50, 0, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Triangle baseFront2 = new Triangle(new Point3D(50, -4, 0), new Point3D(50, 0, 0), new Point3D(-50, 0, 0)
                , new Color(0, 0, 0), new Material(1, 1, 0.3, 0, 1));

        Color cylinderColor = new Color(154, 154, 154);
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

        LimitedCylinder bar1 = new LimitedCylinder(2.5, new Point3D(-47, -160, -6),
                new Ray(new Point3D(47, -160, -6), new Vector(1, 0, 0))
                , cylinderColor, cylinderMaterial);

        LimitedCylinder bar2 = new LimitedCylinder(2.5, new Point3D(-47, -160, -97),
                new Ray(new Point3D(47, -160, -97), new Vector(1, 0, 0))
                , cylinderColor, cylinderMaterial);

        Color ballColor = new Color(128, 128, 128);

        Material ballMaterial = new Material(1, 1, 0.65, 0, 20);

        Sphere ball1 = new Sphere(new Point3D(-40, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball2 = new Sphere(new Point3D(-20, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball3 = new Sphere(new Point3D(0, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball4 = new Sphere(new Point3D(20, -15, -47), 10, ballColor, ballMaterial);

        Sphere ball5 = new Sphere(new Point3D(40, -15, -47), 10, ballColor, ballMaterial);

        Color stringColor = new Color(128, 128, 128);

        Material stringMaterial = new Material(1, 1, 0, 0, 20);

        // strings on left side
        LimitedCylinder string11 = new LimitedCylinder(1, new Point3D(-40, -160, -96),
                new Ray(new Point3D(-40, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string21 = new LimitedCylinder(1, new Point3D(-20, -160, -96),
                new Ray(new Point3D(-20, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string31 = new LimitedCylinder(1, new Point3D(0, -160, -96),
                new Ray(new Point3D(0, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string41 = new LimitedCylinder(1, new Point3D(20, -160, -96),
                new Ray(new Point3D(20, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string51 = new LimitedCylinder(1, new Point3D(40, -160, -96),
                new Ray(new Point3D(40, -15, -47), new Vector(0, 145, 49))
                , stringColor, stringMaterial);

        LimitedCylinder string12 = new LimitedCylinder(1, new Point3D(-20, -160, -5),
                new Ray(new Point3D(-20, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string22 = new LimitedCylinder(1, new Point3D(-40, -160, -5),
                new Ray(new Point3D(-40, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string32 = new LimitedCylinder(1, new Point3D(0, -160, -5),
                new Ray(new Point3D(0, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string42 = new LimitedCylinder(1, new Point3D(20, -160, -5),
                new Ray(new Point3D(20, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        LimitedCylinder string52 = new LimitedCylinder(1, new Point3D(40, -160, -5),
                new Ray(new Point3D(40, -15, -47), new Vector(0, 145, -42))
                , stringColor, stringMaterial);

        Camera camera = new Camera(
                new Point3D(0, -60, 80),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        // adds all geometries into their own voxel!
        // hand made!!!!!
/*
* our starting point for voxels:
*        X                   Y                  Z
*   115 <-> 69           0 <-> -46          81 <-> 35
*   69 <-> 23            -46 <-> -92        35 <-> -11
*   23 <-> -23           -92 <-> -138       -11 <-> -57
*   -23 <-> -69          -138 <-> -184      -57 <-> -103
*   -69 <-> -115         -184 <-> -230      -103 <-> -149
*
* */
        Geometries geos = new Geometries();
        Grid myGrid = new Grid(geos, new Point3D(115, 0, 81), -46, 5);

        myGrid.getGrid().get(new Point3D(-23, 0, -57)).addGeometries(string11);
        myGrid.getGrid().get(new Point3D(-23, -46, -57)).addGeometries(string11);
        myGrid.getGrid().get(new Point3D(-23, -92, -57)).addGeometries(string11);
        myGrid.getGrid().get(new Point3D(-23, -138, -57)).addGeometries(string11);

        myGrid.getGrid().get(new Point3D(23, 0, -57)).addGeometries(string21,string31,string41);
        myGrid.getGrid().get(new Point3D(23, -46, -57)).addGeometries(string21,string31,string41);
        myGrid.getGrid().get(new Point3D(23, -92, -57)).addGeometries(string21,string31,string41);
        myGrid.getGrid().get(new Point3D(23, -138, -57)).addGeometries(string21,string31,string41);

        myGrid.getGrid().get(new Point3D(69, 0, -11)).addGeometries(string51);
        myGrid.getGrid().get(new Point3D(69, -46, -11)).addGeometries(string51);
        myGrid.getGrid().get(new Point3D(69, 0, -57)).addGeometries(string51);
        myGrid.getGrid().get(new Point3D(69, -46, -57)).addGeometries(string51);
        myGrid.getGrid().get(new Point3D(69, -92, -57)).addGeometries(string51);
        myGrid.getGrid().get(new Point3D(69, -138, -57)).addGeometries(string51);

        myGrid.getGrid().get(new Point3D(-23, -46, -11)).addGeometries(string22,string11);
        myGrid.getGrid().get(new Point3D(-23, -92, -11)).addGeometries(string22);
        myGrid.getGrid().get(new Point3D(-23, -92, 35)).addGeometries(string22);
        myGrid.getGrid().get(new Point3D(-23, -138, 35)).addGeometries(string22);

        myGrid.getGrid().get(new Point3D(23, 0, -11)).addGeometries(string12,string32,string42,string21,string31,string41);
        myGrid.getGrid().get(new Point3D(23, -46, -11)).addGeometries(string12,string32,string42,string21,string31,string41);
        myGrid.getGrid().get(new Point3D(23, -92, -11)).addGeometries(string12,string32,string42);
        myGrid.getGrid().get(new Point3D(23, -92, 35)).addGeometries(string12,string32,string42);
        myGrid.getGrid().get(new Point3D(23, -138, 35)).addGeometries(string12,string32,string42);

        myGrid.getGrid().get(new Point3D(69, 0, -11)).addGeometries(string52);
        myGrid.getGrid().get(new Point3D(69, -46, -11)).addGeometries(string52);
        myGrid.getGrid().get(new Point3D(69, -92, -11)).addGeometries(string52);
        myGrid.getGrid().get(new Point3D(69, -92, 35)).addGeometries(string52);
        myGrid.getGrid().get(new Point3D(69, -138, 35)).addGeometries(string52);

        myGrid.getGrid().get(new Point3D(69, 0, 35)).addGeometries(base1,baseFront1,baseFront2);

        myGrid.getGrid().get(new Point3D(23, 0, 35)).addGeometries(base1,baseFront1,baseFront2);
        myGrid.getGrid().get(new Point3D(23, 0, -57)).addGeometries(base1,base2);

        myGrid.getGrid().get(new Point3D(-23, 0, 35)).addGeometries(base1,base2,baseFront1,baseFront2);

        myGrid.getGrid().get(new Point3D(69, 0, -11)).addGeometries(ball5,ball4,base1,side4);
        myGrid.getGrid().get(new Point3D(23, 0, -11)).addGeometries(ball4,ball3,ball2,base1,base2);

        myGrid.getGrid().get(new Point3D(-23, -138, -11)).addGeometries(bar1,side3);
        myGrid.getGrid().get(new Point3D(23, -138, -11)).addGeometries(bar1);
        myGrid.getGrid().get(new Point3D(69, -138, -11)).addGeometries(bar1,side4);

        myGrid.getGrid().get(new Point3D(-23, -138, -57)).addGeometries(bar2,side2);
        myGrid.getGrid().get(new Point3D(23, -138, -57)).addGeometries(bar2);
        myGrid.getGrid().get(new Point3D(69, -138, -57)).addGeometries(bar2,side2);

        myGrid.getGrid().get(new Point3D(69, 0, -57)).addGeometries(side1,base1,base2);
        myGrid.getGrid().get(new Point3D(69, -46, -57)).addGeometries(side1);
        myGrid.getGrid().get(new Point3D(69, -92, -57)).addGeometries(side1);

        myGrid.getGrid().get(new Point3D(-23, 0, -57)).addGeometries(side2,base2);
        myGrid.getGrid().get(new Point3D(-23, -46, -57)).addGeometries(side2);
        myGrid.getGrid().get(new Point3D(-23, -92, -57)).addGeometries(side2);

        myGrid.getGrid().get(new Point3D(-23, 0, -11)).addGeometries(side3,ball1,base1,base2,string11,string22);
        myGrid.getGrid().get(new Point3D(-23, -46, -11)).addGeometries(side3);
        myGrid.getGrid().get(new Point3D(-23, -92, -11)).addGeometries(side3);

        myGrid.getGrid().get(new Point3D(69, -46, -11)).addGeometries(side4);
        myGrid.getGrid().get(new Point3D(69, -92, -11)).addGeometries(side4);

        myGrid.setBackgroundGeometries(floor1,floor2,mirror1,mirror2);

        Scene myScene = new Scene("grided newton Cradle");
        myScene.setCameraScreenDistance(420);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(floor1, floor2, mirror1, mirror2, base1, base2,
                baseFront1,baseFront2,
                side1, side2, side3, side4, bar1, bar2,
                ball1, ball2, ball3, ball4, ball5,
                string11, string21, string31, string41, string51,
                string12, string22, string32, string42, string52);

        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0));

        SpotLight mySpotLight = new SpotLight(
                new Point3D(-60, -100, -150),
                1, 0.0075, 0.0008,
                new Color(255, 255, 255), new Vector(0, -1, 1));

        PointLight myPointLight = new PointLight(
                new Point3D(0, -100, -50),
                1, 0.0076, 0.0001, new Color(201, 226, 255));

        //myScene.addLightSource(mySpotLight);
        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("grided newton Cradle", 1000, 1000, 1000, 1000);
        Render3DDDA myRender = new Render3DDDA();
        myRender.setScene(myScene);
        myRender.getScene().setGrid(myGrid);
        myRender.setImageWriter(sceneWriter);

        //myRender.renderImage3DDDA();
        myRender.renderImage();
        //myRender.renderPixel3DDA(300,800);
        myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();

    }
}
