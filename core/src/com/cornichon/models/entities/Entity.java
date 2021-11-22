package com.cornichon.models.entities;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

  protected Vector2 position;
  protected float sizeHeight;
  protected float sizeWidth;
  protected Shape2D bounds;

  public Entity(
    Vector2 position,
    float sizeHeight,
    float sizeWidth,
    Shape2D bounds
  ) {
    this.position = position;
    this.sizeHeight = sizeHeight;
    this.sizeWidth = sizeWidth;
    this.bounds = bounds;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public Shape2D getBounds() {
    return bounds;
  }

  public void setBounds(Shape2D bounds) {
    this.bounds = bounds;
  }

  public float getSizeHeight() {
    return sizeHeight;
  }

  public void setSizeHeight(float sizeHeight) {
    this.sizeHeight = sizeHeight;
  }

  public float getSizeWidth() {
    return sizeWidth;
  }

  public void setSizeWidth(float sizeWidth) {
    this.sizeWidth = sizeWidth;
  }
}
