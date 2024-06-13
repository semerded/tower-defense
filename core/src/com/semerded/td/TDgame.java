package com.semerded.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.semerded.td.enemy.Enemy;
import com.semerded.td.enemy.types.TestEnemy;
import com.semerded.td.map.Map;

public class TDgame extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private Map map;
	private OrthographicCamera camera;

	@Override
	public void create () {
		data.gameWidth = (short) Gdx.graphics.getWidth();
		data.gameHeight = (short) Gdx.graphics.getHeight();

		camera = new OrthographicCamera(data.gameWidth, data.gameHeight);
		camera.setToOrtho(true, data.gameWidth, data.gameHeight);


		map = Map.loadMap("maps/test.json");
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.BLUE);

		Enemy.create(new Enemy(new TestEnemy()));
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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		//batch.setProjectionMatrix(camera.combined);


		batch.begin();

		batch.draw(map.texture, 0, 0, (float)data.gameHeight, (float)data.gameHeight);
		font.draw(batch, "Hello World!", 0, 100);

		renderEnemies();

		batch.end();
	}

	// render blocks
	private void renderEnemies() {
		for (Enemy e: data.enemyList.values()) {
			e.move();
			batch.draw(e.texture, 50, 0);
		}

	}
	

}
