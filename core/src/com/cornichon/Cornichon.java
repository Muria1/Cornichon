package com.cornichon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.cornichon.utils.Constants;
import com.cornichon.views.screens.*;

public class Cornichon extends Game {

  public SpriteBatch batch;
  public Texture img;
  public MainMenuScreen mainMenuScreen;
  protected GameScreen gameScreen;
  private boolean isPaused = false;
  public BitmapFont font;

  @Override
  public void create() {
    this.batch = new SpriteBatch();
    this.img = new Texture("images/cornichon.png");
    this.mainMenuScreen = new MainMenuScreen(this);
    this.gameScreen = new GameScreen(this, 1, 0, 100);
    this.setScreen(mainMenuScreen);
    font = new BitmapFont();
  }

  @Override
  public void render() {
    super.render();
    if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
      if (!this.isPaused) {
        gameScreen.pause();
      }
    }
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  // @Override
  // public void resize(int width, int height) {}

  @Override
  public void pause() {
    super.pause();
  }

  @Override
  public void resume() {
    super.resume();
  }

  public void setPaused(boolean paused) {
    this.isPaused = paused;
  }

  public boolean getPaused() {
    return this.isPaused;
  }
}
