package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
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
                48.8,new Color(0,20,100),new Material(1, 1,20));

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
                new Point3D(-2,2,-3),
                0.5, 0.01, 0.1, // 1, 0.01, 0.1,
                new Color (255,255,255));
        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("sphere and triangle",640,640,640,640);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        //renderPixel(myRender,400,400);

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }
}
