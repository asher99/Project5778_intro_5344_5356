package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class Scene represents a scene.
 * the scene has a Camera, Geometry objects and background default color.
 */
public class Scene {

    private String sceneName;
    private Color sceneBackgroundColor;
    private Geometries shapesInScene;
    private Camera sceneCamera;
    private double cameraScreenDistance;
    private AmbientLight sceneAmbientLight;

    // ***************** Constructors ********************** //

    /**
     *
     * @param str scene name determined by the user.
     *
     *  set scene name upon user argument, set all other fields with default value.
     */
    public Scene(String str)
    {
        sceneName = str;
        sceneBackgroundColor = Color.BLACK;
        shapesInScene = new Geometries();
        sceneCamera = new Camera( new Point3D(0,0,0), new Vector(0,0,1), new Vector(1,0,0));
        cameraScreenDistance = 4;
        sceneAmbientLight = new AmbientLight();
    }

    // ***************** Getters/Setters ********************** //

    public Color getSceneBackgroundColor() {
        return sceneBackgroundColor;
    }

    public void setSceneBackgroundColor(Color sceneBackgroundColor) {
        this.sceneBackgroundColor = sceneBackgroundColor;
    }

    public Camera getSceneCamera() {
        return sceneCamera;
    }

    public void setSceneCamera(Camera sceneCamera) {
        this.sceneCamera = sceneCamera;
    }

    public double getCameraScreenDistance() {
        return cameraScreenDistance;
    }

    public void setCameraScreenDistance(double cameraScreenDistance) {
        this.cameraScreenDistance = cameraScreenDistance;
    }

    public void setSceneAmbientLight(AmbientLight sceneAmbientLight) {
        this.sceneAmbientLight = sceneAmbientLight;
    }

    public AmbientLight getSceneAmbientLight() {
        return sceneAmbientLight;
    }

    public Geometries getShapesInScene() {
        return shapesInScene;
    }

    // ***************** Operations ******************** //

    /**
     * add a Geomtry to this Scene.
     * @param myGeometry - a Geometry object to insert the list of shapes in scene.
     */
    public void addGeometry( Geometry myGeometry){
        shapesInScene.addGeometry(myGeometry);
    }

    /**
     * add multiple Geometries.
     * @param myGeometries
     */
    public void addGeometries(Geometry... myGeometries){
        for (Geometry g: myGeometries) {
            addGeometry(g);
        }
    }




}


