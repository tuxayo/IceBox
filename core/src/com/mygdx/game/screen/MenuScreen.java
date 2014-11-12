package com.mygdx.game.screen;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.game.tween.ActorAccessor;


public class MenuScreen implements Screen {

	private Stage stage;
	private Skin skin;
	private TweenManager tweenManager;
	private Sprite sprite;
	private SpriteBatch batch;

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);

		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		stage.act(delta); // met tout à jour
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() { 
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		batch = new SpriteBatch();
	    sprite = new Sprite(Assets.manager.get(Assets.menu, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sprite.setAlpha(0.85f);
		
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas("ui/uiskin.pack"));
		

		// Creation des bouttons
		Image buttonSettings  = new Image(skin, "option");
		Image buttonPlay	  = new Image(skin, "jouer");
		Image buttonExit 	  = new Image(skin, "quitter");
		Image buttonCredits   = new Image(skin, "01T");
		
		buttonSettings.setPosition(Gdx.graphics.getWidth()-buttonSettings.getWidth()-20, 20);
		buttonSettings.setSize(70, 70);
		
		buttonPlay.setPosition((Gdx.graphics.getWidth()-buttonPlay.getWidth())/2, 20);
		
		buttonCredits.setPosition(20, 20);
		buttonCredits.setSize(70, 70);
		buttonExit.setPosition(Gdx.graphics.getWidth()-buttonExit.getWidth()-20, 
				Gdx.graphics.getHeight()-buttonExit.getHeight()-20);
		
		buttonPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button Play cliked!!");
				( (Game) Gdx.app.getApplicationListener()).setScreen(new SeclectProfileScreen());
			}

		});


		buttonSettings.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button Play cliked!!");
				( (Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());
			}

		});


		buttonExit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button Exit cliked!!");
				Gdx.app.exit();
			}

		});

		
		stage.addActor(buttonPlay);
		stage.addActor(buttonSettings);
		stage.addActor(buttonExit);
		stage.addActor(buttonCredits);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());


		// heading and buttons fade-in
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonSettings, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonCredits, ActorAccessor.ALPHA).target(0))
		.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .80f).target(1))
		.push(Tween.to(buttonSettings, ActorAccessor.ALPHA, .80f).target(1))
		.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .80f).target(1))
		.push(Tween.to(buttonCredits, ActorAccessor.ALPHA, .80f).target(1))
		.end().start(tweenManager);

		Gdx.app.log("Menu", "Dans le menu"); // On affiche dans la log que l'on soit bien dans notre menu
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
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
