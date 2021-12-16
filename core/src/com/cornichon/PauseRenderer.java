package com.cornichon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class PauseRenderer {

  private SpriteBatch spriteBatch;
  public PauseRenderer(SpriteBatch batch) {

    this.spriteBatch = batch;

  }

  public void render() {
    spriteBatch.begin();

    this.drawText();
    this.drawOptions();

    spriteBatch.end();
  }

  private void drawText() {
    BitmapFont pauseMap = new BitmapFont();
    pauseMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    pauseMap.getData().setScale(4, 4);
    pauseMap.draw(spriteBatch, "PAUSED", 290, 290);
  }

  private void drawOptions() {

    BitmapFont resumeMap = new BitmapFont();

    resumeMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    resumeMap.getData().setScale(1, 1);
    resumeMap.draw(spriteBatch, "press R to resume", 345, 200);

    BitmapFont mainMenu = new BitmapFont();
    mainMenu.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    mainMenu.getData().setScale(1, 1);
    mainMenu.draw(spriteBatch, "press M to go to main menu", 315, 180);
    mainMenu.draw(spriteBatch, "press f10 to mute and unmute the game", 290, 160);
  }

}
