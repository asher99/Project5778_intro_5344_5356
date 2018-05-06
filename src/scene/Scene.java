package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<LightSource> sceneLightSources;

    // ***************** Constructors ********************** //

    /**
     * @param str scene name determined by the user.
     *            <p>
     *            set scene name upon user argument, set all other fields with default value.
     */
    public Scene(String str) {
        sceneName = str;
        sceneBackgroundColor = Color.BLACK;
        shapesInScene = new Geometries();
        sceneCamera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(1, 0, 0));
        cameraScreenDistance = 4;
        sceneAmbientLight = new AmbientLight();
        sceneLightSources = new ArrayList<LightSource>();
    }

    /**
     * set scene name upon user argument , sets all fields by demand
     *
     * @param str
     * @param color
     * @param g
     * @param camera
     * @param distance
     * @param light
     */
    public Scene(String str, Color color, Geometries g, Camera camera, double distance, AmbientLight light) {
        sceneName = str;
        sceneBackgroundColor = color;
        shapesInScene = g;
        sceneCamera = camera;
        cameraScreenDistance = distance;
        sceneAmbientLight = light;
    }
    // ***************** Getters/Setters ********************** //

    /**
     * getter
     *
     * @return
     */
    public Color getSceneBackgroundColor() {

        return sceneBackgroundColor;
    }

    /**
     * setter
     *
     * @param sceneBackgroundColor
     */
    public void setSceneBackgroundColor(Color sceneBackgroundColor) {
        this.sceneBackgroundColor = sceneBackgroundColor;
    }

    /**
     * getter
     *
     * @return
     */
    public Camera getSceneCamera() {
        return sceneCamera;
    }

    /**
     * setter
     *
     * @param sceneCamera
     */
    public void setSceneCamera(Camera sceneCamera) {
        this.sceneCamera = sceneCamera;
    }

    /**
     * getter
     *
     * @return
     */
    public double getCameraScreenDistance() {
        return cameraScreenDistance;
    }

    /**
     * getter
     * @return
     */
    public List<LightSource> getSceneLightSources() {
        return sceneLightSources;
    }

    /**
     * setter
     *
     * @param cameraScreenDistance
     */
    public void setCameraScreenDistance(double cameraScreenDistance) {
        this.cameraScreenDistance = cameraScreenDistance;
    }

    /**
     * setter
     *
     * @param sceneAmbientLight
     */
    public void setSceneAmbientLight(AmbientLight sceneAmbientLight) {
        this.sceneAmbientLight = sceneAmbientLight;
    }

    /**
     * getter
     *
     * @return
     */
    public AmbientLight getSceneAmbientLight() {
        return sceneAmbientLight;
    }

    /**
     * getter
     *
     * @return
     */
    public Geometries getShapesInScene() {
        return shapesInScene;
    }

    // ***************** Operations ******************** //

    /**
     * add a Geometry to this Scene.
     *
     * @param myGeometry - a Geometry object to insert the list of shapes in scene.
     */
    public void addGeometry(Geometry myGeometry) {
        shapesInScene.addGeometry(myGeometry);
    }

    /**
     * add multiple Geometries.
     *
     * @param myGeometries
     */
    public void addGeometries(Geometry... myGeometries) {
        for (Geometry g : myGeometries) {
            addGeometry(g);
        }
    }

    /**
     *adding a light source to a scene.
     * @param ls
     */
    public void addLightSource(LightSource ls){
        sceneLightSources.add(ls);
    }

    /**
     * add multiple light sources
     * @param myLightSources
     */
    public void addLightSources(LightSource... myLightSources){
        for (LightSource ls: myLightSources){
            addLightSource(ls);
        }
    }


}


