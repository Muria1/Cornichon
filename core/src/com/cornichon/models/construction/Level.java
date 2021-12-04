package com.cornichon.models.construction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.utils.LevelReader;
import com.cornichon.views.helpers.ScreenDrawable;

public class Level {

  // This class will be extented this is just for testing

  protected Array<ScreenDrawable> drawables;
  protected Player player;
  protected Skeleton skeleton;

  public Level() {
    this.createWorld();
  }

  private void createWorld() {
    this.drawables = LevelReader.readLevel("level1.json", this);
  }

  public Array<ScreenDrawable> getDrawables() {
    return drawables;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
