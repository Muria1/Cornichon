package com.cornichon.models.construction;


import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.cornichon.models.entities.projectiles.Fireball;
import com.cornichon.models.entities.projectiles.Projectile;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cornichon.Cornichon;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.construction.components.Door;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;

import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.models.entities.aliveEntities.Slime;

import com.cornichon.models.entities.aliveEntities.Sphere;

import com.cornichon.utils.CornichonListener;
import com.cornichon.utils.LevelReader;
import com.cornichon.views.maps.Map;
import com.cornichon.views.screens.GameEndingScreen;
import com.cornichon.views.screens.GameScreen;

public class Level {

  // This class will be extented this is just for testing

  private Player player;
  private Sphere sphere;
  private World world;

  private Door door;

  private Array<Entity> entities;
  private Array<Entity> dyingEntities;
  private Array<Entity> deadEntities;
  private Array<Projectile> projectiles;

  private Array<Entity> background;
  private Map map;

  private int difficulty;
  private int lastScore;
  private float lastHealth;
  private CornichonListener listener;

  private Cornichon game;

  public Level(int difficulty, int lastScore, float lastHealth, Cornichon game) {
    // this.sphere = player.getSphere();
    this.game = game;
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
    this.projectiles = new Array<Projectile>();
    listener = new CornichonListener(this);

    this.world = new World(new Vector2(0, -10f), true);
    world.setContactListener(listener);

    // creating player body and fixtures
    this.player.setBody(world.createBody(player.getBodyDef()));
    this.sphere.setBody(world.createBody(sphere.getBodyDef()));
    this.door.setBody(world.createBody(door.getBodyDef()));

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

    PolygonShape dShape = new PolygonShape();
    dShape.setAsBox(0.02f, 0.01f);
    FixtureDef dFDef = new FixtureDef();
    dFDef.shape = dShape;
    door.getBody().createFixture(dFDef).setUserData(door);
    door.getBody().setUserData("block");

    // end

    Body eBody;
    FixtureDef eFDef = new FixtureDef();

    PolygonShape eShape = new PolygonShape();

    for (Entity e : entities) {
      if (!e.equals(player) && !e.equals(door)) {
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
    this.player.setHealth(this.lastHealth);
  }

  public Sphere getSphere() {
    return this.sphere;
  }

  public void setSphere(Sphere sphere) {
    this.sphere = sphere;
  }

  public Door getDoor() {
    return this.door;
  }

  public void setDoor(Door door) {
    this.door = door;
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

    if (entities.contains(e, false)) {
      entities.removeValue(e, false);
    }
  }

  public void addDyingProjectile(Projectile p) {
    p.setDead(true);
    dyingEntities.add(p);
    deadEntities.add(p);

    if (projectiles.contains(p, false)) {
      projectiles.removeValue(p, false);
    }
  }

  public Array<Entity> getDeadEntities() {
    return deadEntities;
  }

  public Array<Entity> getDyingEntities() {
    return dyingEntities;
  }


  public Array<Projectile> getProjectiles() {
    return projectiles;
  }

  // under construction
  // Projectile velocity and direction is true
  // However, projectiles are created from first location of the mobs, not current
  // locations
  public void fire() {

    for (Entity e : entities) {

      double distance = Math.sqrt(Math.pow(e.getBody().getPosition().x - player.getBody().getPosition().x, 2)
          + Math.pow(e.getBody().getPosition().y - player.getBody().getPosition().y, 2));
      if (e instanceof Skeleton && distance <= 20) {

        Fireball fireball;

        if (player.getBody().getPosition().x <= e.getBody().getPosition().x) {
          fireball = new Fireball(20, new Vector2(e.getPosition().x - player.getPosition().x / 70,
              e.getPosition().y));
          fireball.getBodyDef().position.set(new Vector2(e.getPosition().x - player.getPosition().x / 70,
              e.getPosition().y));

        } else {
          fireball = new Fireball(20, new Vector2(e.getPosition().x + player.getPosition().x / 70,
              e.getPosition().y));
          fireball.getBodyDef().position.set(new Vector2(e.getPosition().x + player.getPosition().x / 70,
              e.getPosition().y));
        }

        FixtureDef fireballFixDef = new FixtureDef();
        PolygonShape fireballShape = new PolygonShape();

        fireballShape.setAsBox(fireball.getSizeWidth() / 2, fireball.getSizeHeight() / 2);
        fireballFixDef.shape = fireballShape;

        fireball.setBody(world.createBody(fireball.getBodyDef()));
        fireball.getBody().setUserData("projectile");
        fireball.getBody().createFixture(fireballFixDef).setUserData(fireball);

        projectiles.add(fireball);

        if ((player.getBody().getPosition().x <= e.getBody().getPosition().x)) {
          fireball.getBody()
              .setLinearVelocity(new Vector2(-5f, 0));
        }
        if ((player.getBody().getPosition().x > e.getBody().getPosition().x)) {
          fireball.getBody()
              .setLinearVelocity(new Vector2(5f, 0));
        }
      }

    }
  }

  // Under construction
  public void moveMobs() {

    for (Entity e : entities) {
      double distanceX = Math.abs(e.getBody().getPosition().x - player.getBody().getPosition().x);
      double distanceY = Math.abs(e.getBody().getPosition().y - player.getBody().getPosition().y);
      if (e instanceof Slime && distanceX <= 20 && distanceY <= 3) {
        if (player.getBody().getPosition().x <= e.getBody().getPosition().x) {
          e.getBody().setLinearVelocity(new Vector2(-2.5f, e.getBody().getLinearVelocity().y));
        } else {
          e.getBody().setLinearVelocity(new Vector2(2.5f, e.getBody().getLinearVelocity().y));

        }
      }
    }
  }


  public void nextLevel() {
    if (difficulty + 1 >= 11) {
      game.setScreen(new GameEndingScreen(game, lastScore));
    } else { // game finished
      game.setScreen(new GameScreen(game, difficulty + 1, lastScore, lastHealth));
    }
  }

}
