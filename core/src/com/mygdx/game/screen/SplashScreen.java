package com.mygdx.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.assets.Assets;
import com.mygdx.coreLogic.paramGame.paramGame;
import com.mygdx.game.tween.SpriteAccessor;


public class SplashScreen implements Screen {

	private SpriteBatch batch;
	private Sprite sprite;
	private TweenManager tweenManager;

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0.0f, 0.0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tweenManager.update(delta);

		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		sprite.setSize(width, height);
	}

	@Override
	public void show() {
	
		batch = new SpriteBatch();

		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		sprite = new Sprite(Assets.manager.get(Assets.splash, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
		Tween.set(sprite, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(sprite, SpriteAccessor.ALPHA, 0.8f).target(1).repeatYoyo(1, 0.8f).setCallback(new TweenCallback() {
		
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
				
			}
		}).start(tweenManager);

		tweenManager.update(Float.MIN_VALUE); // Update une fois pour évité un flash avant l'apparition de l'image

		
		/**
		 * Précharge les données pour le jeu
		 */
		paramGame.preload();
		
	}
	
	@Override
	public void hide() {
		 // Appeler quand l'ecran courrant change, on change donc de stage
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
