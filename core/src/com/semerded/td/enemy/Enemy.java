package com.semerded.td.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.semerded.td.data;
import com.semerded.td.enemy.types.EnemyState;
import com.semerded.td.enemy.types.EnemyType;
import com.semerded.td.map.Grid;


public class Enemy {
    public int health;
    public int moneyWhenKilled;
    public EnemyState state = EnemyState.alive;
    float speed;
    int waypointIndex = 0;
    public Sprite sprite;
    public Texture texture;
    Vector2 velocity = new Vector2();
    public float x = data.spawnBlock.get(0) * 10;
    public float y = data.spawnBlock.get(1) * 10;

    public Enemy(EnemyType enemyType) {
        this.health = enemyType.health;
        this.moneyWhenKilled = enemyType.moneyWhenKilled;
        this.speed = enemyType.speed;
        this.texture = new Texture(enemyType.img);
        this.sprite = new Sprite(this.texture);
    }

    public void move() {
            float angle = (float) Math.atan2(getWaypoint().y - this.y, getWaypoint().x - this.x);
            this.velocity.set((float) Math.cos(angle) * this.speed, (float) Math.sin(angle) * this.speed);

            double dist = Math.sqrt(Math.pow(this.x - getWaypoint().x, 2) + Math.pow(this.y - getWaypoint().y, 2));
            if (dist < 1)
                this.waypointIndex++;

            if (this.waypointIndex >= data.waypoints.size()) {
                this.state = EnemyState.entered_base;
                return;
            }
            this.sprite.setPosition(Grid.pw(this.x += this.velocity.x), Grid.ph(this.y += this.velocity.y));

 // TODO add timer for being frozen
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    private void killEnemy() {
        this.state = EnemyState.killed;
        data.money += this.moneyWhenKilled;
    }

    private Vector2 getWaypoint() {
        return new Vector2(
                data.waypoints.get(this.waypointIndex).get(0) * 10,
                data.waypoints.get(this.waypointIndex).get(1) * 10
        );
    }

    public static void create(Enemy enemy) {
        data.enemyList.add(enemy);
    }
}
