package com.semerded.td.map;

import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import com.semerded.td.data;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Map {
    public String img;
    public byte size;
    public ArrayList<ArrayList<Integer>> waypoints;
    public List<List<Integer>> towerBlocks;
    public ArrayList<Integer> spawnBlock;
    public List<Integer> baseBlock;
    public Texture texture;



    public static Map loadMap(String mapPath) {
        Gson gson = new Gson();
        try {
            Map map = gson.fromJson(new FileReader(mapPath), Map.class);
            map.texture = new Texture(map.img);
            data.gridDimension = map.size;
            data.waypoints = map.waypoints;
            data.spawnBlock = map.spawnBlock;
            return map;
        } catch (Exception e) {
            System.out.println("map file not found");
            e.printStackTrace();
        }
        return new Map();
    }

    public void dispose() {
        texture.dispose();
    }
}

