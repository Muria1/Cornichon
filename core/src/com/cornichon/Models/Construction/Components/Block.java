package com.cornichon.Models.Construction.Components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.Models.Entities.Entity;

public class Block extends Entity {

  public static final float SIZE = 1f;
  public static final Rectangle BOUNDS = new Rectangle()
    .setWidth(SIZE)
    .setHeight(SIZE);

  public Block(Vector2 position) {
    super(position, SIZE, BOUNDS);
  }
}
