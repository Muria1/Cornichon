package com.cornichon.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.views.LevelRenderer;

public class ManaBar extends Bar {

  private float progress;
  private Texture mana;
  private Texture bar;
  private Player player;
  private long currentTime;
  private long startTime;

  public ManaBar(Player player) {
    this.mana = new Texture(Gdx.files.internal("images/mana.png"));
    this.bar = new Texture(Gdx.files.internal("images/manabar.png"));
    this.progress = 0;
    this.player = player;
    this.startTime = TimeUtils.nanoTime();
  }

  @Override
  public void draw(SpriteBatch batch) {
    currentTime = TimeUtils.nanoTime();

    if (currentTime - startTime > TimeUtils.millisToNanos(200)) {
      if (player.getMana() / 100 < 1) {
        startTime = currentTime;
        player.increaseMana(1f);
      }
    }

    batch.draw(bar, 5.1f, 4.1f, 1.5f * player.getMana() / 100, 0.3f);
    batch.draw(mana, 4.85f, 4.07f, 0.4f, 0.4f);
  }
}
