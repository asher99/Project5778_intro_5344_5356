package renderer;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
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
        ballScene.addGeometry(new Sphere(new Point3D(10,0,0),3));

        ImageWriter ballWriter = new ImageWriter("ball example image",500,500,500,500);

        Render ballImageRenderer = new Render();
        ballImageRenderer.setImageWriter(ballWriter);
        ballImageRenderer.setScene(ballScene);

        ballImageRenderer.renderImage();
        ballImageRenderer.printGrid(50);
        ballImageRenderer.getImageWriter().writeToimage();
    }

}