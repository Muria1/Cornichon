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

public class GameOverScreen implements Screen {

  private Cornichon game;
  private OrthographicCamera camera;
  public Stage stage;
  private int finishingScore;

  public GameOverScreen(Cornichon game, int finishingScore) {
    this.game = game;
    this.finishingScore = finishingScore;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 400);
  }

  @Override
  public void show() {
    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
    Table table = new Table();

    table.setDebug(true);
    stage.addActor(table);

    Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

    TextButton mainMenu = new TextButton("Main Menu", skin);
    TextButton leaderboard = new TextButton("Leaderboard", skin);

    table.setBounds(550, 50, 200, 100);
    table.add(mainMenu).fillX().uniformX();
    table.row().pad(10, 0, 0, 0);
    table.add(leaderboard).fillX().uniformX();

    mainMenu.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new MainMenuScreen(game));
        }
      }
    );

    leaderboard.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new LeaderboardScreen(game, finishingScore));
        }
      }
    );
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();

    /**
     * Erdem buraya da bir şeyler yazarsın
     */
    game.font.draw(game.batch, "You suck ", 150, 250);
    game.font.draw(game.batch, "You are the freak", 70, 230);
    game.font.draw(game.batch, "Score: " + this.finishingScore, 70, 300);

    game.batch.end();
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    stage.dispose();
  }
}
