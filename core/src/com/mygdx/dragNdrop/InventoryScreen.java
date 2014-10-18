package com.mygdx.dragNdrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.assets.Assets;


public class InventoryScreen implements Screen {

	private InventoryActor inventoryActor;
	private Sprite sprite;
	private SpriteBatch batch;
	
	public static Stage stage;
	
	@Override
	public void show() {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();
		sprite = new Sprite(Assets.manager.get(Assets.plateau, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"), new TextureAtlas("data/uiskin.pack"));
		DragAndDrop dragAndDrop = new DragAndDrop();
		
		inventoryActor = new InventoryActor(new Inventory(), dragAndDrop, skin);

		float X = (Gdx.graphics.getWidth() - inventoryActor.getWidth()) / 2.0f;
		inventoryActor.setPosition(X, 0);
		stage.addActor(inventoryActor);
	}

	@Override
	public void resume() {
		Assets.manager.finishLoading();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.begin();
		sprite.draw(batch);
		batch.end();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// NOOP
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
