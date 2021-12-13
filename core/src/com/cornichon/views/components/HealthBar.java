package com.cornichon.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.LevelRenderer;

public class HealthBar extends Bar {

  private Texture heart;
  private Texture bar;
  private float health;

  public HealthBar(LevelRenderer levelRenderer) {
    this.heart = new Texture(Gdx.files.internal("images/heart.png"));
    this.bar = new Texture(Gdx.files.internal("images/bar.png"));
    this.health = 1;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  public float getHealth() {
    return health;
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(bar, 5.1f, 4.6f, 1.5f * getHealth(), 0.3f);
    batch.draw(heart, 4.8f, 4.5f, 0.5f, 0.5f);
  }
}
