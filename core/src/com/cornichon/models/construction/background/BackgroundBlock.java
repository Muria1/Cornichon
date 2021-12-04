package com.cornichon.models.construction.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.Entity;

public class BackgroundBlock extends Entity {

  public static final float SIZE = 1f;
  public static final Rectangle BOUNDS = new Rectangle()
    .setWidth(SIZE)
    .setHeight(SIZE);

  public BackgroundBlock(Vector2 position) {
    super(position, SIZE, SIZE, BOUNDS);
    this.setTexture(new Texture(Gdx.files.internal("images/grass_block.png")));
  }
}
