package com.cornichon.models.entities.helpers;

import com.badlogic.gdx.math.Vector2;

public interface Moveable {
  float getSpeed();

  void setSpeed(float speed);

  float getJumpVelocity();

  void setJumpVelocity(float jumpVelocity);

  Vector2 getAcceleration();

  void setAcceleration(Vector2 acceleration);

  Vector2 getVelocity();

  void setVelocity(Vector2 velocity);
}
