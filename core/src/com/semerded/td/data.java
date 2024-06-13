package com.semerded.td;

import com.semerded.td.enemy.Enemy;
import com.semerded.td.tower.Tower;

import java.util.ArrayList;

public class data {
    public static float gameWidth;
    public static float gameHeight;

    public static int money;
    public static byte gridDimension;
    public static ArrayList<Enemy> enemyList = new ArrayList<>();
    public static ArrayList<String> killList = new ArrayList<>();
    public static ArrayList<Tower> towerList = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> waypoints = new ArrayList<>(new ArrayList<>());
    public static ArrayList<Integer> spawnBlock = new ArrayList<>();
    public static double baseHealth;
}
