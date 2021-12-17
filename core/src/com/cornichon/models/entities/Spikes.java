package com.cornichon.models.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.helpers.Collectible;
import com.cornichon.utils.Constants;
import com.cornichon.views.textures.Textures;

public class Spikes extends Entity {

  public static final float SIZE_HEIGTH = 0.75f; // half a uni
  public static final float SIZE_WIDTH = 0.65f; // half a uni
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);

  public Spikes(Vector2 position) {
    super(position, SIZE_HEIGTH, SIZE_WIDTH, BOUNDS);
    this.setTexture(Textures.SPIKES);
    this.type = "spike";
    this.b2bBodyDef.type = BodyType.StaticBody;
    b2bBodyDef.position.set(position.x, position.y - 0.3f);

  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
        this.getTexture(),
        this.getBody().getPosition().x - 0.3f,
        this.getBody().getPosition().y - 0.3f,
        this.getSizeWidth(),
        this.getSizeHeight());
  }

}
