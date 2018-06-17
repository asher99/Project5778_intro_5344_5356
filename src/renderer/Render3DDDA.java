package renderer;

import geometries.*;
import primitives.*;

import java.util.List;
import java.util.Map;

/**
 * class Render3DDDA  for a renderer that implement the 3DDDA algorithm.
 */
public class Render3DDDA extends Render {

    public void renderImage3DDDA() {

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = imageWriter.getNy() - 1; j > 0; j--) {

                // construct ray through that pixel
                Ray ray = scene.getSceneCamera().ConstractRaythroughPixel(imageWriter.getNx(), imageWriter.getNy(),
                        i, j, scene.getCameraScreenDistance(), imageWriter.getWidth(), imageWriter.getHeight());


                // RAY TRACING
                Map<Geometry,Point3D> intersections = scene.getGrid().rayTrace(ray);

                // if there is no intersection in the voxels: try the background geometries.
                if(intersections.isEmpty()){

                    for (Geometry g: scene.getGrid().getBackgroundGeometries().getGeometries()){
                        Map<Geometry, List<Point3D>> intersectionPoints = g.findIntersections(ray);
                        intersections = getClosestPoint(getScene().getSceneCamera().getP0(),intersectionPoints);
                        if(!intersections.isEmpty()){
                            break;
                        }
                    }

                    // if there is no intersection with the background geometries either: give it the background color.
                    if(intersections.isEmpty()){
                        imageWriter.writePixel(i, j, scene.getSceneBackgroundColor());
                    }
                    // else sent the intersection point and the background geometry to calcColor.
                    else{
                        Map.Entry<Geometry, Point3D> entry = intersections.entrySet().iterator().next();
                        imageWriter.writePixel(i, j, calcColor(entry.getKey(), entry.getValue(), ray).getColor());
                    }
                }
                // else sent the intersection point and the geometry in the voxel to calcColor.
                else{
                    Map.Entry<Geometry, Point3D> entry = intersections.entrySet().iterator().next();
                    imageWriter.writePixel(i, j, calcColor(entry.getKey(), entry.getValue(), ray).getColor());
                }
            }
        }

    }























}
