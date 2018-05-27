import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import geometries.Sphere;
import org.junit.Test;
import primitives.*;

import static org.junit.Assert.assertTrue;




import org.junit.Test;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import static org.junit.Assert.*;
public class ZveiTests {


    public void directionalight1(int i, Vector dir) {

        Sphere blueSp = new Sphere(new Point3D(0, 0, -50),
                48.5,new Color(30,30,100),new Material(4, 1,10));
        Sphere greenSp = new Sphere(new Point3D(5, 5, -50),
                48.4,new Color(30,100,30),new Material(4, 1,10));
        Sphere redSp = new Sphere(new Point3D(-5, 5, -50),
                48.2,new Color(100,30,30),new Material(4, 1,10));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("spheres with a point light ");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(redSp,greenSp,blueSp);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        PointLight mySpotLight = new PointLight(
                new Point3D(-0.55,3.3,-0.5),
                1, 0.0075, 0.01,
                new Color (155,155,155));
        DirectionalLight dl1 = new DirectionalLight(new Color(240,144,60),dir);

        myScene.addLightSource(mySpotLight);
        myScene.addLightSource(dl1);



        ImageWriter sceneWriter = new ImageWriter("spheres with a point light" + i,1000,1000,1000,1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();

    }

    @Test
    public void createMany() {

        Vector dir = new Vector(0,-1,0);

        for(int i =0;i<40;i++){
            dir = new Vector(dir.getVector().getX()-0.025, dir.getVector().getY()+0.05,0);
            directionalight1(i,dir);
        }

        dir = new Vector(-1,+1,0);
        for(int i =40;i<80;i++){
            dir = new Vector(dir.getVector().getX()+0.025, dir.getVector().getY()-0.05,0);
            directionalight1(i,dir);
        }

    }
}
