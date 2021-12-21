package com.cornichon.models.construction.components;

import com.badlogic.gdx.math.Vector2;
import com.cornichon.views.textures.Textures;

public class Brick extends Block {

  public Brick(Vector2 position) {
    super(position);
    this.setTexture(Textures.BRICK);
  }
}
