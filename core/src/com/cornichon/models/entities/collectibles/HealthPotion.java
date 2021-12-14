package com.cornichon.models.entities.collectibles;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.helpers.Collectible;
import com.cornichon.views.textures.Textures;

public class HealthPotion extends Entity implements Collectible {

  public static final float SIZE_HEIGTH = 0.6f; // half a uni
  public static final float SIZE_WIDTH = 0.45f; // half a uni
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  public HealthPotion(Vector2 position) {
    super(position, SIZE_HEIGTH, SIZE_WIDTH, BOUNDS);
    this.setTexture(Textures.POTIONS_HEALTH);
  }

  @Override
  public void collected(Player player, Level level) {
    player.increaseHealth(10);
    level.getEntities().removeIndex(level.getEntities().indexOf(this, true));
  }
}
