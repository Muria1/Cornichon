package com.cornichon.models.entities.projectiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.utils.Constants;

public class Fireball extends Projectile {

  public static final float SIZE_HEIGTH = 0.25f; // half a uni
  public static final float SIZE_WIDTH = 0.25f; // half a uni
  public static final float SPEED = 4f; // unit per second
  public static final float JUMP_VELOCITY = 0;
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  public Fireball(Vector2 position) {
    super(position, SIZE_HEIGTH, SIZE_WIDTH, BOUNDS, SPEED, 0, new Vector2(), new Vector2());
    this.damage = Constants.FIREBALL_DAMAGE;
    this.type = "projectile";
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
      this.getTexture(),
      this.getBody().getPosition().x - 0.12f,
      this.getBody().getPosition().y - 0.12f,
      this.getSizeWidth(),
      this.getSizeHeight()
    );
  }
}
