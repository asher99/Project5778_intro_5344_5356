package renderer;

import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
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
                    gridWriter.writePixel(i,j, Color.WHITE);

                else gridWriter.writePixel(i,j,Color.BLACK);
            }

        }

        gridWriter.writeToimage();

    }

    @Test
    public void writeToimage2() {

        Scene ballScene = new Scene("ball example scene");
        ballScene.addGeometry(new Sphere(new Point3D(60,0,0),40));
        ballScene.setCameraScreenDistance(50);

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

        //camera and scene
        Camera camera = new Camera(new Point3D(0,0,0),new Vector(0,-1,0),new Vector(0,0,-1));
        Scene myScene = new Scene("Triangles and Sphere, Asher and Zvei, Targil 4");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(75,127,190));
        myScene.addGeometries(upLeft,upRight,downLeft,downRight,middle);

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


}