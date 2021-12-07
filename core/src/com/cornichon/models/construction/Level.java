package com.cornichon.models.construction;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.utils.LevelReader;
import com.cornichon.views.helpers.ScreenDrawable;

public class Level {

  // This class will be extented this is just for testing

  protected Array<ScreenDrawable> drawables;
  protected Player player;
  protected Skeleton skeleton;
  protected World world;
  protected LevelReader reader;
  protected ArrayList<Entity> entities;

  public Level() {
    this.createWorld();
  }

  private void createWorld() {
    this.drawables = LevelReader.readLevel("level1.json", this);

    world = new World(new Vector2(0, -98f), true);
    player.setBody(world.createBody(player.getBodyDef()));

    
    for( Entity e: entities) {
      Body body = world.createBody(e.getBodyDef());
      e.setBody(body);
    }
    // this.drawables.addAll(LevelReader.readLevel("level1.json", this));
  }

  public Array<ScreenDrawable> getDrawables() {
    return drawables;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setEntities( ArrayList<Entity> entities) {
    this.entities = entities;
  }
}
