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

public class GameSelectionScreen implements Screen {

  Cornichon game;
  OrthographicCamera camera;
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

    secondTable.setDebug(true);
    stage.addActor(secondTable);

    Skin skin = new Skin(Gdx.files.internal("images/uiskin.json"));

    TextButton storyMode = new TextButton("Story Mode", skin);
    TextButton speedRun = new TextButton("Speed Run", skin);

    firstTable.setBounds(0, 0, 650, 800);
    secondTable.setBounds(650, 0, 650, 800);
    firstTable.add(storyMode).fillX().uniformX();
    secondTable.add(speedRun).fillX().uniformX();

    storyMode.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new GameScreen());
        }
      }
    );

    speedRun.addListener(
      new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
          game.setScreen(new GameScreen());
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
