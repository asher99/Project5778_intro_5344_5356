package primitives;

/**
 * Class Material to represent the Material of a geometry.
 */
public class Material {
    protected double Kd;
    protected double Ks;
    int nShininess;

    // ***************** Constructors ********************** //
    public Material() {
        Kd= 1;
        Ks = 1;
        nShininess = 1;
    }

    public Material(double kd,double ks ,int shineOnME){
        Kd= kd;
        Ks = ks;
        nShininess = shineOnME;
    }


    // ***************** Getters/Setters ********************** //
    public double getKd() {
        return Kd;
    }

    public double getKs() {
        return Ks;
    }

    public int getnShininess() {
        return nShininess;
    }

    public void setKd(double kd) {
        Kd = kd;
    }

    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
    }

    public void setKs(double ks) {
        Ks = ks;
    }
}
