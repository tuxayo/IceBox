package com.mygdx.game.screen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.coreLogic.paramGame.Profil;
import com.mygdx.coreLogic.paramGame.paramGame;

public class SeclectProfileScreen implements Screen {

	private Stage stage;
	private Skin skin;
	private Sprite sprite;
	private SpriteBatch batch;

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
		sprite = new Sprite(Assets.manager.get(Assets.scene, Texture.class));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sprite.setAlpha(0.7f);

		skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas("ui/uiskin.pack"));

		ImageButton back = new ImageButton(skin, "back");

		Table savedPlayers = new Table(skin);
		savedPlayers.setFillParent(true);

		savedPlayers.add("SELECTION DU JOUEUR").colspan(5).center().padBottom(90).padTop(15);

		List<Profil> players = paramGame.getAllProfil();

		savedPlayers.row();
		for (int i = 0; i < 5; i++) {
			Image savedPlayer = null;
			
			if (i >= players.size()) {
				savedPlayer = new Image(skin, "perso");
				savedPlayer.setName("libre" + i);
				savedPlayer.setColor(Color.LIGHT_GRAY);
			} else {				
				savedPlayer = new Image(skin, "perso");
				savedPlayer.setName(players.get(i).getNom());
				savedPlayer.addListener(new SelectAccountListenner());
			}
			
			savedPlayers.add(savedPlayer).padLeft(7).padRight(7);
		}

		savedPlayers.row();
		for (int i = 0; i < 5; i++) {
			Label namePlayer = null;
			
			if (i >= players.size()) {
				namePlayer = new Label("libre" + i, skin);
				namePlayer.setColor(Color.LIGHT_GRAY);
			} else {				
				namePlayer = new Label(players.get(i).getNom(), skin);
			}
			
			
			
			savedPlayers.add(namePlayer).padLeft(1).padRight(1);
		}

		savedPlayers.row();
		Image newPlayer = new Image(skin, "perso");
		savedPlayers.add(newPlayer).padTop(90).colspan(5).center();

		savedPlayers.row();
		savedPlayers.add("Nouveau joueur").colspan(5).center();;
		savedPlayers.pack();

		newPlayer.addListener(new addAccountListenner());	



		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(moveTo(0, stage.getHeight(), .5f), run(new Runnable() {

					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
					}

				})));
			}
		});

		back.setPosition(Gdx.graphics.getWidth()-back.getWidth()-10, 
				Gdx.graphics.getHeight()-back.getHeight()-10);

		stage.addActor(savedPlayers);
		stage.addActor(back);
		stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f)));
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
