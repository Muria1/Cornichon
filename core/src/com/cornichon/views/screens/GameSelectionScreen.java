package com.cornichon.views.screens;

import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet.FontAttribute;

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

public class GameSelectionScreen implements Screen {

  private Cornichon game;
  private OrthographicCamera camera;
  public Stage stage;

  public GameSelectionScreen(Cornichon game) {
    this.game = game;

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

    TextButton storyMode = new TextButton("Start the journey", skin);
    firstTable.setBounds(550, 50, 150, 50);
    firstTable.add(storyMode).fillX().uniformX();

    storyMode.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new GameScreen(game));
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

    game.font.draw(game.batch, "There once was a medieval village in which a boy was born: Cornichon.", 180, 300);
    game.font.draw(game.batch, "His father gave this unique name to his son because there were not any pickles in the village, believing", 90, 280);
    game.font.draw(game.batch, "his son will also be this important like pickles", 270, 260);
    game.font.draw(game.batch, "However, his son was born a freak. Being kicked out from the village, he started a new journey...", 100, 200);

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
