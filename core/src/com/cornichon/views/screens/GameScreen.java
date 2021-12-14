package com.cornichon.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.cornichon.Cornichon;
import com.cornichon.PauseRenderer;
import com.cornichon.controllers.PlayerController;
import com.cornichon.models.construction.Level;
import com.cornichon.utils.Constants;
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
    this.level = new Level(5, 0, 50);
    this.renderer = new LevelRenderer(level, true);
    this.pauseRenderer = new PauseRenderer(game.batch);

    this.playerController = new PlayerController(this.level);


    


    Gdx.input.setInputProcessor(playerController);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    if (!game.getPaused()) {
      playerController.update(delta);
      level.getWorld().step(1f / 60f, 6, 2);
      renderer.render();
    } else {
      pauseRenderer.render();

      if (Gdx.input.isKeyJustPressed(Keys.R)) {
        this.resume();
      } else if (Gdx.input.isKeyJustPressed(Keys.M)) {
        this.dispose();
      }
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
    game.setScreen(new MainMenuScreen(game));
  }
}
