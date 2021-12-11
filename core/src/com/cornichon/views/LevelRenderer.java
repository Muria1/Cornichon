package com.cornichon.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.utils.Constants;
import com.cornichon.views.components.HealthBar;
import com.cornichon.views.components.ManaBar;
import com.cornichon.views.helpers.ScreenDrawable;
import com.cornichon.views.textures.Textures;

public class LevelRenderer {

  private static final float CAMERA_WIDTH = 10f;
  private static final float CAMERA_HEIGHT = 7f;

  private Level level;
  private OrthographicCamera camera;
  private ShapeRenderer debugRenderer = new ShapeRenderer();

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

    this.camera.position.set(level.getPlayer().getPosition().x, level.getPlayer().getPosition().y, 2);
    this.camera.update();

    this.drawEverything();
    this.drawBars();
    this.drawHudTexts();

    spriteBatch.end();

    this.toggleDebug();
    if (debug) this.drawDebug();
  }

  private void drawEverything() {
    spriteBatch.setProjectionMatrix(this.camera.combined);

    for (Entity entity : level.getEntities()) {
      ((ScreenDrawable) entity).draw(spriteBatch);
    }
  }

  private void drawHudTexts() {
    // spriteBatch.setProjectionMatrix(this.camera.combined);
    final BitmapFont font = new BitmapFont();
    font.getData().setScale(0.14f, 0.045f);
    font.setColor(Color.WHITE);
    font.draw(spriteBatch, level.getDifficulty() + "", -4.5f, 3.03f);
    // spriteBatch.draw(Textures.BRICK, 0, 0, 1f, 1f);
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

  private void drawDebug() {
    debugRenderer.setProjectionMatrix(camera.combined);
    debugRenderer.begin(ShapeType.Line);
    debugRenderer.setColor(new Color(1, 0, 0, 1));

    for (Entity entity : level.getEntities()) {
      Rectangle rect = (Rectangle) entity.getBounds();
      float x1 = entity.getPosition().x + rect.x;
      float y1 = entity.getPosition().y + rect.y;
      debugRenderer.rect(x1, y1, rect.height, rect.width);
    }

    debugRenderer.end();
  }

  public void toggleDebug() {
    if (Gdx.input.isKeyJustPressed(Keys.NUM_0)) {
      this.debug = !this.debug;
    }
  }
}
