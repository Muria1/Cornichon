package com.cornichon.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.LevelRenderer;

public class ManaBar extends Bar {

  private float progress;
  private Texture mana;
  private Texture bar;

  public ManaBar(LevelRenderer levelRenderer) {
    this.mana = new Texture(Gdx.files.internal("images/mana.png"));
    this.bar = new Texture(Gdx.files.internal("images/manabar.png"));
    this.progress = 1;
  }

  public void setProgress(float progress) {
    this.progress = progress;
  }

  public float getProgress() {
    return progress;
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(bar, 2.6f, 2.2f, 1.5f * getProgress(), 0.3f);
    batch.draw(mana, 2.35f, 2.17f, 0.4f, 0.4f);
  }
}
