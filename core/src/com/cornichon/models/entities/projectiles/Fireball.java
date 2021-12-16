package com.cornichon.models.entities.projectiles;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Fireball extends Projectile {

  public static final float SIZE_HEIGTH = 0.15f; // half a uni
  public static final float SIZE_WIDTH = 0.15f; // half a uni
  public static final float SPEED = 4f; // unit per second
  public static final float JUMP_VELOCITY = 0;
  public static final Rectangle BOUNDS = new Rectangle()
    .setWidth(SIZE_HEIGTH)
    .setHeight(SIZE_WIDTH);

  public Fireball(int damage, Vector2 position) {
    super(
      damage,
      position,
      SIZE_HEIGTH,
      SIZE_WIDTH,
      BOUNDS,
      SPEED,
      0,
      new Vector2(),
      new Vector2()
    );

    this.type = "projectile";
  }
}
