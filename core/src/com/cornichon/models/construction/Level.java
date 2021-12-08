package com.cornichon.models.construction;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.utils.LevelReader;
import com.cornichon.views.helpers.ScreenDrawable;
import java.util.ArrayList;

public class Level {

  // This class will be extented this is just for testing

  protected Player player;
  protected Skeleton skeleton;
  protected World world;
  protected LevelReader reader;
  protected Array<Entity> entities;

  public Level() {
    this.createWorld();
  }

  private void createWorld() {
    this.entities = LevelReader.readLevel(this);

    world = new World(new Vector2(0, -10f), true);
    player.setBody(world.createBody(player.getBodyDef()));

    PolygonShape shape = new PolygonShape();

    shape.setAsBox(player.getSizeWidth(), player.getSizeHeight());

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 1f;
    fixtureDef.shape = shape;
    Fixture fix = player.getBody().createFixture(fixtureDef);

    for (Entity e : entities) {
      if (!e.equals(player)) {
        Body body = world.createBody(e.getBodyDef());
        e.setBody(body);
      }
    }
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setEntities(Array<Entity> entities) {
    this.entities = entities;
  }

  public Array<Entity> getEntities() {
    return entities;
  }

  public World getWorld() {
    return world;
  }
}
