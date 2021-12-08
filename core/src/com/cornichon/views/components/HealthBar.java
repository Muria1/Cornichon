package com.cornichon.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cornichon.views.LevelRenderer;
import com.cornichon.views.helpers.ScreenDrawable;

public class HealthBar implements ScreenDrawable{

    protected Texture texture;
    private Texture heart;
    private Texture bar;
    private float health;
    

    public HealthBar(LevelRenderer levelRenderer){

        heart = new Texture(Gdx.files.internal("images/heart.png"));
        bar = new Texture(Gdx.files.internal("images/bar.png")); 
        this.health = 1;            
    }

    public void setHealth(float f){
        this.health = f;
    }

    public float getHealth(){
        return health;
    }

      @Override
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public void setTexture(Texture t) {
        this.texture = t;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(heart, 0, -1f, 0.5f, 0.5f);
        batch.draw(bar, 0.5f, -0.9f, 1 * getHealth() , 0.25f);
    }
    
    
}
