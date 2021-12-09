package com.cornichon.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.LevelRenderer;

public class ManaBar extends Bar{

    private float progress;
    private Texture mana;
    private Texture bar;
    
    public ManaBar(LevelRenderer levelRenderer) {
        this.mana = new Texture(Gdx.files.internal("images/mana.png"));
        this.bar = new Texture(Gdx.files.internal("images/manabar.png"));
        this.progress = 1;
    }

    public void setProgress(float f) {
        this.progress = f;
      }
    
    public float getProgress() {
    return progress;
    }

    @Override
    public void draw(SpriteBatch batch) {
      batch.draw(mana, 0, -1.75f, 0.5f, 0.5f);
      batch.draw(bar, 0.5f, -1.62f, 1.2f  * getProgress(), 0.25f);
    }


}
