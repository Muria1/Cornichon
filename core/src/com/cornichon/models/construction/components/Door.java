package com.cornichon.models.construction.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.views.textures.Textures;

public class Door extends Entity {

  public static final float SIZE_HEIGTH = 1f; // half a uni
  public static final float SIZE_WIDTH = 0.65f; // half a uni
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  private Level level;

  public Door(Vector2 position, Level level) {
    super(position, SIZE_HEIGTH, SIZE_WIDTH, BOUNDS);
    this.level = level;
    this.setTexture(Textures.DOOR_CLOSED);
    this.type = "block";
    b2bBodyDef.type = BodyType.KinematicBody;
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
      this.getTexture(),
      this.getBody().getPosition().x - 0.3f,
      this.getBody().getPosition().y - 0.45f,
      this.getSizeWidth(),
      this.getSizeHeight()
    );
  }

  public void switchToNewLevel() {
    level.nextLevel();
  }
}
