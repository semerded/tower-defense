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


		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			Enemy.create(new Enemy(new TestEnemy()));
		}

		batch.begin();

		batch.draw(map.texture, 0, 0, (float)data.gameHeight, (float)data.gameHeight);

		renderEnemies();

		font.draw(batch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, 100);
		batch.end();
	}

	// render blocks
	private void renderEnemies() {
		Enemy.killAllOnKillList();
		for (Enemy enemy: data.enemyList.values()) {
			enemy.move();
			enemy.sprite.draw(batch);
		}
	}
	

}
