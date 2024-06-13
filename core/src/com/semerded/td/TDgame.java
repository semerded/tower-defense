package com.semerded.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.semerded.td.enemy.Enemy;
import com.semerded.td.enemy.types.TestEnemy;
import com.semerded.td.map.Map;

import java.util.ArrayList;
import java.util.Iterator;

public class TDgame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	private OrthographicCamera camera;
	private BitmapFont font;

	@Override
	public void create () {
		data.gameWidth = (short) Gdx.graphics.getWidth();
		data.gameHeight = (short) Gdx.graphics.getHeight();

		camera = new OrthographicCamera(data.gameWidth, data.gameHeight);
		camera.setToOrtho(true, data.gameWidth, data.gameHeight);

		Gdx.input.setInputProcessor(new Stage());
		map = Map.loadMap("maps/test.json");
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.GREEN);


	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		map.dispose();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		ScreenUtils.clear(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		eventHandler();

		batch.begin();

		batch.draw(map.texture, 0, 0, data.gameHeight, data.gameHeight); // !ignore

		renderEnemies();

		font.draw(batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, 100);
		font.draw(batch,  data.enemyList.size() + " | " + data.killList.size(), 0, 80);
		batch.end();
	}

	// render blocks
	private void renderEnemies() {
		for (Iterator<Enemy> iterator = data.enemyList.iterator(); iterator.hasNext();) {
			Enemy enemy = iterator.next();
			switch (enemy.state) {
				case alive -> {
					enemy.move();
					enemy.sprite.draw(batch);
				}
				case frozen -> {
					// todo
				}
				case entered_base -> {
					data.baseHealth -= enemy.health;
					enemy.texture.dispose();
					iterator.remove();
				}
				case killed -> {
					data.money += enemy.moneyWhenKilled;
					enemy.texture.dispose();
					iterator.remove();


				}
			}
		}
	}



	private void eventHandler() {
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			Enemy.create(new Enemy(new TestEnemy()));
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
			System.exit(0);

		}

		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			data.enemyList = new ArrayList<>();
		}
	}
	

}
