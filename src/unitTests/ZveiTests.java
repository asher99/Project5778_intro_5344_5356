import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.*;

import static org.junit.Assert.assertTrue;




import org.junit.Test;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class ZveiTests {


    public void directionalight1(int i, Vector dir) {

        Sphere blueSp = new Sphere(new Point3D(0, 0, -50),
                48.5, new Color(30, 30, 100), new Material(4, 1, 10));
        Sphere greenSp = new Sphere(new Point3D(5, 5, -50),
                48.4, new Color(30, 100, 30), new Material(4, 1, 10));
        Sphere redSp = new Sphere(new Point3D(-5, 5, -50),
                48.2, new Color(100, 30, 30), new Material(4, 1, 10));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));

        Scene myScene = new Scene("spheres with a point light ");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(redSp, greenSp, blueSp);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));

        PointLight mySpotLight = new PointLight(
                new Point3D(-0.55, 3.3, -0.5),
                1, 0.0075, 0.01,
                new Color(155, 155, 155));
        DirectionalLight dl1 = new DirectionalLight(new Color(240, 144, 60), dir);

        myScene.addLightSource(mySpotLight);
        myScene.addLightSource(dl1);


        ImageWriter sceneWriter = new ImageWriter("spheres with a point light" + i, 1000, 1000, 1000, 1000);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();

    }

    @Test
    public void createMany() {

        Vector dir = new Vector(0, -1, 0);

        for (int i = 0; i < 40; i++) {
            dir = new Vector(dir.getVector().getX() - 0.025, dir.getVector().getY() + 0.05, 0);
            directionalight1(i, dir);
        }

        dir = new Vector(-1, +1, 0);
        for (int i = 40; i < 80; i++) {
            dir = new Vector(dir.getVector().getX() + 0.025, dir.getVector().getY() - 0.05, 0);
            directionalight1(i, dir);
        }

    }

    @Test
    public void triangle_hide_sphere_GIF(){
        Triangle triangle = new Triangle(new Point3D(-10,1,-0.99),new Point3D(-8,5,-0.99),new Point3D(-12,5,-0.99),
                new Color(255,0,0),new Material(1,1,20));

        double offset = 0.4;
        for (int i =0; i<50; i++){
            triangle_hide_sphere(triangle,i);
            triangle = shiftTriangleRight(triangle,offset);
        }



    }



    public void triangle_hide_sphere(Triangle tr, double index){
        Sphere middle = new Sphere(new Point3D(0, 0, -50),
                    48.8,new Color(0,20,100),new Material(1, 1,20));

       // Triangle triangle = new Triangle(new Point3D(0,1,-0.99),new Point3D(-2,5,-0.99),new Point3D(2,5,-0.99),
        //        new Color(255,0,0),new Material(1,1,20));

        Camera camera = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Scene myScene = new Scene("sphere and triangle");
        myScene.setCameraScreenDistance(50);
        myScene.setSceneCamera(camera);
        myScene.setSceneBackgroundColor(new java.awt.Color(0, 0, 0));
        myScene.addGeometries(middle,tr);
        myScene.setSceneAmbientLight(new AmbientLight(new Color(20, 20, 20), 0.1));

        PointLight myPointLight = new PointLight(
                new Point3D(-2,2,-3),
                0.5, 0.01, 0.1, // 1, 0.01, 0.1,
                new Color (255,255,255));
        myScene.addLightSource(myPointLight);

        ImageWriter sceneWriter = new ImageWriter("sphere and triangle"+index,640,640,640,640);
        Render myRender = new Render();
        myRender.setScene(myScene);
        myRender.setImageWriter(sceneWriter);

        myRender.renderImage();
        //renderPixel(myRender,400,400);

        //myRender.printGrid(100);
        myRender.getImageWriter().writeToimage();
    }

    public void renderPixel(Render mr,int i, int j) {
        Ray ray = mr.getScene().getSceneCamera().ConstractRaythroughPixel(mr.getImageWriter().getNx(), mr.getImageWriter().getNy(),
                i, j, mr.getScene().getCameraScreenDistance(), mr.getImageWriter().getWidth(), mr.getImageWriter().getHeight());

        //find the intersections of the ray with the scene geometries.
        Map<Geometry, List<Point3D>> intersectionPoints = mr.getScene().getShapesInScene().findIntersections(ray);

        // write to that pixel the right color.
        if (intersectionPoints.isEmpty())
            mr.getImageWriter().writePixel(i, j, mr.getScene().getSceneBackgroundColor());
        else {
            Map<Geometry, Point3D> closestPoint = mr.getClosestPoint(intersectionPoints);
            Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
            mr.getImageWriter().writePixel(i, j, mr.calcColor(entry.getKey(), entry.getValue()).getColor());
        }


    }

    public Triangle shiftTriangleRight(Triangle tr, double offset){

        Point3D offsetPoint = new Point3D(offset,0,0);
        return new Triangle(Point3D.add(tr.getA(),offsetPoint),Point3D.add(tr.getB(),offsetPoint),Point3D.add(tr.getC(),offsetPoint),tr.getEmission(),tr.getMaterial());
    }

}
