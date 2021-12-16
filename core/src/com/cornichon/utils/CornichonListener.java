package com.cornichon.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.collectibles.HealthPotion;
import com.cornichon.models.entities.helpers.Collectible;

public class CornichonListener implements ContactListener {

    World world;
    Level level;
    private static final String PLAYER_IDENTIFIER = "player";
    private static final String GROUND_IDENTIFIER = "block";
    private static final String MOB_IDENTIFIER = "mob";
    private static final String COL_IDENTIFIER = "col";
    private static final String SPHERE_IDENTIFIER = "top";

    private int groundContacts;

    public CornichonListener(Level level) {
        world = level.getWorld();
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {

        String userData1 = (String) contact.getFixtureA().getBody().getUserData();
        String userData2 = (String) contact.getFixtureB().getBody().getUserData();

        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (GROUND_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        GROUND_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {
            groundContacts++;
            System.out.println("groundContacts" + groundContacts);
        }

        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (MOB_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        MOB_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {

            level.getPlayer().setHealth(level.getPlayer().getHealth() - 5);
            System.out.println("MOB");
        }

        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (COL_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        COL_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {
            System.out.println("COLL");


            if (contact.getFixtureA().getUserData() instanceof Collectible) {
                Collectible e = (Collectible)contact.getFixtureA().getUserData();
                e.applyEffect(level.getPlayer(), level);
                level.addDyingEntity((Entity)e);
            }

            else if (contact.getFixtureB().getUserData() instanceof Collectible) {
                Collectible e = (Collectible)contact.getFixtureB().getUserData();
                e.applyEffect(level.getPlayer(), level);
                level.addDyingEntity((Entity)e);

            }
        }

    }

    @Override
    public void endContact(Contact contact) {
        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (GROUND_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        GROUND_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {
            groundContacts--;
            System.out.println("groundContacts" + groundContacts);

        }

        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (MOB_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        MOB_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {

            System.out.println("NO MORE MOB");
        }

        if ((PLAYER_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData())) &&
                (COL_IDENTIFIER.equals(contact.getFixtureA().getBody().getUserData()) ||
                        COL_IDENTIFIER.equals(contact.getFixtureB().getBody().getUserData()))) {
            System.out.println("POTT");
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
