package unittest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CoordinateTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(primitives.Coordinate.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void getCoord() {
    }

    @org.junit.Test
    public void setCoord() {
    }

    @org.junit.Test
    public void toString() {
    }

    @org.junit.Test
    public void equals() {
    }

    @org.junit.Test
    public void add() {
    }

    @org.junit.Test
    public void substract() {
    }
}
