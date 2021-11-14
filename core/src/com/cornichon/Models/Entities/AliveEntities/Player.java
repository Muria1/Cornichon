package com.cornichon.Models.Entities.AliveEntities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.Models.Entities.MovingEntity;

public class Player extends MovingEntity {

  public static final float SIZE = 0.5f; // half a uni
  public static final float SPEED = 2f; // unit per second
  public static final float JUMP_VELOCITY = 1f;
  public static final Rectangle BOUNDS = new Rectangle()
    .setWidth(SIZE)
    .setHeight(SIZE);

  // Will be extended with appropriate states
  public enum State {
    IDLE,
    WALKING,
    JUMPING,
    DYING,
  }

  private State state;
  private boolean facingLeft;

  public Player(Vector2 position) {
    super(
      position,
      SIZE,
      BOUNDS,
      SPEED,
      JUMP_VELOCITY,
      new Vector2(), // Acceleration
      new Vector2() // Velocity
    );
    this.state = State.IDLE;
    this.facingLeft = false;
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
}
