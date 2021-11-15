package com.cornichon.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cornichon.models.construction.World;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.entities.aliveEntities.Player;

public class WorldRenderer {

  private World world;
  private OrthographicCamera camera;

  private ShapeRenderer debugRenderer = new ShapeRenderer();

  public WorldRenderer(World world) {
    this.world = world;
    this.camera = new OrthographicCamera(10, 7);
    this.camera.position.set(5, 3.5f, 0);
    this.camera.update();
  }

  public void render() {
    // Render blocks
    debugRenderer.setProjectionMatrix(camera.combined);
    debugRenderer.begin(ShapeType.Filled);

    for (Block block : world.getBlocks()) {
      Rectangle rect = (Rectangle) block.getBounds();

      float x1 = block.getPosition().x + rect.x;

      float y1 = block.getPosition().y + rect.y;

      debugRenderer.setColor(new Color(1, 0, 0, 1));

      debugRenderer.rect(x1, y1, rect.width, rect.height);
    }
    // Render player
    final Player player = world.getPlayer();
    final Rectangle rect = (Rectangle) player.getBounds();

    float x1 = player.getPosition().x + rect.x;

    float y1 = player.getPosition().y + rect.y;

    debugRenderer.setColor(new Color(0, 1, 0, 1));

    debugRenderer.rect(x1, y1, rect.width, rect.height);

    debugRenderer.end();
  }
}
