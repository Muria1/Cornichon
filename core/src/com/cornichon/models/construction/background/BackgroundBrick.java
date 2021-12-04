package com.cornichon.models.construction.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BackgroundBrick extends BackgroundBlock {

  public BackgroundBrick(Vector2 position) {
    super(position);
    this.setTexture(
        new Texture(Gdx.files.internal("images/background_brick_block.jpg"))
      );
  }
}
