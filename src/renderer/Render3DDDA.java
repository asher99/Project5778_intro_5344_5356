package renderer;

import elements.LightSource;
import elements.PointLight;
import geometries.*;
import primitives.*;

import java.util.HashMap;
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
                Map<Geometry, Point3D> intersections = scene.getGrid().rayTrace(ray);

                // if there is no intersection in the voxels: try the background geometries.
                if (intersections == null) {

                    for (Geometry g : scene.getGrid().getBackgroundGeometries().getGeometries()) {
                        Map<Geometry, List<Point3D>> intersectionPoints = g.findIntersections(ray);
                        if (!(intersectionPoints == null)) {
                            intersections = getClosestPoint(getScene().getSceneCamera().getP0(), intersectionPoints);
                            break;
                        }
                    }

                    // if there is no intersection with the background geometries either: give it the background color.
                    if (intersections == null) {
                        imageWriter.writePixel(i, j, scene.getSceneBackgroundColor());
                    }
                    // else sent the intersection point and the background geometry to calcColor.
                    else {
                        Map.Entry<Geometry, Point3D> entry = intersections.entrySet().iterator().next();
                        imageWriter.writePixel(i, j, calcColor3DDDA(entry.getKey(), entry.getValue(), ray).getColor());
                    }
                }
                // else sent the intersection point and the geometry in the voxel to calcColor.
                else {
                    Map.Entry<Geometry, Point3D> entry = intersections.entrySet().iterator().next();
                    imageWriter.writePixel(i, j, calcColor3DDDA(entry.getKey(), entry.getValue(), ray).getColor());
                }
            }
        }

    }

    /**
     * call the real calcColor method.
     *
     * @param geo
     * @param p
     * @param inRay
     * @return
     */
    private Color calcColor3DDDA(Geometry geo, Point3D p, Ray inRay) {
        return calcColor3DDDA(geo, p, inRay, MAX_CALC_COLOR_LEVEL, 1);
    }


    /**
     * calculate color in a point.
     * using Phong Reflectance model:
     * ambient light.
     * emission light
     * diffusion light
     * specular light
     * <p>
     * <p>
     * This version implement the 3DDDA algorithm.
     *
     * @param p
     * @return
     */
    public Color calcColor3DDDA(Geometry geo, Point3D p, Ray inRay, int level, double k) {

        if (level == 0 || Coordinate.isZero(k)) {
            return new Color(0, 0, 0);
        }/**/

        //ambient light
        Color color = scene.getSceneAmbientLight().getIntensity();

        //emission light
        color.add(geo.getEmission());

        //prepare for iterating over all light sources.
        //identify the material, get the normal vector in the checked point.
        Vector n = geo.getNormal(p);
        int nShininess = geo.getMaterial().getnShininess();
        double kd = geo.getMaterial().getKd();
        double ks = geo.getMaterial().getKs();

        // foreach light source in the scene: add the diffusion and specular lights.
        for (LightSource lightSource : scene.getSceneLightSources()) {
            Vector l = lightSource.getL(p);

            Vector v = new Vector(scene.getSceneCamera().getP0(), p);
            //Vector v = new Vector(inRay.getDirection().getVector());

            // check if the Diffusion and Specular components are in the
            // same side of the tangent surface as the light source.
            // if true - return the scaled color.
            // if false - return just a (0,0,0) color that can't change the result in the rendering procedure.
            if ((Vector.dotProduct(l, n) > 0 && Vector.dotProduct(v, n) > 0) || (Vector.dotProduct(l, n) < 0 && Vector.dotProduct(v, n) < 0)) {
                double shadowK = occluded3DDDA(lightSource, l, p, geo);
                if (!new Coordinate(0).equals(shadowK * k)) {
                    Color lightIntensity = lightSource.getIntensity(p);
                    lightIntensity.scale(shadowK);
                    color.add(calcDiffusive(kd, l, n, v, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(n, geo, p, inRay);
        Map.Entry<Geometry, Point3D> reflectedPoint = findClosestIntersection3DDDA(reflectedRay);
        Color reflectedLight;

        if (reflectedPoint == null) {
            reflectedLight = new Color(0, 0, 0);
        } else {
            double kr = geo.getMaterial().getKr();

            reflectedLight = calcColor3DDDA(reflectedPoint.getKey(), reflectedPoint.getValue(), reflectedRay, level - 1, k * kr);
            reflectedLight.scale(kr);
        }


        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geo, p, inRay);
        Map.Entry<Geometry, Point3D> refractedPoint = findClosestIntersection3DDDA(refractedRay);
        Color refractedLight;

        if (refractedPoint == null) {
            refractedLight = new Color(0, 0, 0);
        } else {
            double kt = geo.getMaterial().getKt();
            refractedLight = calcColor3DDDA(refractedPoint.getKey(), refractedPoint.getValue(), refractedRay, level - 1, k * kt);
            refractedLight.scale(kt);
        }

        color.add(reflectedLight, refractedLight);
        return color;

    }


    /**
     * finding the closest that intersect Ray.
     * This version implement the 3DDDA algorithm.
     *
     * @param inRay
     * @return
     */
    private Map.Entry<Geometry, Point3D> findClosestIntersection3DDDA(Ray inRay) {
        Map<Geometry, Point3D> intersection = scene.getGrid().rayTrace(inRay);
        if (intersection == null)
            return null;
        return intersection.entrySet().iterator().next();
    }


    /**
     * find if a point is occluded - there is a Geometry blocking the way to the light source.
     * This version implement the 3DDDA algorithm.
     *
     * @param l   - vector from the light source to the object
     * @param p   - intersection point between the ray and the Geometry.
     * @param geo - Geometry.
     * @return
     */
    private double occluded3DDDA(LightSource lightSource, Vector l, Point3D p, Geometry geo) {
        Vector lightDirection = l.normal().multiplyByScalar(-1); // from point to light source

        Vector normal = geo.getNormal(p);
        /*Vector epsVector = normal.multiplyByScalar(Vector.dotProduct(normal, lightDirection) > 0 ? 2 : -2);
        Point3D geometryPoint = Point3D.add(p, epsVector.getVector());*/

        Ray lightRay = new Ray(/*geometryPoint*/p, lightDirection);
        Map<Geometry, Point3D> intersectionPoints = scene.getGrid().rayTrace(lightRay);
        if (intersectionPoints == null)
            return 1;
        else {

            if (intersectionPoints.containsKey(geo)) {
                intersectionPoints.remove(geo);
                if (intersectionPoints.isEmpty())
                    return 1;
            }

            // Check if someone block the way:
            //       if the vector between the point on the Geometry and the intersection point
            //       equals to the ray direction vector.
            //
            //     Also, if the LightSource have position make sure that the distance
            //       between the point on Geometry and the LightSource position
            //       is bigger than the distance
            //       between the point on the Geometry and the blocking Geometry.
            double shadowK = 1;

            Map.Entry<Geometry, Point3D> intersection = intersectionPoints.entrySet().iterator().next();

            Vector offset = new Vector(lightRay.getPoint(), intersection.getValue());
            if (lightRay.getDirection().equals(offset.normal())) {

                // in case the LightSource has origin.
                if (lightSource instanceof PointLight) {
                    Vector pToLsPosition = new Vector(p, ((PointLight) lightSource).getPosition());
                    Vector pToIntersectionPoint = new Vector(p, intersection.getValue());

                    if (pToIntersectionPoint.sizeOfVector() < pToLsPosition.sizeOfVector()) {
                        shadowK *= intersection.getKey().getMaterial().getKt();

                    }
                } else {
                    shadowK *= intersection.getKey().getMaterial().getKt();
                }
            }
            return shadowK;
        }

    }


    /**
     * render a specific pixel.
     * this method is used for debugging, enabling check what exactly get wrong in a certain pixel
     * This version implement the 3DDDA algorithm.
     *
     * @param i
     * @param j
     */
    public void renderPixel3DDA(int i, int j) {

        // construct ray through that pixel
        Ray ray = scene.getSceneCamera().ConstractRaythroughPixel(imageWriter.getNx(), imageWriter.getNy(),
                i, j, scene.getCameraScreenDistance(), imageWriter.getWidth(), imageWriter.getHeight());


        // RAY TRACING
        Map<Geometry, Point3D> intersections = scene.getGrid().rayTrace(ray);

        // if there is no intersection in the voxels: try the background geometries.
        if (intersections == null) {

            for (Geometry g : scene.getGrid().getBackgroundGeometries().getGeometries()) {
                Map<Geometry, List<Point3D>> intersectionPoints = g.findIntersections(ray);
                if (!(intersectionPoints == null)) {
                    intersections = getClosestPoint(getScene().getSceneCamera().getP0(), intersectionPoints);
                    break;
                }
            }

            // if there is no intersection with the background geometries either: give it the background color.
            if (intersections == null) {
                imageWriter.writePixel(i, j, scene.getSceneBackgroundColor());
            }
            // else sent the intersection point and the background geometry to calcColor.
            else {
                Map.Entry<Geometry, Point3D> entry = intersections.entrySet().iterator().next();
                imageWriter.writePixel(i, j, calcColor3DDDA(entry.getKey(), entry.getValue(), ray).getColor());
            }
        }
        // else sent the intersection point and the geometry in the voxel to calcColor.
        else {
            Map.Entry<Geometry, Point3D> entry = intersections.entrySet().iterator().next();
            imageWriter.writePixel(i, j, calcColor3DDDA(entry.getKey(), entry.getValue(), ray).getColor());
        }
    }
}



