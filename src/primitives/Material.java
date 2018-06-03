package primitives;

/**
 * Class Material to represent the Material of a geometry.
 */
public class Material {

    // the diffuse factor.
    protected double Kd;

    // the specular factor.
    protected double Ks;

    // constant of reflection
    protected double Kr;

    // constant of transparency
    protected double Kt;

    // shininess factor.
    int nShininess;

    // ***************** Constructors ********************** //

    /**
     * default constructor.
     */
    public Material() {
        Kd = 1;
        Ks = 1;
        nShininess = 1;
        Kr = 0;
        Kt = 0;

    }

    /**
     * constructor.
     *
     * @param kd
     * @param ks
     * @param shineOnME
     */
    public Material(double kd, double ks, int shineOnME) {
        Kd = kd;
        Ks = ks;
        nShininess = shineOnME;
        Kr = 0;
        Kt = 0;
    }

    /**
     * constractor for reflection and refraction part of program
     *
     * @param kd
     * @param ks
     * @param kr
     * @param kt
     * @param shineOnME
     */
    public Material(double kd, double ks, double kr, double kt, int shineOnME) {
        Kd = kd;
        Ks = ks;
        Kr = kr;
        Kt = kt;
        nShininess = shineOnME;
    }


    // ***************** Getters/Setters ********************** //

    /**
     * getter
     *
     * @return
     */
    public double getKd() {
        return Kd;
    }

    /**
     * getter
     *
     * @return
     */
    public double getKs() {
        return Ks;
    }

    /**
     * getter
     *
     * @return
     */
    public double getKr() {
        return Kr;
    }

    /**
     * getter
     *
     * @return
     */
    public double getKt() {
        return Kt;
    }

    /**
     * getter
     *
     * @return
     */
    public int getnShininess() {
        return nShininess;
    }

    /**
     * setter.
     *
     * @param kd
     */
    public void setKd(double kd) {
        Kd = kd;
    }

    /**
     * setter.
     *
     * @param kr
     */
    public void setKr(double kr) {
        Kr = kr;
    }
    /**
     * setter.
     *
     * @param kt
     */
    public void setKt(double kt) {
        Kt = kt;
    }

    /**
     * setter
     *
     * @param nShininess
     */
    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
    }

    /**
     * setter.
     *
     * @param ks
     */
    public void setKs(double ks) {
        Ks = ks;
    }
}
