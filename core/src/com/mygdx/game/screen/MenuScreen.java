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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.game.tween.ActorAccessor;


public class MenuScreen implements Screen {

	private Stage stage;
	private Skin skin;
	private Table table;
	private TextButton buttonPlay, buttonExit;
	private TweenManager tweenManager;
	private Sprite sprite;
	private SpriteBatch batch;
	private Table buttonSettings;

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 0.4f, 0.0f, 1);
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
		table.invalidateHierarchy();
	}

	@Override
	public void show() { 
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		batch = new SpriteBatch();
	    sprite = new Sprite(Assets.manager.get(Assets.splashScreen, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"), new TextureAtlas("data/uiskin.pack"));

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		
		// Creation des bouttons
		buttonPlay = new TextButton("PLAY", skin, "default");
		buttonPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button Play cliked!!");
				( (Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
			}

		});
		buttonPlay.pad(20);

		
		buttonSettings = new TextButton("SETTINGS", skin, "default");
		buttonSettings.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button Play cliked!!");
				( (Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());
			}

		});
		buttonSettings.pad(20);


		
		buttonExit = new TextButton("EXIT", skin, "default");
		buttonExit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button Exit cliked!!");
				Gdx.app.exit();
			}

		});
		buttonExit.pad(20);

		
		table.add(buttonPlay).spaceBottom(15).fillX().row();
		table.add(buttonSettings).spaceBottom(15).fillX().row();
		table.add(buttonExit).fillX();
		
		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());


		// heading and buttons fade-in
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonSettings, ActorAccessor.ALPHA).target(0))
		.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .80f).target(1))
		.push(Tween.to(buttonSettings, ActorAccessor.ALPHA, .80f).target(1))
		.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .80f).target(1))
		.end().start(tweenManager);

		Tween.from(table, ActorAccessor.ALPHA, .75f).target(1).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		Gdx.app.log("Menu", "Dans le menu rose"); // On affiche dans la log que l'on soit bien dans notre menu
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
