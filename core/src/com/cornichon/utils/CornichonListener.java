package com.cornichon.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Door;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Mob;
import com.cornichon.models.entities.collectibles.HealthPotion;
import com.cornichon.models.entities.helpers.Collectible;

import com.cornichon.models.entities.projectiles.Projectile;

public class CornichonListener implements ContactListener {

    World world;
    Level level;
    private static final String PLAYER_IDENTIFIER = "player";
    private static final String GROUND_IDENTIFIER = "block";
    private static final String MOB_IDENTIFIER = "mob";
    private static final String COL_IDENTIFIER = "col";
    private static final String SPHERE_IDENTIFIER = "top";
    private static final String PROJECTILE_IDENTIFIER = "projectile";

    private int groundContacts;

    public CornichonListener(Level level) {
        world = level.getWorld();
        this.level = level;


  @Override
  public void beginContact(Contact contact) {
    String userData1 = (String) contact.getFixtureA().getBody().getUserData();
    String userData2 = (String) contact.getFixtureB().getBody().getUserData();

    if (
      (
        PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      ) &&
      (
        GROUND_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        GROUND_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      )
    ) {
      groundContacts++;
      System.out.println("groundContacts" + groundContacts);
      if (contact.getFixtureA().getUserData() instanceof Door) {
        Door d = (Door) contact.getFixtureA().getUserData();
        d.setTexture(Textures.DOOR_OPENED);
        d.switchToNewLevel();
      } else if (contact.getFixtureB().getUserData() instanceof Door) {
        Door d = (Door) contact.getFixtureB().getUserData();
        d.setTexture(Textures.DOOR_OPENED);
        d.switchToNewLevel();
      }

    }

    if (
      (
        PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      ) &&
      (
        MOB_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        MOB_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      )
    ) {
      level.getPlayer().setHealth(level.getPlayer().getHealth() - 5);
      System.out.println("MOB");
    }


        if ((SPHERE_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                SPHERE_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (MOB_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        MOB_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {
            if (contact.getFixtureA().getUserData() instanceof Mob) {
                Mob m = (Mob) contact.getFixtureA().getUserData();
                m.applyDamage(level.getSphere().getDamage());
                if (m.isDead()) {
                    level.addDyingEntity(m);
                }
            } else if (contact.getFixtureB().getUserData() instanceof Mob) {
                Mob m = (Mob) contact.getFixtureB().getUserData();
                m.applyDamage(level.getSphere().getDamage());
                if (m.checkDeath()) {
                    level.addDyingEntity(m);
                }
            }

        }
      } else if (contact.getFixtureB().getUserData() instanceof Mob) {
        Mob m = (Mob) contact.getFixtureB().getUserData();
        m.applyDamage();
        if (m.checkDeath()) {
          level.addDyingEntity(m);
        }


        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (PROJECTILE_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        PROJECTILE_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {

            if (contact.getFixtureA().getUserData() instanceof Projectile) {
                Projectile e = (Projectile) contact.getFixtureA().getUserData();
                level.getPlayer().setHealth(level.getPlayer().getHealth() - e.getDamage());
                level.addDyingProjectile(e);

            }

            else if (contact.getFixtureB().getUserData() instanceof Projectile) {
                Projectile e = (Projectile) contact.getFixtureB().getUserData();
                level.getPlayer().setHealth(level.getPlayer().getHealth() - e.getDamage());
                level.addDyingProjectile(e);

            }

        }

        if ((PROJECTILE_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PROJECTILE_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (GROUND_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        GROUND_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {

            if (contact.getFixtureA().getUserData() instanceof Projectile) {
                Projectile e = (Projectile) contact.getFixtureA().getUserData();
                level.addDyingProjectile(e);
            }

            else if (contact.getFixtureB().getUserData() instanceof Projectile) {
                Projectile e = (Projectile) contact.getFixtureB().getUserData();
                level.addDyingProjectile(e);
            }

        }

        if ((PROJECTILE_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PROJECTILE_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))
                && (MOB_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        MOB_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {
            if (contact.getFixtureA().getUserData() instanceof Mob) {
                Mob m = (Mob) contact.getFixtureA().getUserData();
                m.applyDamage(25);
                if (m.isDead()) {
                    level.addDyingEntity(m);
                }
            } else if (contact.getFixtureB().getUserData() instanceof Mob) {
                Mob m = (Mob) contact.getFixtureB().getUserData();
                m.applyDamage(25);
                if (m.checkDeath()) {
                    level.addDyingEntity(m);
                }
            }
        }


    }

    if (
      (
        PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      ) &&
      (
        COL_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        COL_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      )
    ) {
      System.out.println("COLL");

      if (contact.getFixtureA().getUserData() instanceof Collectible) {
        Collectible e = (Collectible) contact.getFixtureA().getUserData();
        e.applyEffect(level.getPlayer(), level);
        level.addDyingEntity((Entity) e);
      } else if (contact.getFixtureB().getUserData() instanceof Collectible) {
        Collectible e = (Collectible) contact.getFixtureB().getUserData();
        e.applyEffect(level.getPlayer(), level);
        level.addDyingEntity((Entity) e);
      }
    }
  }

  @Override
  public void endContact(Contact contact) {
    if (
      (
        PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      ) &&
      (
        GROUND_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
        GROUND_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())
      )
    ) {
      groundContacts--;
      System.out.println("groundContacts" + groundContacts);
    }
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
    // TODO Auto-generated method stub

  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
    // TODO Auto-generated method stub

  }

  public int getGroundContacts() {
    return groundContacts;
  }
}
