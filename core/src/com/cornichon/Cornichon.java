package com.cornichon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.screens.*;

public class Cornichon extends Game {

  public SpriteBatch batch;
  public Texture img;

  @Override
  public void create() {
    batch = new SpriteBatch();
    img = new Texture("images/cornichon.png");
    setScreen(new MainMenuScreen(this));
  }
  // @Override
  // public void render() {}

  @Override
  public void dispose() {batch.dispose();}

  // @Override
  // public void resize(int width, int height) {}

  // @Override
  // public void pause() {}

  // @Override
  // public void resume() {}
}
