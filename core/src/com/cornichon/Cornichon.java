package com.cornichon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.screens.*;

public class Cornichon extends Game {

  public SpriteBatch batch;
  public Texture img;
  protected MainMenuScreen mainMenu;
  static boolean isPaused = false;
  PauseScreen pauseScreen;

  @Override
  public void create() {
    batch = new SpriteBatch();
    img = new Texture("images/cornichon.png");
    mainMenu = new MainMenuScreen(this);
    setScreen(mainMenu);
    
  }
  @Override
  public void render() {
      
      super.render();

      if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
        Cornichon.setPaused(true);
        pauseScreen = new PauseScreen(this);
        setScreen(pauseScreen);
      }

  }

  @Override
  public void dispose() {batch.dispose();}

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

  public static void setPaused(boolean status){
    isPaused = status;
  }
}
