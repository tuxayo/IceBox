package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.mygdx.assets.Assets;


public class Play implements Screen {

	private Stage stage;
	private Sprite sprite;
	private SpriteBatch batch;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		batch = new SpriteBatch();
		sprite = new Sprite(Assets.manager.get(Assets.plateau, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		final Skin skin = new Skin();
		
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("badlogic", new Texture("ui/button.png"));
		
		final Image sourceImage = new Image(skin, "badlogic");
		sourceImage.setBounds(50, 125, 100, 100);
		stage.addActor(sourceImage);
		
		Image validTargetImage = new Image(skin, "badlogic");
		validTargetImage.setBounds(200, 50, 100, 100);
		stage.addActor(validTargetImage);
		
		Image invalidTargetImage = new Image(skin, "badlogic");
		invalidTargetImage.setBounds(200, 200, 100, 100);
		stage.addActor(invalidTargetImage);
		
		DragAndDrop dragAndDrop = new DragAndDrop();
		
		dragAndDrop.addSource(new Source(sourceImage) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				
				Payload payload = new Payload();
				payload.setObject("Some payload!");
				payload.setDragActor(new Image(sourceImage.getDrawable()));

				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);
				
				Label invalidLabel = new Label("Some payload!", skin);
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);
				
				return payload;
			}
		});
		
		dragAndDrop.addTarget(new Target(validTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}
			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}
			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
			}
		});
		
		dragAndDrop.addTarget(new Target(invalidTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.RED);
				return false;
			}
			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}
			public void drop (Source source, Payload payload, float x, float y, int pointer) {
			}
		});
	}
	

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose () {
		stage.dispose();
		batch.dispose();
	}


	@Override
	public void hide() {
	}


	@Override
	public void pause() {
	}


	@Override
	public void resume() {
	}


}
