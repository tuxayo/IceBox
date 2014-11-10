package com.mygdx.game.screen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.coreLogic.paramGame.paramGame;
import com.mygdx.game.tween.ActorAccessor;
import com.mygdx.game.tween.SpriteAccessor;


public class PlayScreen implements Screen {

	private Sprite sprite;
	private SpriteBatch batch;
	private TweenManager tweenManager;

	public static Stage stage;


	@Override
	public void show() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		Tween.registerAccessor(Batch.class, new ActorAccessor());

		batch = new SpriteBatch();
		sprite = new Sprite(Assets.manager.get(Assets.plateau, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		
		
		paramGame.initGame();



		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas("ui/uiskin.pack"));
		
		Image close = new Image(skin, "quitter");
		close.setSize(50, 50);
		close.setPosition(Gdx.graphics.getWidth() - close.getWidth(), 
				Gdx.graphics.getHeight() - close.getHeight());

		close.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(parallel(fadeOut(1f))); // coming in from top animation	
				Tween.set(sprite, SpriteAccessor.ALPHA).target(1).start(tweenManager);
				Tween.to(sprite, SpriteAccessor.ALPHA, 0.8f).target(0.5f).setCallback( new TweenCallback() {

					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						Gdx.app.exit();	
					}

				}).start(tweenManager);

				tweenManager.update(Float.MIN_VALUE); // Update une fois pour évité un flash avant l'apparition de l'image
			}

		});

		Image undo = new Image(skin, "annuler");
		undo.setSize(50, 50);
		undo.setPosition(Gdx.graphics.getWidth() - (close.getWidth() + undo.getWidth() + 20), 
				Gdx.graphics.getHeight() - undo.getHeight());

		undo.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				paramGame.getController().undo();
			}

		});

		stage.addActor(close);
		stage.addActor(undo);
		stage.addActor(paramGame.getDeckActor());
		stage.addActor(paramGame.getLeftpaneActor());
		stage.addActor(paramGame.getRightpaneActor());
		
	}


	@Override
	public void resume() {
		Assets.manager.finishLoading();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		tweenManager.update(delta);

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
