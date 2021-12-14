package com.cornichon.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.utils.LevelReader;
import com.cornichon.utils.LevelWriter;
import com.cornichon.views.components.HealthBar;
import com.cornichon.views.components.ManaBar;
import com.cornichon.views.helpers.ScreenDrawable;

public class LevelRenderer {

  private static final float CAMERA_WIDTH = 10f;
  private static final float CAMERA_HEIGHT = 7f;

  private Level level;
  private OrthographicCamera camera;
  private ShapeRenderer debugRenderer = new ShapeRenderer();
  private Box2DDebugRenderer box2dDebugRenderer = new Box2DDebugRenderer();

  /* TEXTURES */
  private boolean debug = false;
  private SpriteBatch spriteBatch;
  private HealthBar healthBar;
  private ManaBar manaBar;

  private int width;
  private int height;

  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public LevelRenderer(Level level, boolean debug) {
    this.level = level;
    this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
    this.debug = debug;
    this.spriteBatch = new SpriteBatch();
    this.camera.position.set(level.getPlayer().getPosition().x, level.getPlayer().getPosition().y, 0);
    this.camera.update();
    this.healthBar = new HealthBar(this);
    this.manaBar = new ManaBar(this);
    this.setSize(width, height);
  }

  public void render() {
    spriteBatch.begin();

    this.camera.position.set(level.getPlayer().getPosition().x, level.getPlayer().getPosition().y, 1);
    this.camera.update();

    this.drawEverything();
    this.drawBars();

    spriteBatch.end();

    box2dDebugRenderer.render(level.getWorld(), camera.combined);
  }

  private void drawEverything() {
    spriteBatch.setProjectionMatrix(this.camera.combined);

    for (Entity entity : level.getBackground()) {
      ((ScreenDrawable) entity).draw(spriteBatch);
    }

    for (Entity entity : level.getEntities()) {
      ((ScreenDrawable) entity).draw(spriteBatch);
    }

    level.getPlayer().draw(spriteBatch);

  }

  private void drawBars() {
    spriteBatch.setProjectionMatrix(this.camera.projection);

    float health = healthBar.getHealth();
    float progress = manaBar.getProgress();

    if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
      if (health > 0.01f) {
        healthBar.setHealth((health - 0.1f));
      }
    }

    if (Gdx.input.isKeyPressed(Input.Keys.X)) {
      if (progress > 0.01f) {
        manaBar.setProgress((progress - 0.01f));
      }
    }

    if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
      if (progress < 1f) {
        manaBar.setProgress((progress + 0.01f));
      }
    }

    healthBar.draw(spriteBatch);
    manaBar.draw(spriteBatch);
  }

}
