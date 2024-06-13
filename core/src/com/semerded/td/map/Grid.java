package com.semerded.td.map;

import com.semerded.td.data;

public class Grid {;



    public static float getGridBlockDimension() {
        return (float)data.gameHeight / data.gridDimension;
    }

    public static float[] getGridBlockPosition(int x, int y) {
        return new float[]
            {
            (float)(data.gameHeight / data.gridDimension) * x,
            (float)(data.gameHeight / data.gridDimension) * y
            };
    }

}
