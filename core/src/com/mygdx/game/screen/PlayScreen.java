package com.mygdx.game.screen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
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

/**
 * Represente l'ecran pricipal du jeu ou le joueur joue
 *
 */
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

		
		/**
		 * Initialisation du contrôleur et des parametres du jeu
		 */
		paramGame.getInstance().initGame();



		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas("ui/uiskin.pack"));
		
		Image close = new Image(skin, "quitter_plateau");
		close.setPosition(Gdx.graphics.getWidth()-close.getWidth()-10, 
				Gdx.graphics.getHeight()-close.getHeight()-10);
		
		
		close.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(parallel(fadeOut(1f))); // apparait d'en haut
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

		
		Image undo = new Image(skin, "retour_arriere");
		undo.setPosition(Gdx.graphics.getWidth()-(undo.getWidth() + close.getWidth())-30, 
				Gdx.graphics.getHeight()-undo.getHeight()-10);

		undo.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				paramGame.getInstance().getController().undo();
			}

		});

		
		Image backToMenu = new Image(skin, "retour_niveau");
		backToMenu.setPosition(10, Gdx.graphics.getHeight()-undo.getHeight()-10);

		backToMenu.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			}

		});
		
		
		stage.addActor(close);
		stage.addActor(undo);
		stage.addActor(backToMenu);
		stage.addActor(paramGame.getInstance().getDeckActor());
		stage.addActor(paramGame.getInstance().getLeftpaneActor());
		stage.addActor(paramGame.getInstance().getRightpaneActor());

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
