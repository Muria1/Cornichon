package com.cornichon.models.entities.aliveEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.projectiles.Fireball;
import com.cornichon.models.entities.projectiles.Projectile;
import com.cornichon.utils.Constants;

/**
 * Example mob class
 */
public class Skeleton extends Mob {

  public static final float SIZE_HEIGTH = 1f; // half a uni
  public static final float SIZE_WIDTH = 0.6f; // half a uni
  public static final float SPEED = 4f; // unit per second
  public static final float JUMP_VELOCITY = 1f;
  public static final Rectangle BOUNDS = new Rectangle()
      .setWidth(SIZE_HEIGTH)
      .setHeight(SIZE_WIDTH);

  public BodyDef b2bBody;

  public Skeleton(Vector2 position) {
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
    // this.projectile = new Fireball(5, position);

    this.setTexture(new Texture(Gdx.files.internal("images/skeleton_idle.png")));
    this.damage = Constants.SKELETON_DAMAGE;
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
        this.getTexture(),
        this.getBody().getPosition().x - 0.35f,
        this.getBody().getPosition().y - 0.45f,
        this.getSizeWidth(),
        this.getSizeHeight());
  }
}
