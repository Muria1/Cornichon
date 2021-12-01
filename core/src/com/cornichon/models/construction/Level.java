package com.cornichon.models.construction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.utils.LevelReader;

public class Level {

  // This class will be extented this is just for testing

  protected Array<Block> blocks;
  protected Player player;
  protected Skeleton skeleton;

  public Level() {
    this.createWorld();
  }

  /**
   * Temp 2D {@link Level}
   */
  private void createWorld() {
    this.player = new Player(new Vector2(7, 3));
    this.skeleton = new Skeleton(new Vector2(15, 3));
    this.blocks = LevelReader.readLevel("level1.json");
  }

  public Array<Block> getBlocks() {
    return blocks;
  }

  public Player getPlayer() {
    return player;
  }

  public Skeleton getSkeleton() {
    return skeleton;
  }
}
