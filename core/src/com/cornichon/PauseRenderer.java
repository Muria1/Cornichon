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
    this.drawButtons();

    spriteBatch.end();
  }

  private void drawText() {
    BitmapFont pauseMap = new BitmapFont();
    pauseMap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    pauseMap.getData().setScale(4, 4);
    pauseMap.draw(spriteBatch, "PAUSED", 290, 290);
  }

  private void drawButtons() {
    // Add buttons here
  }
}
