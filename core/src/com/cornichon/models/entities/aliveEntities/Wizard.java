package com.cornichon.models.entities.aliveEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.cornichon.utils.Constants;

public class Wizard extends Mob {

    public static final float SIZE_HEIGTH = 1f; // half a uni
    public static final float SIZE_WIDTH = 0.6f; // half a uni
    public static final float SPEED = 4f; // unit per second
    public static final float JUMP_VELOCITY = 1f;
    public static final Rectangle BOUNDS = new Rectangle()
            .setWidth(SIZE_HEIGTH)
            .setHeight(SIZE_WIDTH);

    public BodyDef b2bBody;

    public Wizard(Vector2 position) {
        super(
                position,
                SIZE_HEIGTH,
                SIZE_WIDTH,
                BOUNDS,
                SPEED,
                JUMP_VELOCITY,
                new Vector2(), // Acceleration
                new Vector2() // Velocity
        );

        this.b2bBodyDef.type = BodyType.StaticBody;
        this.damage = Constants.WIZARD_DAMAGE;
        this.health = Constants.MOB_HEALTH_GENERAL;
        this.setTexture(new Texture(Gdx.files.internal("images/wizard.png")));

    }

    public boolean checkDeath() {
        if (this.getHealth() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                this.getTexture(),
                this.getBody().getPosition().x - 0.25f,
                this.getBody().getPosition().y - 0.30f,
                this.getSizeWidth(),
                this.getSizeHeight());
    }

}
