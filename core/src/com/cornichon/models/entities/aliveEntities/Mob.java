package com.cornichon.models.entities.aliveEntities;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.MovingEntity;
import com.cornichon.models.entities.helpers.State;
import com.cornichon.utils.Constants;

public class Mob extends MovingEntity {

  private State state;
  private boolean facingLeft;
  protected int health;
  protected int damage;

  public Mob(
      Vector2 position,
      float sizeHeight,
      float sizeWidth,
      Shape2D bounds,
      float speed,
      float jumpVelocity,
      Vector2 acceleration,
      Vector2 velocity) {
    super(
        position,
        sizeHeight,
        sizeWidth,
        bounds,
        speed,
        jumpVelocity,
        acceleration,
        velocity);

    this.state = State.IDLE;
    this.facingLeft = false;
    this.health = Constants.MOB_HEALTH_GENERAL;
    this.type = "mob";
    this.damage = 0;
    this.b2bBodyDef.gravityScale = 6f;
  }

  public void update(float delta) {
    this.position.add(new Vector2(velocity.x * delta, velocity.y * delta));
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

  public int getHealth() {
    return health;
  }

  public int getDamage() {
    return damage;
  }

  public void applyDamage(int damage) {
    health = health - damage;
  }

  public boolean checkDeath() {
    if (this.health <= 0) {
      return true;
    } else {
      return false;
    }
  }

}
