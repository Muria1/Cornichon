package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.cornichon.controllers.PlayerController;
import com.cornichon.models.construction.Level;
import com.cornichon.views.LevelRenderer;

public class GameScreen implements Screen {

  private Level level;
  private LevelRenderer renderer;

  /** controllers */
  private PlayerController playerController;

  @Override
  public void show() {
    level = new Level();
    playerController = new PlayerController(level.getPlayer());
    renderer = new LevelRenderer(level, true);

    // Not sure whether we can add more than one input processer
    // If not, we will create a main controller, put sub controllers inside it
    Gdx.input.setInputProcessor(playerController);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    playerController.update(delta);
    renderer.render();
  }

  @Override
  public void resize(int width, int height) {
    renderer.setSize(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {
    Gdx.input.setInputProcessor(playerController);
  }

  @Override
  public void hide() {
    Gdx.input.setInputProcessor(null);
  }

  @Override
  public void dispose() {
    Gdx.input.setInputProcessor(null);
  }
  //ERDEM
}
