package com.semerded.td;

import com.semerded.td.enemy.Enemy;
import com.semerded.td.tower.Tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class data {
    public static short gameWidth;
    public static short gameHeight;

    public static int money;
    public static byte gridDimension;
    public static HashMap<String, Enemy> enemyList = new HashMap<String, Enemy>();
    public static ArrayList<String> killList = new ArrayList<String>();
    public static ArrayList<Tower> towerList = new ArrayList<Tower>();
    public static List<List<Integer>> waypoints = new ArrayList<>(new ArrayList<>());
}
