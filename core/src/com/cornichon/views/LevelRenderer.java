package com.cornichon.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.views.helpers.ScreenDrawable;

public class LevelRenderer {

  private static final float CAMERA_WIDTH = 20f;
  private static final float CAMERA_HEIGHT = 14f;

  private Level level;
  private OrthographicCamera camera;

  private ShapeRenderer debugRenderer = new ShapeRenderer();

  /* TEXTURES */
  private boolean debug = false;
  private SpriteBatch spriteBatch;

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
    this.camera.position.set(
        level.getPlayer().getPosition().x,
        level.getPlayer().getPosition().y,
        0
      );
    this.camera.update();
    this.setSize(width, height);
  }

  public void render() {
    spriteBatch.begin();

    this.camera.position.set(
        level.getPlayer().getPosition().x,
        level.getPlayer().getPosition().y,
        1
      );
    this.camera.update();

    this.drawEverything();

    spriteBatch.end();

    if (debug) {
      this.drawDebug();
    }
  }

  private void drawEverything() {
    spriteBatch.setProjectionMatrix(this.camera.combined);

    for (ScreenDrawable drawable : level.getDrawables()) {
      drawable.draw(spriteBatch);
    }
  }

  private void drawDebug() {
    debugRenderer.setProjectionMatrix(camera.combined);

    debugRenderer.begin(ShapeType.Line);

    for (ScreenDrawable drawable : level.getDrawables()) {
      Entity entity = (Entity) drawable;
      Rectangle rect = (Rectangle) entity.getBounds();

      float x1 = entity.getPosition().x + rect.x;

      float y1 = entity.getPosition().y + rect.y;

      debugRenderer.setColor(new Color(1, 0, 0, 1));

      debugRenderer.rect(x1, y1, rect.height, rect.width);
    }

    debugRenderer.end();
  }
}
