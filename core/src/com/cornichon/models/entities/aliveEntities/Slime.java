package com.cornichon.models.entities.aliveEntities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.cornichon.models.entities.projectiles.Projectile;
import com.cornichon.utils.Constants;

import java.util.Random;

public class Slime extends Mob {

  public static final float SIZE_HEIGTH = 0.40f; // half a uni
  public static final float SIZE_WIDTH = 0.5f; // half a uni
  public static final float SPEED = 4f; // unit per second
  public static final float JUMP_VELOCITY = 1f;
  public static final Rectangle BOUNDS = new Rectangle().setWidth(SIZE_HEIGTH).setHeight(SIZE_WIDTH);
  public static final int HEALTH = 100;
  public static final int DAMAGE = 10;

  public BodyDef b2bBody;

  private Projectile projectile;
  private Random ran;
  private int slimeDecisionMaker;

  public Slime(Vector2 position) {
    super(
        position,
        SIZE_HEIGTH,
        SIZE_WIDTH,
        BOUNDS,
        SPEED,
        JUMP_VELOCITY,
        new Vector2(), // Acceleration
        new Vector2());

    this.damage = Constants.SLIME_DAMAGE;

    ran = new Random();
    int slimeDecisionMaker = ran.nextInt(3) + 1;

    if (slimeDecisionMaker == 1) {
      this.setTexture(new Texture(Gdx.files.internal("images/blueSlime.png")));
    } else if (slimeDecisionMaker == 2) {
      this.setTexture(new Texture(Gdx.files.internal("images/greenSlime.png")));
    } else {
      this.setTexture(new Texture(Gdx.files.internal("images/yellowSlime.png")));
    }
  }

  public boolean checkDeath() {
    if (this.getHealth() <= 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(
        this.getTexture(),
        this.getBody().getPosition().x - 0.25f,
        this.getBody().getPosition().y - 0.30f,
        this.getSizeWidth(),
        this.getSizeHeight());
  }
}
