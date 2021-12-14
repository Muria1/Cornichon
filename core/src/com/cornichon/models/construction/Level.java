package com.cornichon.models.construction;

import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.utils.LevelReader;
import com.cornichon.views.maps.Map;

public class Level {

  // This class will be extented this is just for testing

  private Player player;
  private Sphere sphere;
  private World world;
  private Array<Entity> entities;
  private Map map;

  private int difficulty;
  private int lastScore;
  private float lastHealth;

  public Level(int difficulty, int lastScore, float lastHealth) {
    this.difficulty = difficulty;
    this.lastScore = lastScore;
    this.lastHealth = lastHealth;

    this.map = new Map(difficulty);
    this.createWorld();
  }

  private void createWorld() {
    this.map.processMap();
    this.entities = LevelReader.readLevel(this);

    this.world = new World(new Vector2(0, -10f), true);
    this.player.setBody(world.createBody(player.getBodyDef()));
    this.sphere.setBody(world.createBody(sphere.getBodyDef()));

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(player.getSizeWidth(), player.getSizeHeight());

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.density = 1f;
    fixtureDef.shape = shape;
    Fixture fix = player.getBody().createFixture(fixtureDef);
    

    for (Entity e : entities) {
      if (!(e.equals(player)) && !(e.equals(sphere))) {
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
    this.player.setHealth(lastHealth);
  }

  public Sphere getSphere() {
    return this.sphere;
  }

  public void setSphere(Sphere sphere) {
    this.sphere = sphere;
  }

  public Array<Entity> getEntities() {
    return entities;
  }

  public World getWorld() {
    return world;
  }

  public Map getMap() {
    return map;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }
}
