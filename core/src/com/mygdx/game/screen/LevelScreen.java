package com.mygdx.game.screen;

/**
 * Represente l'ecran de selection de niveau
 *
 */
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.Assets;
import com.mygdx.coreLogic.paramGame.paramGame;

public class LevelScreen implements Screen {

	private Stage stage;
	private Table table;
	private Skin skin;
	private SpriteBatch batch;
	private Sprite sprite;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		table.invalidateHierarchy();
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

		final List<String> list = new List<String>(skin);
		
		java.util.List<String> authorizedLevelList = new LinkedList<String>();
		
		for (int i = 1; i <= paramGame.getInstance().getJoueur().getNivEnCourt(); i++) {
			authorizedLevelList.add("Niveau " + i);
		}

		String[] authorizedLevelTab = new String[authorizedLevelList.size()];
        authorizedLevelList.toArray(authorizedLevelTab);
		list.setItems(authorizedLevelTab);

		ScrollPane scrollPane = new ScrollPane(list, skin);
		list.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(Integer.parseInt(list.getSelected().replaceAll("[^0-9]", "")));
				paramGame.getInstance().getJoueur().setNivEnCourt(
						Integer.parseInt(list.getSelected().replaceAll("[^0-9]", "")));
			}
		});
		
		ImageButton play = new ImageButton(skin, "play");
		play.setSize(50, 50);
		play.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
			}

		});
		play.pad(15);

		ImageButton back = new ImageButton(skin, "back");
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
		back.pad(10);
		back.setSize(50, 50);
		
		table.add(new Label("SELECT LEVEL", skin)).colspan(3).expandX().spaceBottom(50);
		table.add(back).uniformX().top().right().row();
		table.add(scrollPane).uniformX().expandY().top().left();
		table.add(play).uniformX();

		stage.addActor(table);

		stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f)));
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
