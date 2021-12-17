package com.cornichon.models.entities.aliveEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.MovingEntity;
import com.cornichon.models.entities.helpers.State;
import com.cornichon.views.components.HealthBar;

public class Player extends MovingEntity {

  public static final float SIZE_HEIGTH = 0.75f; // half a uni
  public static final float SIZE_WIDTH = 0.5f; // half a uni
  public static final float SPEED = 4f; // unit per second
  public static final float JUMP_VELOCITY = 1f;
  public static final int sphereBound = 300; // Boundries of the sphere /value will be adjusted later
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  private float health;
  private State state;
  private boolean facingLeft;
  protected Sphere sphere;

  public Player(Vector2 position) {
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
    this.setTexture(new Texture(Gdx.files.internal("images/idle.png")));
    this.type = "player";
    this.health = 100;

    this.sphere = new Sphere(new Vector2(1, 68));
  }

  public void update(float delta) {
    // this.position.add(new Vector2(velocity.x * delta, velocity.y * delta));
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

  public Sphere getSphere() {
    return sphere;
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
      this.getTexture(),
      this.getBody().getPosition().x - 0.2f,
      this.getBody().getPosition().y - 0.4f,
      this.getSizeWidth(),
      this.getSizeHeight()
    );
  }

  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  public void decreaseHealth(int amount) {
    this.health -= amount;
  }

  public void increaseHealth(int amount) {
    this.health += amount;
  }

  @Override
  public boolean isDead() {
    if (this.getHealth() <= 0) {
      this.setDead(true);
    }

    return this.isDead;
  }
}
