package com.cornichon.models.construction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;

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
    // Player's position vector will be determined by the starting point in the starting rooms
    this.player = new Player(new Vector2(7, 2));
    this.skeleton = new Skeleton(new Vector2(15,2));
    this.blocks = new Array<Block>();

    for (int i = 0; i < 100; i++) {
      blocks.add(new Block(new Vector2(i, 0)));
      // blocks.add(new Block(new Vector2(i, 7)));
      blocks.add(new Block(new Vector2(i, 20)));
      if (i > 5) blocks.add(new Block(new Vector2(i, 1)));
    }

    blocks.add(new Block(new Vector2(9, 4)));
    blocks.add(new Block(new Vector2(9, 5)));
    blocks.add(new Block(new Vector2(19, 2)));
    blocks.add(new Block(new Vector2(19, 3)));
    blocks.add(new Block(new Vector2(19, 4)));
    blocks.add(new Block(new Vector2(19, 5)));
    blocks.add(new Block(new Vector2(6, 3)));
    blocks.add(new Block(new Vector2(6, 4)));
    blocks.add(new Block(new Vector2(6, 5)));
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
