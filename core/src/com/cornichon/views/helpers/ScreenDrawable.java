package com.cornichon.views.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ScreenDrawable {
  void draw(SpriteBatch batch);

  Texture getTexture();

  void setTexture(Texture t);
}
