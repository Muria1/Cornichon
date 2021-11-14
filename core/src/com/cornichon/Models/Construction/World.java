package com.cornichon.Models.Construction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.Models.Construction.Components.Block;
import com.cornichon.Models.Entities.AliveEntities.Player;

public class World {

  // This class will be extented this is just for testing

  protected Array<Block> blocks;
  protected Player player;

  public World() {
    this.createWorld();
  }

  /**
   * Temp 2D {@link World}
   */
  private void createWorld() {
    // Player's position vector will be determined by the starting point in the starting rooms
    this.player = new Player(new Vector2(7, 2));
    this.blocks = new Array<Block>();

    for (int i = 0; i < 10; i++) {
      blocks.add(new Block(new Vector2(i, 0)));
      blocks.add(new Block(new Vector2(i, 7)));
      if (i > 2) blocks.add(new Block(new Vector2(i, 1)));
    }

    blocks.add(new Block(new Vector2(9, 2)));
    blocks.add(new Block(new Vector2(9, 3)));
    blocks.add(new Block(new Vector2(9, 4)));
    blocks.add(new Block(new Vector2(9, 5)));
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
}
