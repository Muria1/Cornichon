package com.cornichon.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.cornichon.models.construction.Level;

public class CornichonListener implements ContactListener {

    World world;
    private static final String PLAYER_IDENTIFIER = "player";
    private static final String GROUND_IDENTIFIER = "block";
    private int groundContacts;

    public CornichonListener(Level level) {
        world = level.getWorld();
    }

    @Override
    public void beginContact(Contact contact) {
        if (PLAYER_IDENTIFIER.equals(contact.getFixtureA().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getUserData()) &&
                        (GROUND_IDENTIFIER.equals(contact.getFixtureA().getUserData()) ||
                                GROUND_IDENTIFIER.equals(contact.getFixtureB().getUserData()))) {
            groundContacts++;
            System.out.println("groundContacts" + groundContacts);
        }
    }

    @Override
    public void endContact(Contact contact) {
        if (PLAYER_IDENTIFIER.equals(contact.getFixtureA().getUserData()) ||
                PLAYER_IDENTIFIER.equals(contact.getFixtureB().getUserData()) &&
                        (GROUND_IDENTIFIER.equals(contact.getFixtureA().getUserData()) ||
                                GROUND_IDENTIFIER.equals(contact.getFixtureB().getUserData()))) {
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
