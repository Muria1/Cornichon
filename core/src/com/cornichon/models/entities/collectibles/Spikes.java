package com.cornichon.models.entities.collectibles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.helpers.Collectible;
import com.cornichon.views.textures.Textures;

public class Spikes extends Entity implements Collectible {

  public static final float SIZE_HEIGTH = 0.75f; // half a uni
  public static final float SIZE_WIDTH = 0.65f; // half a uni
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  public Spikes(Vector2 position) {
    super(position, SIZE_HEIGTH, SIZE_WIDTH, BOUNDS);
    this.setTexture(Textures.SPIKES);
    this.type = "block";
  }

  @Override
  public void collected(Player player, Level level) {}

  @Override
  public void applyEffect(Player player, Level level) {
    player.decreaseHealth(10);
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
      this.getTexture(),
      this.getBody().getPosition().x - 0.3f,
      this.getBody().getPosition().y - 0.6f,
      this.getSizeWidth(),
      this.getSizeHeight()
    );
  }
}
