package com.semerded.td.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.semerded.td.data;
import com.semerded.td.enemy.types.EnemyType;

import org.w3c.dom.Text;

import java.util.UUID;

public class Enemy {
    int health;
    int moneyWhenKilled;
    // img
    float speed;
    int waypointIndex = 0;
    public String id = String.valueOf(UUID.randomUUID().toString());
    public Texture texture;
    public int x = 0;
    public int y = 0;

    public Enemy(EnemyType enemyType) {

        this.health = enemyType.health;
        this.moneyWhenKilled = enemyType.moneyWhenKilled;
        this.speed = enemyType.speed;
        this.texture = new Texture(enemyType.img);
    }

    public void move() {

    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0)
            removeEnemy();
    }

    private void removeEnemy() {
        data.money += this.moneyWhenKilled;
        data.killList.add(this.id);
    }

    private double[] getWaypoint() {
        return new double[]{
                data.waypoints.get(this.waypointIndex).get(0) * 10,
                data.waypoints.get(this.waypointIndex).get(1) * 10
        };
    }

    public static  void create(Enemy enemy) {
        data.enemyList.put(enemy.id, enemy);
    }

}
