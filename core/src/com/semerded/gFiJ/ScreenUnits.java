package com.semerded.gFiJ;


import com.semerded.td.data;

public class ScreenUnits {

    static double vw(double screenUnit) {
        return data.gameWidth / 100 * screenUnit;
    };

    static double vh(double screenUnit) {
    return data.gameHeight / 100 * screenUnit;
    };
}
