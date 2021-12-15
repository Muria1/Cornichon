package com.cornichon.models.entities.aliveEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.cornichon.models.entities.MovingEntity;
import com.cornichon.models.entities.helpers.State;
import com.cornichon.views.textures.Textures;

public class Sphere extends MovingEntity {

    public static final float SIZE_HEIGTH = 0.3f; // half a uni
    public static final float SIZE_WIDTH = 0.3f; // half a uni
    public static final float SPEED = 5f; // unit per second
    public static final float JUMP_VELOCITY = 1f;
    public static final Rectangle BOUNDS = new Rectangle()
            .setWidth(SIZE_HEIGTH)
            .setHeight(SIZE_WIDTH);

    private State state;
    private boolean facingLeft;
    private int progress;

    public Sphere(Vector2 position) {
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

        this.state = State.IDLE;
        this.facingLeft = false;
        this.setTexture(Textures.PICKLE_SPHERE);
        b2bBodyDef.type = BodyType.KinematicBody;
    }

    public void update(float delta) {
        this.position = b2bBody.getPosition();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    // public void decreaseProgress(int amount) {
    //     this.progress -= amount;
    //   }
    
    //   public void increaseProgress(int amount) {
    //     this.progress += amount;
    
    //   }

}
