package com.cornichon.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.models.entities.aliveEntities.Player;

public class HealthBar extends Bar {

  private Texture heart;
  private Texture bar;
  private Player player;

  public HealthBar(Player player) {
    this.heart = new Texture(Gdx.files.internal("images/heart.png"));
    this.bar = new Texture(Gdx.files.internal("images/bar.png"));
    this.player = player;
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(bar, 5.1f, 4.6f, 1.5f * this.player.getHealth() / 100, 0.3f);
    batch.draw(heart, 4.8f, 4.5f, 0.5f, 0.5f);
  }
}
