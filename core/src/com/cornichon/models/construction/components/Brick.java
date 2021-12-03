package com.cornichon.models.construction.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Block {

  public Brick(Vector2 position) {
    super(position);
    this.setTexture(new Texture(Gdx.files.internal("images/brick_block.jpg")));
  }
}
