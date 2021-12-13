package com.cornichon.models.entities.collectibles;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.helpers.Collectible;
import com.cornichon.views.textures.Textures;

public class Chest extends Entity implements Collectible {

  public static final float SIZE_HEIGTH = 0.75f; // half a uni
  public static final float SIZE_WIDTH = 0.65f; // half a uni
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  private Entity entity;

  public Chest(Vector2 position, Entity entity) {
    super(position, SIZE_HEIGTH, SIZE_WIDTH, BOUNDS);
    this.entity = entity;
    this.setTexture(Textures.CHEST);
  }

  @Override
  public void collected(Player player) {}

  public Entity getEntity() {
    return entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }
}
