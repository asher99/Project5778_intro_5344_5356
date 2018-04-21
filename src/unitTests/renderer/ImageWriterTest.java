package renderer;

import org.junit.Test;

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
}