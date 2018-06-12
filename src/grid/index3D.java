package grid;

import jdk.jfr.Unsigned;

/**
 * represent a 3d index, for a voxel index.
 * the indexes are positive integer starts from 0 to grid size;
 */
public class index3D {

    // index fields:
    private int x;
    private int y;
    private int z;



    // constructor:
    public index3D(int myX, int myY, int myZ){
        x = myX;
        y = myY;
        z = myZ;
    }

}
