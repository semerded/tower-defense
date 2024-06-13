package com.semerded.td.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.semerded.td.data;
import com.semerded.td.enemy.types.EnemyType;
import com.semerded.td.map.Grid;

import java.util.UUID;

public class Enemy {
    int health;
    int moneyWhenKilled;
    // img
    float speed;
    int waypointIndex = 0;
    public String id = String.valueOf(UUID.randomUUID().toString());
    public Sprite sprite;
    public Vector2 position = new Vector2(Grid.pw(data.spawnBlock.get(0)), Grid.ph(data.spawnBlock.get(1)));
    public Vector2 velocity = new Vector2();
    public float x = data.spawnBlock.get(0) * 10;
    public float y = data.spawnBlock.get(1) * 10;

    public Enemy(EnemyType enemyType) {

        this.health = enemyType.health;
        this.moneyWhenKilled = enemyType.moneyWhenKilled;
        this.speed = enemyType.speed;
        this.sprite = new Sprite(new Texture(enemyType.img));


    }

    public void move() {
        float angle = (float) Math.atan2(getWaypoint().y - this.y, getWaypoint().x - this.x);
        this.velocity.set((float) Math.cos(angle) * this.speed, (float) Math.sin(angle) * this.speed);

        double dist = Math.sqrt(Math.pow(this.x - getWaypoint().x, 2) + Math.pow(this.y - getWaypoint().y, 2));
        if (dist < 1)
            this.waypointIndex++;

        if (this.waypointIndex >= data.waypoints.size())
            data.killList.add(this.id);

        this.sprite.setPosition(Grid.pw(this.x += this.velocity.x), Grid.ph(this.y +=   this.velocity.y));
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

    private Vector2 getWaypoint() {
        return new Vector2(
                data.waypoints.get(this.waypointIndex).get(0) * 10,
                data.waypoints.get(this.waypointIndex).get(1) * 10
        );
    }

    public static void create(Enemy enemy) {
        data.enemyList.put(enemy.id, enemy);
    }

    public  static void killAllOnKillList() {
        for (String id: data.killList) {
            data.enemyList.remove(id);
        }
    }

}
