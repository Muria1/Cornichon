package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;

public class MainMenuScreen implements Screen {

  Cornichon game;
  OrthographicCamera camera;
  public Stage stage;

  public MainMenuScreen(Cornichon game) {
    this.game = game;
    game.setPaused(false);

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 400);

    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();
    game.batch.draw(game.img, 0, 0);

    game.batch.end();

    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void show() {
    Table table = new Table();
    //table.setFillParent(true);
    table.setDebug(true);
    stage.addActor(table);

    Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

    TextButton newGame = new TextButton("New Game", skin);
    TextButton leaderBoard = new TextButton("Leaderboard", skin);
    TextButton exit = new TextButton("Exit", skin);

    table.setBounds(550, 50, 200, 100);
    table.add(newGame).fillX().uniformX();
    table.row().pad(10, 0, 10, 0);
    table.row();
    table.add(exit).fillX().uniformX();
    table.row();
    table.add(leaderBoard).fillX().uniformX();

    exit.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          Gdx.app.exit();
        }
      }
    );

    newGame.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new GameSelectionScreen(game));
        }
      }
    );
    //leaderBoard.addListener(new ChangeListener() {
    //	@Override
    //	public void changed(ChangeEvent event, Actor actor) {
    //		game.setScreen(new LeaderboardScreen());
    //	}
    //});

  }

  @Override
  public void hide() {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void dispose() {
    stage.dispose();
  }
}
