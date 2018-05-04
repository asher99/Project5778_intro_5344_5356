package elements;

import primitives.Color;

/**
 * class that represents a scene that has only one color
 */
public class AmbientLight {

   protected Color color;
   protected double Ka;

    /*************constructors**********************/

    /**
     * default constructor.
     */
   public AmbientLight(){color = new Color(255,255,255); Ka = 1.0;}

    /**
     * construct AmbientLight with user arguments
     * @param myColor
     * @param myKa
     */
   public AmbientLight(Color myColor, double myKa){

       color = myColor;
       if (myKa > 1.0 || myKa < 0.0)
           throw new ExceptionInInitializerError("scaling is between: (0.0,1.0)");

       Ka = myKa;
   }

    /*************operations**********************/
    /**
     *
     * @return Color
     */
    public Color getIntensity()
    {
        Color temp = new Color(color);
         temp.scale(Ka);
         return temp;
    }

    /*************getters/setters**********************/
    public void setColor(Color color) {
        this.color = color;
    }

    public void setKa(double ka) {
        Ka = ka;
    }

    public Color getColor() {
        return color;
    }

    public double getKa() {
        return Ka;
    }
}
