package com.cornichon.models.construction.background;

import com.badlogic.gdx.math.Vector2;
import com.cornichon.views.textures.Textures;

public class BackgroundBrick extends BackgroundBlock {

  public BackgroundBrick(Vector2 position) {
    super(position);
    this.setTexture(Textures.BACKGROUND_BRICK);
  }
}
