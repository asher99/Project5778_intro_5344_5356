package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import primitives.Color;
import scene.Scene;

import java.awt.*;

import static org.junit.Assert.*;

public class ImageWriterTest {

    @Test
    public void writeToimage() {

        ImageWriter gridWriter = new ImageWriter("asher and zvei grid test",500,500,500,500);

        for (int i = 0; i <= 499; i++){
            for (int j = 499; j > 0; j--){

                if (i % 50 == 0 || j % 50 == 0)
                    gridWriter.writePixel(i,j, java.awt.Color.WHITE);

                else gridWriter.writePixel(i,j,java.awt.Color.BLACK);
            }

        }

        gridWriter.writeToimage();

    }

    @Test
    public void writeToimage2() {

        Scene ballScene = new Scene("ball example scene");
        Sphere ball = new Sphere(new Point3D(60,0,0),40);
        ball.emission = new Color(255,0,0);
        ballScene.addGeometry(ball);
        ballScene.setCameraScreenDistance(50);
        ballScene.setSceneAmbientLight(new AmbientLight(new Color(0,0,0),1));

        ImageWriter ballWriter = new ImageWriter("ball example image",500,500,500,500);

        Render ballImageRenderer = new Render();
        ballImageRenderer.setImageWriter(ballWriter);
        ballImageRenderer.setScene(ballScene);

        ballImageRenderer.renderImage();
        ballImageRenderer.printGrid(50);
        ballImageRenderer.getImageWriter().writeToimage();
    }

    @Test
    public void writeToImage3(){

        // define Geometries.
        Triangle upLeft     = new Triangle(new Point3D(100,0,-49),new Point3D(0,100,-49),new Point3D(100,100,-49));
        Triangle upRight    = new Triangle(new Point3D(-100,0,-49),new Point3D(0,100,-49),new Point3D(-100,100,-49));
        Triangle downLeft   = new Triangle(new Point3D(100,0,-49),new Point3D(0,-100,-49),new Point3D(100,-100,-49));
        Triangle downRight  = new Triangle(new Point3D(-100,0,-49),new Point3D(0,-100,-49),new Point3D(-100,-100,-49));
        Sphere middle = new Sphere(new Point3D(0,0,-50),35);


        //give some colors to those shapes.
        //upLeft.emission = new Color(0,255,0);
        //downLeft.emission = new Color(255,0,0);
        //downRight.emission= new Color(0,0,255);
       // upRight.emission = new Color(0,255,255);
        middle.emission = new Color(255,255,255);

        //camera and scene
        Camera camera = new Camera(new Point3D(0,0,0),new Vector(0,-1,0),new Vector(0,0,-1));
        Scene myScene = new Scene("Triangles and Sphere, Asher and Zvei, Targil 4");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(75,127,190));
        myScene.addGeometries(upLeft,upRight,downLeft,downRight,middle);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0,0,0),1));

        // image writer and Renderer.
        ImageWriter sceneWriter = new ImageWriter("Triangles and Sphere, Asher and Zvei, Targil 4",500,500,500,500);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();


    }

    @Test
    public void writeToImage4(){

        // defining Geometries.
        Triangle upLeft     = new Triangle(new Point3D(100,0,-49),new Point3D(0,100,-49),new Point3D(100,100,-49));
        Triangle upRight    = new Triangle(new Point3D(-100,0,-49),new Point3D(0,100,-49),new Point3D(-100,100,-49));
        Triangle downLeft   = new Triangle(new Point3D(100,0,-49),new Point3D(0,-100,-49),new Point3D(100,-100,-49));
        Triangle downRight  = new Triangle(new Point3D(-100,0,-49),new Point3D(0,-100,-49),new Point3D(-100,-100,-49));

        Sphere middle = new Sphere(new Point3D(0,0,-50),35);

        Camera camera = new Camera(new Point3D(0,-50,50),new Vector(0,Math.sqrt(2),Math.sqrt(2)),new Vector(0,Math.sqrt(2),-Math.sqrt(2)));

        Scene myScene = new Scene("ubanubu");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(75,127,190));

        myScene.addGeometries(upLeft,upRight,downLeft,downRight,middle);

        ImageWriter sceneWriter = new ImageWriter("ubanubu",500,500,500,500);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();


    }

    @Test
    public void writeToImage5(){

        // defining Geometries.
        Triangle upLeft     = new Triangle(new Point3D(100,0,-49),new Point3D(0,100,-49),new Point3D(100,100,-49),new Color(200,126,70));
        Triangle upRight    = new Triangle(new Point3D(-100,0,-49),new Point3D(0,100,-49),new Point3D(-100,100,-49),new Color(50,200,170));
        Triangle downLeft   = new Triangle(new Point3D(100,0,-49),new Point3D(0,-100,-49),new Point3D(100,-100,-49),new Color(0,255,0));
        Triangle downRight  = new Triangle(new Point3D(-100,0,-49),new Point3D(0,-100,-49),new Point3D(-100,-100,-49),new Color(255,0,0));

        Sphere middle = new Sphere(new Point3D(0,0,-50),35,new Color(100,100,100));

        Camera camera = new Camera(new Point3D(0,0,0),new Vector(0,-1,0),new Vector(0,0,-1));

        Scene myScene = new Scene("colored geometrys");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(75,127,190));

        myScene.addGeometries(upLeft,upRight,downLeft,downRight,middle);

        ImageWriter sceneWriter = new ImageWriter("colored geometrys",500,500,500,500);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        myRender.printGrid(50);
        myRender.getImageWriter().writeToimage();


    }


}