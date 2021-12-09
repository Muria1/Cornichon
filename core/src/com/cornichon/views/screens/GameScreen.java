package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.cornichon.Cornichon;
import com.cornichon.PauseRenderer;
import com.cornichon.controllers.PlayerController;
import com.cornichon.models.construction.Level;
import com.cornichon.views.LevelRenderer;

public class GameScreen implements Screen {

  private Level level;
  private LevelRenderer renderer;
  private PauseRenderer pauseRenderer;
  private Cornichon game;

  /** controllers */
  private PlayerController playerController;

  public GameScreen(Cornichon game) {
    this.game = game;
  }

  @Override
  public void show() {
    this.level = new Level(1);
    this.renderer = new LevelRenderer(level, true);
    this.pauseRenderer = new PauseRenderer(game.batch);
    this.playerController = new PlayerController(level.getPlayer(), renderer);

    Gdx.input.setInputProcessor(playerController);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // This code will be replaced with a button event listener
    // Inside pause overlay class
    if (Gdx.input.isKeyPressed(Keys.J)) {
      game.setPaused(false);
    }

    if (!game.getPaused()) {
      playerController.update(delta);
      level.getWorld().step(1f / 60f, 6, 2);
      renderer.render();
    } else {
      pauseRenderer.render();
    }
  }

  @Override
  public void resize(int width, int height) {
    renderer.setSize(width, height);
  }

  @Override
  public void pause() {
    game.setPaused(true);
  }

  @Override
  public void resume() {
    game.setPaused(false);
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
