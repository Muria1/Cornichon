package com.cornichon.models.construction;

import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.utils.CornichonListener;
import com.cornichon.utils.LevelReader;
import com.cornichon.views.maps.Map;

public class Level {

  // This class will be extented this is just for testing

  private Player player;
  private Sphere sphere;
  private World world;
  private Array<Entity> entities;
  private Array<Entity> dyingEntities;
  private Array<Entity> deadEntities;

  private Array<Entity> background;
  private Map map;

  private int difficulty;
  private int lastScore;
  private float lastHealth;
  private CornichonListener listener;

  public Level(int difficulty, int lastScore, float lastHealth) {
    // this.sphere = player.getSphere();
    this.difficulty = difficulty;
    this.lastScore = lastScore;
    this.lastHealth = lastHealth;
    this.map = new Map(difficulty);
    this.createWorld();
  }

  private void createWorld() {
    this.map.processMap();
    this.entities = LevelReader.readLevel(this).get(0);
    this.background = LevelReader.readLevel(this).get(1);
    this.dyingEntities = new Array<Entity>();
    this.deadEntities = new Array<Entity>();
    listener = new CornichonListener(this);

    this.world = new World(new Vector2(0, -10f), true);
    world.setContactListener(listener);

    // creating player body and fixtures
    this.player.setBody(world.createBody(player.getBodyDef()));
    this.sphere.setBody(world.createBody(sphere.getBodyDef()));

    PolygonShape shape = new PolygonShape();

    shape.setAsBox(player.getSizeWidth() / 2, player.getSizeHeight() / 2);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    player.getBody().createFixture(fixtureDef);
    player.getBody().setUserData("player");

    PolygonShape sShape = new PolygonShape();
    sShape.setAsBox(sphere.getSizeWidth() / 2, sphere.getSizeHeight() / 2);
    FixtureDef sFDef = new FixtureDef();
    sFDef.shape = sShape;
    sphere.getBody().createFixture(sFDef).setUserData(sphere);
    sphere.getBody().setUserData("top");
    // end

    Body eBody;
    FixtureDef eFDef = new FixtureDef();

    PolygonShape eShape = new PolygonShape();

    for (Entity e : entities) {
      if (!e.equals(player)) {

        eBody = world.createBody(e.getBodyDef());
        e.setBody(eBody);

        eShape.setAsBox(e.getSizeWidth() / 2, e.getSizeHeight() / 2);
        eFDef.shape = eShape;

        // Adjusting Fixtures

        // add every entity data to b2body and fixture
        if (e.getType().equals("block")) {
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("block");
        } else if (e.getType().equals("mob")) {
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("mob");
        } else if (e.getType().equals("col")) {
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("col");
        } else {
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("other");
        }

      }
    }

    for (Entity e : background) {
      eBody = world.createBody(e.getBodyDef());
      e.setBody(eBody);

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

  public Array<Entity> getBackground() {
    return background;
  }

  public CornichonListener getListener() {
    return listener;
  }

  public void setListener(CornichonListener listener) {
    this.listener = listener;
  }

  public void addDyingEntity(Entity e) {
    e.setDead(true);
    dyingEntities.add(e);
    deadEntities.add(e);
  }

  public Array<Entity> getDeadEntities() {
    return deadEntities;
  }

  public Array<Entity> getDyingEntities() {
    return dyingEntities;
  }
}
