package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cornichon.Cornichon;
import com.cornichon.utils.Database;

public class LeaderboardScreen implements Screen {

  Cornichon game;
  OrthographicCamera camera;
  public Stage stage;
  public Database database;
  Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));
  int score;

  TextField scoreTextField = new TextField("", skin);

  public LeaderboardScreen(Cornichon game, int score) {
    this.game = game;
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, 800, 400);
    this.score = score;
    Database.initDatabase();
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();

    game.font.draw(game.batch, "SCORE: " + score, 350, 300);
    game.font.draw(game.batch, "Enter your name to see your place in the leaderboard:", 220, 200);
    game.font.draw(game.batch, "You will see your name if you are in top 10 (Nobody remembers losers)", 180, 240);

    if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
      String playerName = scoreTextField.getText();

      Database.insert(playerName, score);
      game.setScreen(new LeaderboardListScreen(game, score));
    }

    game.font.draw(game.batch, "Please press SPACE to skip", 300, 20);

    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
      game.setScreen(new LeaderboardListScreen(game));
    }

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
    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);

    Table table = new Table();
    this.stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);

    table.setDebug(false);
    stage.addActor(table);

    scoreTextField.setMessageText("Your Name");
    scoreTextField.setPosition(550, 180);
    stage.addActor(scoreTextField);
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
