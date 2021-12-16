package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet.FontAttribute;

public class GameEndingScreen implements Screen {

  private Cornichon game;
  private OrthographicCamera camera;
  public Stage stage;
  private int finishingScore;

  public GameEndingScreen(Cornichon game, int finishingScore) {
    this.game = game;
    this.finishingScore = finishingScore;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 400);

    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void show() {
    Table firstTable = new Table();
    Table secondTable = new Table();

    //firstTable.setFillParent(true);
    firstTable.setDebug(true);
    stage.addActor(firstTable);

    Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

    TextButton leaderboard = new TextButton("Go to leaderboard", skin);
    firstTable.setBounds(550, 50, 150, 50);
    firstTable.add(leaderboard).fillX().uniformX();

    /** Buraya da leaderboardda isim girme yeri gelsin
     *  İsim girdikten sonra normal liste gözüksün sen ayarlarsın bu kısmı
     */
    leaderboard.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          // game.setScreen(new LeaderboardScreen(game));
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

    /** Erdem buraya hikaye yazarsın */
    game.font.draw(game.batch, "You have completed the game without dying once!", 180, 300);
    game.font.draw(game.batch, "Score: " + this.finishingScore, 180, 400);
    /** Skorla beraber */
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
