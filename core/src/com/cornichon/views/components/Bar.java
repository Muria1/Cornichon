package com.cornichon.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.helpers.ScreenDrawable;

public class Bar implements ScreenDrawable{

    private Texture texture;

    
      @Override
      public Texture getTexture() {
        return this.texture;
      }
    
      @Override
      public void setTexture(Texture t) {
        this.texture = t;
      }

    public void draw(SpriteBatch batch) {
    
    }
    
}
