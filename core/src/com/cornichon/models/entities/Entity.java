package com.cornichon.models.entities;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

  protected Vector2 position;
  protected float size;
  protected Shape2D bounds;

  public Entity(Vector2 position, float size, Shape2D bounds) {
    this.position = position;
    this.size = size;
    this.bounds = bounds;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public float getSize() {
    return size;
  }

  public void setSize(float size) {
    this.size = size;
  }

  public Shape2D getBounds() {
    return bounds;
  }

  public void setBounds(Shape2D bounds) {
    this.bounds = bounds;
  }
}
