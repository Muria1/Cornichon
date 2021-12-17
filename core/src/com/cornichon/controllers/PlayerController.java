package com.cornichon.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.cornichon.models.entities.helpers.State;
import com.cornichon.models.entities.projectiles.Projectile;
import com.cornichon.utils.CornichonListener;
import com.cornichon.views.LevelRenderer;
import com.cornichon.views.textures.Textures;
import java.util.HashMap;
import java.util.Map;

public class PlayerController extends GeneralController {

  enum Actions {
    LEFT,
    RIGHT,
    JUMP,
    SPELL,
    ATTACK,
    /** MORE */
  }

  enum SphereActions {
    SLEFT,
    SRIGHT,
    SDOWN,
    SUP,
  }

  private Player player;
  private Sphere sphere;
  private Level level;
  private CornichonListener listener;

  static Map<Actions, Boolean> keys = new HashMap<PlayerController.Actions, Boolean>();
  static Map<SphereActions, Boolean> sKeys = new HashMap<PlayerController.SphereActions, Boolean>();

  static {
    keys.put(Actions.LEFT, false);
    keys.put(Actions.RIGHT, false);
    keys.put(Actions.JUMP, false);
    keys.put(Actions.SPELL, false);
    keys.put(Actions.ATTACK, false);
  }

  static {
    sKeys.put(SphereActions.SLEFT, false);
    sKeys.put(SphereActions.SRIGHT, false);
    sKeys.put(SphereActions.SUP, false);
    sKeys.put(SphereActions.SDOWN, false);
  }

  public PlayerController(Level level) {
    this.player = level.getPlayer();
    this.sphere = player.getSphere();
    this.level = level;
    listener = level.getListener();
  }

  /** The main update method **/

  public void update(float delta) {
    this.processInput();
    this.player.update(delta);
  }

  /** Change playerplayer's state and parameters based on input controls **/

  private void processInput() {
    if (Gdx.input.isKeyJustPressed(Keys.SPACE) && listener.getGroundContacts() > 0) {
      player.getBody().applyForceToCenter(0, 600f, true);
      player.setTexture(Textures.PLAYER_JUMPING);
      player.setState(State.JUMPING);
    }

    if (keys.get(Actions.LEFT)) {
      // left is pressed
      player.setFacingLeft(true);
      player.setState(State.WALKING);

      player.getBody().setLinearVelocity(new Vector2(-Player.SPEED, player.getBody().getLinearVelocity().y));
    }

    if (keys.get(Actions.RIGHT)) {
      // right is pressed
      player.setFacingLeft(false);
      player.setState(State.WALKING);

      player.getBody().setLinearVelocity(new Vector2(Player.SPEED, player.getBody().getLinearVelocity().y));
    }

    if (
      (keys.get(Actions.LEFT) && keys.get(Actions.RIGHT)) || (!keys.get(Actions.LEFT) && !(keys.get(Actions.RIGHT)))
    ) {
      player.setState(State.IDLE);

      // horizontal speed is 0
      player.getBody().setLinearVelocity(new Vector2(0, player.getBody().getLinearVelocity().y));
    }

    // Sphere's actions

    if (sKeys.get(SphereActions.SLEFT)) {
      sphere.setFacingLeft(true);
      sphere.setState(State.WALKING);
      sphere.rotate(10f);
      // sphere.getBody().setLinearVelocity(new Vector2(-Sphere.SPEED,
      // sphere.getBody().getLinearVelocity().y));
      if (Math.abs(sphere.getBody().getLinearVelocity().x) <= sphere.getMaxSpeed()) sphere
        .getBody()
        .applyLinearImpulse(new Vector2(-2f, 0), sphere.getBody().getPosition(), true);
    }

    if (sKeys.get(SphereActions.SRIGHT)) {
      // right is pressed
      sphere.setFacingLeft(false);
      sphere.setState(State.WALKING);
      sphere.rotate(-10f);
      // sphere.getBody().setLinearVelocity(new Vector2(Sphere.SPEED,
      // sphere.getBody().getLinearVelocity().y));
      if (Math.abs(sphere.getBody().getLinearVelocity().x) <= sphere.getMaxSpeed()) sphere
        .getBody()
        .applyLinearImpulse(new Vector2(2f, 0), sphere.getBody().getPosition(), true);
    }

    if (sKeys.get(SphereActions.SUP)) {
      sphere.setFacingLeft(false);
      sphere.setState(State.JUMPING);
      // sphere.getBody().setLinearVelocity(new Vector2(sphere.getBody().getLinearVelocity().x, Sphere.SPEED));
      if (Math.abs(sphere.getBody().getLinearVelocity().x) <= sphere.getMaxSpeed()) sphere
        .getBody()
        .applyLinearImpulse(new Vector2(0, 2f), sphere.getBody().getPosition(), true);
    }

    if (sKeys.get(SphereActions.SDOWN)) {
      sphere.setFacingLeft(false);
      sphere.setState(State.FALLING);
      // sphere.getBody().setLinearVelocity(new Vector2(sphere.getBody().getLinearVelocity().x, -Sphere.SPEED));
      if (Math.abs(sphere.getBody().getLinearVelocity().x) <= sphere.getMaxSpeed()) sphere
        .getBody()
        .applyLinearImpulse(new Vector2(0, -2f), sphere.getBody().getPosition(), true);
    }

    if (
      (sKeys.get(SphereActions.SLEFT) && sKeys.get(SphereActions.SRIGHT)) ||
      (!sKeys.get(SphereActions.SLEFT) && !(sKeys.get(SphereActions.SRIGHT)))
    ) {
      sphere.setState(State.IDLE);
      sphere.getBody().setLinearVelocity(new Vector2(0, sphere.getBody().getLinearVelocity().y));
    }

    if (
      (sKeys.get(SphereActions.SUP) && sKeys.get(SphereActions.SDOWN)) ||
      ((!sKeys.get(SphereActions.SUP)) && (!sKeys.get(SphereActions.SDOWN)))
    ) {
      sphere.setState(State.IDLE);
      sphere.getBody().setLinearVelocity(new Vector2(sphere.getBody().getLinearVelocity().x, 0));
    }

    sphere
      .getBody()
      .applyForceToCenter(
        new Vector2(
          30 * (player.getBody().getPosition().x - sphere.getBody().getPosition().x),
          40 * (player.getBody().getPosition().y - sphere.getBody().getPosition().y)
        ),
        true
      );
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.A) keys.put(Actions.LEFT, true);
    if (keycode == Keys.D) keys.put(Actions.RIGHT, true);

    if (keycode == Keys.Z) keys.put(Actions.SPELL, true);
    if (keycode == Keys.X) keys.put(Actions.ATTACK, true);

    if (keycode == Keys.LEFT) sKeys.put(SphereActions.SLEFT, true);
    if (keycode == Keys.RIGHT) sKeys.put(SphereActions.SRIGHT, true);
    if (keycode == Keys.UP) sKeys.put(SphereActions.SUP, true);
    if (keycode == Keys.DOWN) sKeys.put(SphereActions.SDOWN, true);

    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.A) keys.put(Actions.LEFT, false);
    if (keycode == Keys.D) keys.put(Actions.RIGHT, false);
    if (keycode == Keys.Z) keys.put(Actions.SPELL, false);
    if (keycode == Keys.X) keys.put(Actions.ATTACK, false);

    if (keycode == Keys.LEFT) sKeys.put(SphereActions.SLEFT, false);
    if (keycode == Keys.RIGHT) sKeys.put(SphereActions.SRIGHT, false);
    if (keycode == Keys.UP) sKeys.put(SphereActions.SUP, false);
    if (keycode == Keys.DOWN) sKeys.put(SphereActions.SDOWN, false);

    return true;
  }
}
