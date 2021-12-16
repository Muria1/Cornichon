package com.cornichon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cornichon.views.screens.*;

public class Cornichon extends Game {

  public SpriteBatch batch;
  public Texture img;
  public MainMenuScreen mainMenuScreen;
  protected GameScreen gameScreen;
  private boolean isPaused = false;
  public BitmapFont font;
  public static Music backgroundMusic;
  static boolean soundOn = true;
  boolean toggle = false;

  
  
  @Override
  public void create() {
    this.batch = new SpriteBatch();
    this.img = new Texture("images/cornichon.png");
    this.mainMenuScreen = new MainMenuScreen(this);
    this.gameScreen = new GameScreen(this, 1, 0, 100);
    this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("images/music/BACKGROUND_music.mp3"));
    
    
    backgroundMusic.setLooping(true);
    backgroundMusic.play();
    this.setScreen(mainMenuScreen);
    font = new BitmapFont();
  }

  @Override
  public void render() {
    super.render();
    if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
      if (!this.isPaused) {
        backgroundMusic.pause();
        gameScreen.pause();
      }
     }

    else if(!this.isPaused && soundOn){
      soundOn = true;
      backgroundMusic.play();
    }

    if(Gdx.input.isKeyJustPressed(Keys.F10)) {
      if(!toggle){
        soundOn = false;
        toggle = true;
        backgroundMusic.pause();
      }

      else{
        soundOn = true;
        toggle = false;
        backgroundMusic.play();
      }
      
    }

  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {
    backgroundMusic.pause();
    super.pause();
  }

  @Override
  public void resume() {
    backgroundMusic.play();
    super.resume();

  }

  public void setPaused(boolean paused) {
    this.isPaused = paused;
  }

  public boolean getPaused() {
    return this.isPaused;
  }

  public boolean isSoundOn(){
    return soundOn;
  }

  public static void setSound(boolean b){
    soundOn = b;
  }
}
