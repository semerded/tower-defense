package com.semerded.td.enemy.types;


public abstract class EnemyType {
    public int health;
    public int moneyWhenKilled;
    public float speed;
    public String img;

    protected EnemyType(int health, int moneyWhenKilled, float speed, String img) {
        this.health = health;
        this.moneyWhenKilled = moneyWhenKilled;
        this.speed = speed;
        this.img = img;
    }

}
