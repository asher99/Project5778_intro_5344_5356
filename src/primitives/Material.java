package primitives;

/**
 * Class Material to represent the Material of a geometry.
 */
public class Material {

    // the diffuse factor.
    protected double Kd;

    // the specular factor.
    protected double Ks;

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
