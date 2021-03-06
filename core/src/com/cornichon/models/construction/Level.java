package com.cornichon.models.construction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cornichon.Cornichon;
import com.cornichon.models.construction.components.Door;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.models.entities.aliveEntities.Slime;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.cornichon.models.entities.aliveEntities.Wizard;
import com.cornichon.models.entities.projectiles.Fireball;
import com.cornichon.models.entities.projectiles.Projectile;
import com.cornichon.utils.Constants;
import com.cornichon.utils.CornichonListener;
import com.cornichon.utils.LevelReader;
import com.cornichon.utils.Scores;
import com.cornichon.views.maps.Map;
import com.cornichon.views.screens.GameEndingScreen;
import com.cornichon.views.screens.GameScreen;
import com.cornichon.views.textures.Textures;

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
    FixtureDef fixtureDef = new FixtureDef();

    Vector2[] playerVerts = new Vector2[6];
    playerVerts[5] = new Vector2((float) -(player.getSizeWidth() / 2), 0);
    playerVerts[4] = new Vector2((float) -(0.5 * player.getSizeWidth() / 2), (player.getSizeHeight() / 2));
    playerVerts[3] = new Vector2((float) (0.5 * player.getSizeWidth() / 2), (player.getSizeHeight() / 2));
    playerVerts[2] = new Vector2((float) (player.getSizeWidth() / 2), 0);
    playerVerts[1] = new Vector2((float) (0.5 * player.getSizeWidth() / 2), -(player.getSizeHeight() / 2));
    playerVerts[0] = new Vector2((float) -(0.5 * player.getSizeWidth() / 2), -(player.getSizeHeight() / 2));
    shape.set(playerVerts);

    fixtureDef.shape = shape;
    fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
    fixtureDef.filter.maskBits = Constants.MASK_PLAYER;

    player.getBody().createFixture(fixtureDef);
    player.getBody().setUserData("player");

    PolygonShape sShape = new PolygonShape();
    Vector2[] verts = new Vector2[6];
    verts[5] = new Vector2((float) -(sphere.getSizeWidth() / 2), 0);
    verts[4] = new Vector2((float) -(0.5 * sphere.getSizeWidth() / 2), (sphere.getSizeHeight() / 2));
    verts[3] = new Vector2((float) (0.5 * sphere.getSizeWidth() / 2), (sphere.getSizeHeight() / 2));
    verts[2] = new Vector2((float) (sphere.getSizeWidth() / 2), 0);
    verts[1] = new Vector2((float) (0.5 * sphere.getSizeWidth() / 2), -(sphere.getSizeHeight() / 2));
    verts[0] = new Vector2((float) -(0.5 * sphere.getSizeWidth() / 2), -(sphere.getSizeHeight() / 2));
    sShape.set(verts);
    FixtureDef sFDef = new FixtureDef();
    sFDef.shape = sShape;
    sFDef.filter.categoryBits = Constants.CATEGORY_TOP;
    sFDef.filter.maskBits = Constants.MASK_TOP;

    sphere.getBody().createFixture(sFDef).setUserData(sphere);
    sphere.getBody().setUserData("top");

    PolygonShape dShape = new PolygonShape();
    dShape.setAsBox(0.02f, 0.01f);
    FixtureDef dFDef = new FixtureDef();
    dFDef.shape = dShape;

    dFDef.filter.categoryBits = Constants.CATEGORY_DOOR;
    dFDef.filter.maskBits = Constants.MASK_DOOR;

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

        if (e.getType().equals("block")) {
          eShape.setAsBox(e.getSizeWidth() / 2, e.getSizeHeight() / 2);
        } else if (e.getType().equals("col")) {
          eShape.setAsBox(e.getSizeWidth() / 4, e.getSizeHeight() / 2);
        } else {
          Vector2[] eVerts = new Vector2[6];
          eVerts[5] = new Vector2((float) -(e.getSizeWidth() / 2), 0);
          eVerts[4] = new Vector2((float) -(0.5 * e.getSizeWidth() / 2), (e.getSizeHeight() / 2));
          eVerts[3] = new Vector2((float) (0.5 * e.getSizeWidth() / 2), (e.getSizeHeight() / 2));
          eVerts[2] = new Vector2((float) (e.getSizeWidth() / 2), 0);
          eVerts[1] = new Vector2((float) (0.5 * e.getSizeWidth() / 2), -(e.getSizeHeight() / 2));
          eVerts[0] = new Vector2((float) -(0.5 * e.getSizeWidth() / 2), -(e.getSizeHeight() / 2));
          eShape.set(eVerts);
        }
        eFDef.shape = eShape;

        // Adjusting Fixtures

        // add every entity data to b2body and fixture
        if (e.getType().equals("block")) {
          eFDef.filter.categoryBits = Constants.CATEGORY_BLOCK;
          eFDef.filter.maskBits = Constants.MASK_BLOCK;
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("block");
        } else if (e.getType().equals("spike")) {
          eFDef.filter.categoryBits = Constants.CATEGORY_SPIKES;
          eFDef.filter.maskBits = Constants.MASK_SPIKES;
          eShape.setAsBox(e.getSizeWidth() / 2, e.getSizeHeight() / 3);
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("spike");
        } else if (e.getType().equals("mob")) {
          eFDef.filter.categoryBits = Constants.CATEGORY_MOB;
          eFDef.filter.maskBits = Constants.MASK_MOB;
          e.getBody().createFixture(eFDef).setUserData(e);
          e.getBody().setUserData("mob");
        } else if (e.getType().equals("col")) {
          eFDef.filter.categoryBits = Constants.CATEGORY_COLLECTIBLE;
          eFDef.filter.maskBits = Constants.MASK_COLLECTIBLES;
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
      double distance = Math.sqrt(
        Math.pow(e.getBody().getPosition().x - player.getBody().getPosition().x, 2) +
        Math.pow(e.getBody().getPosition().y - player.getBody().getPosition().y, 2)
      );
      if (e instanceof Wizard && distance <= 20) {
        Fireball fireball;

        if (player.getBody().getPosition().x <= e.getBody().getPosition().x) {
          fireball = new Fireball(new Vector2(e.getPosition().x - 0.5f, e.getPosition().y));
          fireball.getBodyDef().position.set(new Vector2(e.getPosition().x - 0.5f, e.getPosition().y));
          fireball.setTexture(Textures.FIREBALL);
          // entities.add(fireball);
        } else {
          fireball = new Fireball(new Vector2(e.getPosition().x + 0.5f, e.getPosition().y));
          fireball.getBodyDef().position.set(new Vector2(e.getPosition().x + 0.5f, e.getPosition().y));
          fireball.setTexture(Textures.FIREBALL);
          // entities.add(fireball);
        }

        FixtureDef fireballFixDef = new FixtureDef();
        PolygonShape fireballShape = new PolygonShape();

        fireballShape.setAsBox(fireball.getSizeWidth() / 2, fireball.getSizeHeight() / 2);
        fireballFixDef.shape = fireballShape;
        fireballFixDef.filter.categoryBits = Constants.CATEGORY_PROJECTILE;
        fireballFixDef.filter.maskBits = Constants.MASK_PROJECTILES;

        fireball.setBody(world.createBody(fireball.getBodyDef()));
        fireball.getBody().setUserData("projectile");
        fireball.getBody().createFixture(fireballFixDef).setUserData(fireball);

        projectiles.add(fireball);

        if ((player.getBody().getPosition().x <= e.getBody().getPosition().x)) {
          fireball.getBody().setLinearVelocity(new Vector2(-4f, 0));
        }
        if ((player.getBody().getPosition().x > e.getBody().getPosition().x)) {
          fireball.getBody().setLinearVelocity(new Vector2(4f, 0));
        }
      }
    }
  }

  // Under construction
  public void moveMobs() {
    for (Entity e : entities) {
      double distanceX = Math.abs(e.getBody().getPosition().x - player.getBody().getPosition().x);
      double distanceY = Math.abs(e.getBody().getPosition().y - player.getBody().getPosition().y);
      if ((e instanceof Slime || e instanceof Skeleton) && distanceX <= 20 && distanceY <= 3) {
        long currentTime = System.currentTimeMillis() / 100;
        if (player.getBody().getPosition().x <= e.getBody().getPosition().x) {
          e.getBody().setLinearVelocity(new Vector2(-2.5f, e.getBody().getLinearVelocity().y));
          if (e instanceof Skeleton) {
            if (currentTime % 2 == 0) {
              e.setTexture(Textures.SKELETON_LEFT1);
            } else {
              e.setTexture(Textures.SKELETON_LEFT2);
            }
          }
        } else {
          e.getBody().setLinearVelocity(new Vector2(2.5f, e.getBody().getLinearVelocity().y));
          if (e instanceof Skeleton) {
            if (currentTime % 2 == 0) {
              e.setTexture(Textures.SKELETON_RIGHT1);
            } else {
              e.setTexture(Textures.SKELETON_RIGHT2);
            }
          }
        }
      }
    }
  }

  public void nextLevel() {
    if (difficulty + 1 >= 11) {
      game.setScreen(new GameEndingScreen(game, lastScore));
    } else {
      this.increaseLastScore(Scores.LEVEL_PASSED);
      game.setScreen(new GameScreen(game, difficulty + 1, lastScore, lastHealth));
    }
  }

  public void increaseLastScore(int amount) {
    this.lastScore += amount;
  }

  public int getLatestScore() {
    return this.lastScore;
  }
}
