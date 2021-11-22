package com.cornichon.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;

public class LevelRenderer {

  private static final float CAMERA_WIDTH = 20f;
  private static final float CAMERA_HEIGHT = 14f;

  private Level level;
  private OrthographicCamera camera;

  private ShapeRenderer debugRenderer = new ShapeRenderer();

  /* TEXTURES */
  private boolean debug = false;
  private SpriteBatch spriteBatch;

  private Texture grassTexture; // Testing
  private Texture playeTexture;
  private Texture skeletonTexture;

  private int width;
  private int height;
  private float ppuX; // Pixels per unit on x
  private float ppuY; // Pixels per unit on y

  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;

    this.ppuX = (float) this.width / CAMERA_WIDTH;
    this.ppuY = (float) this.height / CAMERA_HEIGHT;
  }

  public LevelRenderer(Level level, boolean debug) {
    this.level = level;
    this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
    this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
    this.camera.update();
    this.debug = debug;
    this.spriteBatch = new SpriteBatch();
    this.setSize(width, height);

    this.loadTextures();
  }

  private void loadTextures() {
    grassTexture = new Texture(Gdx.files.internal("images/grass_block.png"));
    playeTexture = new Texture(Gdx.files.internal("images/player01.png"));
    skeletonTexture = new Texture(Gdx.files.internal("images/skeleton.png"));

  }

  public void render() {
    spriteBatch.begin();

    this.drawBlocks();
    this.drawPlayer();

    //Temp method for testing
    this.drawSkeleton();

    spriteBatch.end();

    if (debug) {
      this.drawDebug();
    }
  }

  private void drawBlocks() {
    for (Block block : level.getBlocks()) {
      spriteBatch.draw(
        grassTexture,
        block.getPosition().x * ppuX,
        block.getPosition().y * ppuY,
        Block.SIZE * ppuX,
        Block.SIZE * ppuY
      );
    }
  }

  private void drawPlayer() {
    Player player = level.getPlayer();
    spriteBatch.draw(
      playeTexture,
      player.getPosition().x * ppuX,
      player.getPosition().y * ppuY,
      Player.SIZE_WIDTH * ppuX,
      Player.SIZE_HEIGTH * ppuY
    );
  }

  //temp
  private void drawSkeleton() {
    Skeleton skeleton = level.getSkeleton();
    spriteBatch.draw(
      skeletonTexture,
      skeleton.getPosition().x * ppuX,
      skeleton.getPosition().y * ppuY,
      Player.SIZE_WIDTH * ppuX,
      Player.SIZE_HEIGTH * ppuY
    );
  }

  private void drawDebug() {
    // render blocks

    debugRenderer.setProjectionMatrix(camera.combined);

    debugRenderer.begin(ShapeType.Line);

    for (Block block : level.getBlocks()) {
      Rectangle rect = (Rectangle) block.getBounds();

      float x1 = block.getPosition().x + rect.x;

      float y1 = block.getPosition().y + rect.y;

      debugRenderer.setColor(new Color(1, 0, 0, 1));

      debugRenderer.rect(x1, y1, rect.width, rect.height);
    }

    // render player

    Player player = level.getPlayer();

    Rectangle rect = (Rectangle) player.getBounds();

    float x1 = player.getPosition().x + rect.x;

    float y1 = player.getPosition().y + rect.y;

    debugRenderer.setColor(new Color(0, 1, 0, 1));

    debugRenderer.rect(x1, y1, rect.height, rect.width);

    Skeleton skeleton = level.getSkeleton();

    Rectangle rectS = (Rectangle) skeleton.getBounds();

    float xS = skeleton.getPosition().x + rectS.x;
    float yS = skeleton.getPosition().y + rectS.y;

    debugRenderer.setColor(new Color(0, 1, 0, 1));

    debugRenderer.rect(xS, yS, rectS.height, rectS.width);


    debugRenderer.end();
  }
}
