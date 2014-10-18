package com.mygdx.game.screen;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.game.IceBox;


public class SettingsScreen implements Screen {

	private Skin skin;
	private Stage stage;
	private Table table;
	private SpriteBatch batch;
	private Sprite sprite;

	/** @return retourne le dossier ou seront placer les sauvegarde */
	public static FileHandle levelDirectory() {
		String prefsDir = Gdx.app.getPreferences(IceBox.TITLE).getString("leveldirectory").trim();
		if(prefsDir != null && !prefsDir.equals(""))
			return Gdx.files.absolute(prefsDir);
		else
			return Gdx.files.absolute(Gdx.files.external(IceBox.TITLE + "/levels").path()); // return default level directory
	}

	/** @return si vSync est activé */
	public static boolean vSync() {
		return Gdx.app.getPreferences(IceBox.TITLE).getBoolean("vsync");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	

		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
	    sprite = new Sprite(Assets.manager.get(Assets.splashScreen, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sprite.setAlpha(0.7f);
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"), new TextureAtlas("data/uiskin.pack"));

		table = new Table(skin);
		table.setFillParent(true);

		final CheckBox vSyncCheckBox = new CheckBox("vSync", skin);
		vSyncCheckBox.setChecked(vSync());

		final TextField levelDirectoryInput = new TextField(levelDirectory().path(), skin); // creating a new TextField with the current level directory already written in it
		levelDirectoryInput.setMessageText("level directory"); // set the text to be shown when nothing is in the TextField

		final TextButton back = new TextButton("BACK", skin);
		back.pad(10);

		ClickListener buttonHandler = new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// event.getListenerActor() returns the source of the event, e.g. a button that was clicked
				if(event.getListenerActor() == vSyncCheckBox) {
					// save vSync
					Gdx.app.getPreferences(IceBox.TITLE).putBoolean("vsync", vSyncCheckBox.isChecked());

					// set vSync
					Gdx.graphics.setVSync(vSync());

					Gdx.app.log(IceBox.TITLE, "vSync " + (vSync() ? "enabled" : "disabled"));
				} else if(event.getListenerActor() == back) {
					// save level directory
					String actualLevelDirectory = levelDirectoryInput.getText().trim().equals("") ? Gdx.files.getExternalStoragePath() + IceBox.TITLE + "/levels" : levelDirectoryInput.getText().trim(); // shortened form of an if-statement: [boolean] ? [if true] : [else] // String#trim() removes spaces on both sides of the string
					Gdx.app.getPreferences(IceBox.TITLE).putString("leveldirectory", actualLevelDirectory);

					// save the settings to preferences file (Preferences#flush() writes the preferences in memory to the file)
					Gdx.app.getPreferences(IceBox.TITLE).flush();

					Gdx.app.log(IceBox.TITLE, "settings saved");

					stage.addAction(sequence(moveTo(0, stage.getHeight(), .5f), run(new Runnable() {

						@Override
						public void run() {
							((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
						}
					})));
				}
			}
		};

		vSyncCheckBox.addListener(buttonHandler);

		back.addListener(buttonHandler);

		// putting everything in the table
		table.add(new Label("SETTINGS", skin)).spaceBottom(50).colspan(3).expandX().row();
		table.add();
		table.add("level directory");
		table.add().row();
		table.add(vSyncCheckBox).top().expandY();
		table.add(levelDirectoryInput).top().fillX();
		table.add(back).bottom().right();

		stage.addActor(table);

		stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f))); // coming in from top animation
		
		// On affiche dans la log que l'on soit bien dans la fenetre pricipale du jeu
		Gdx.app.log("Jeu", "Dans le jeu"); 
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
