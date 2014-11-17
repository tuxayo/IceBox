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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.game.IceBox;

/**
 * 
 * Represente l'ecran des options
 */
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
	    sprite = new Sprite(Assets.manager.get(Assets.scene, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sprite.setAlpha(0.7f);
		
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas("ui/uiskin.pack"));

		table = new Table(skin);
		table.setFillParent(true);

		final TextField levelDirectoryInput = new TextField(levelDirectory().path(), skin); 
		levelDirectoryInput.setMessageText("level directory"); 

		final ImageButton back = new ImageButton(skin, "back");
		back.setSize(50, 50);
		back.setPosition(Gdx.graphics.getWidth()-back.getWidth()+5, 
				Gdx.graphics.getHeight()-back.getHeight()+5);
		

		ClickListener buttonHandler = new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if(event.getListenerActor() == back) {
					
					String actualLevelDirectory = levelDirectoryInput.getText().trim().equals("") ? Gdx.files.getExternalStoragePath() + IceBox.TITLE + "/levels" : levelDirectoryInput.getText().trim(); 
					Gdx.app.getPreferences(IceBox.TITLE).putString("leveldirectory", actualLevelDirectory);


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


		back.addListener(buttonHandler);

		table.add("PARAMETRES").colspan(5).top().padBottom(190);
		table.row();
		table.add("level directory").padBottom(190).padRight(50);
		table.add(levelDirectoryInput).padBottom(190).fillX();
		table.row();
		
		stage.addActor(table);
		stage.addActor(back);
		stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f)));
		
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
