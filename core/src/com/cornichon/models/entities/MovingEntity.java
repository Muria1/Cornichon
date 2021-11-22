package com.cornichon.models.entities;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.helpers.Moveable;

public class MovingEntity extends Entity implements Moveable {

  protected float speed; // unit per second
  protected float jumpVelocity;

  protected Vector2 acceleration;
  protected Vector2 velocity;

  public MovingEntity(
    Vector2 position,
    float sizeHeight,
    float sizeWidth,
    Shape2D bounds,
    float speed,
    float jumpVelocity,
    Vector2 acceleration,
    Vector2 velocity
  ) {
    super(position, sizeHeight, sizeWidth, bounds);
    this.speed = speed;
    this.jumpVelocity = jumpVelocity;
    this.acceleration = acceleration;
    this.velocity = velocity;
  }

  @Override
  public float getSpeed() {
    return speed;
  }

  @Override
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  @Override
  public float getJumpVelocity() {
    return jumpVelocity;
  }

  @Override
  public void setJumpVelocity(float jumpVelocity) {
    this.jumpVelocity = jumpVelocity;
  }

  @Override
  public Vector2 getAcceleration() {
    return acceleration;
  }

  @Override
  public void setAcceleration(Vector2 acceleration) {
    this.acceleration = acceleration;
  }

  @Override
  public Vector2 getVelocity() {
    return velocity;
  }

  @Override
  public void setVelocity(Vector2 velocity) {
    this.velocity = velocity;
  }
}
