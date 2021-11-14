package com.cornichon.Models.Entities;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class MovingEntity extends Entity {

  protected float speed; // unit per second
  protected float jumpVelocity;

  protected Vector2 acceleration;
  protected Vector2 velocity;

  public MovingEntity(
    Vector2 position,
    float size,
    Shape2D bounds,
    float speed,
    float jumpVelocity,
    Vector2 acceleration,
    Vector2 velocity
  ) {
    super(position, size, bounds);
    this.speed = speed;
    this.jumpVelocity = jumpVelocity;
    this.acceleration = acceleration;
    this.velocity = velocity;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public float getJumpVelocity() {
    return jumpVelocity;
  }

  public void setJumpVelocity(float jumpVelocity) {
    this.jumpVelocity = jumpVelocity;
  }

  public Vector2 getAcceleration() {
    return acceleration;
  }

  public void setAcceleration(Vector2 acceleration) {
    this.acceleration = acceleration;
  }

  public Vector2 getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector2 velocity) {
    this.velocity = velocity;
  }
}
