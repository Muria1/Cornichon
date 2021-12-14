package com.cornichon.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.cornichon.models.entities.helpers.State;
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
  enum SphereActions{
    SLEFT,
    SRIGHT,
    SDOWN,
    SUP
  }

  private Player player;
  protected Sphere sphere;
  private LevelRenderer renderer;
  private static int jumpCount = 0;

  static Map<Actions, Boolean> keys = new HashMap<PlayerController.Actions, Boolean>();
  static Map<SphereActions, Boolean> sphereKeys = new HashMap<PlayerController.SphereActions, Boolean>();

  static {
    keys.put(Actions.LEFT, false);
    keys.put(Actions.RIGHT, false);
    keys.put(Actions.JUMP, false);
    keys.put(Actions.SPELL, false);
    keys.put(Actions.ATTACK, false);
  }

  public PlayerController(Player player) {
    this.player = player;
    this.sphere = player.getSphere();
  }

  /** The main update method **/

  public void update(float delta) {
    this.processInput();
    this.player.update(delta);
  }

  /** Change playerplayer's state and parameters based on input controls **/

  private void processInput() {
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      player.getBody().applyForceToCenter(0, 800f, true);
      player.setTexture(Textures.PLAYER_JUMPING);
      player.setState(State.JUMPING);
    }
    if (keys.get(Actions.LEFT)) {
      // left is pressed
      player.setFacingLeft(true);
      player.setState(State.WALKING);
      // player.getVelocity().x = -Player.SPEED;
      // player.getBody().applyLinearImpulse(new Vector2(-0.5f, 0),
      // player.getBody().getPosition(), true);
      player.getBody().setLinearVelocity(new Vector2(-Player.SPEED, player.getBody().getLinearVelocity().y));
    }

    if (keys.get(Actions.RIGHT)) {
      // right is pressed
      player.setFacingLeft(false);
      player.setState(State.WALKING);
      // player.getVelocity().x = Player.SPEED;
      // player.getBody().applyLinearImpulse(new Vector2(0.5f, 0),
      // player.getBody().getPosition(), true);
      player.getBody().setLinearVelocity(new Vector2(Player.SPEED, player.getBody().getLinearVelocity().y));
    }

    /** TRASH CODE START */

    // if (keys.get(Actions.JUMP)) {
    //   player.setState(State.JUMPING);

    //   // player.setJumpVelocity(0.3f);
    //   // if (player.getBody().getLinearVelocity().y <= 10f) {
    //     // player.getBody().applyLinearImpulse(new Vector2(0, 1.9f),
    //     // player.getBody().getPosition(), false);

    //     player.getBody().applyForceToCenter(0, 20f, true);

    //     // player.getBody().setLinearVelocity(new Vector2(player.getBody().getLinearVelocity().x, 10f));
    //   // }

    //   // else{
    //   // player.getBody().setLinearVelocity(new
    //   // Vector2(player.getBody().getLinearVelocity().x,0));
    //   // }
    //   player.setPosition(player.getBody().getPosition());

    // }

    if (!keys.get(Actions.JUMP)) {
      player.setJumpVelocity(0);

      if (player.getBody().getPosition().y <= 0) {
        player.getBody().setLinearVelocity(new Vector2(player.getBody().getLinearVelocity().x, 0));
        player.getBody().setGravityScale(0);
      } else {
        player
          .getBody()
          .setLinearVelocity(
            new Vector2(player.getBody().getLinearVelocity().x, player.getBody().getLinearVelocity().y)
          );
        player.getBody().setGravityScale(1f);
      }
    }

    /** TRASH CODE END */

    if (
      (keys.get(Actions.LEFT) && keys.get(Actions.RIGHT)) || (!keys.get(Actions.LEFT) && !(keys.get(Actions.RIGHT)))
    ) {
      player.setState(State.IDLE);
      // acceleration is 0 on the x
      player.getAcceleration().x = 0;
      // horizontal speed is 0
      player.getBody().setLinearVelocity(new Vector2(0, player.getBody().getLinearVelocity().y));
    }

    //Sphere's actions

    if (sphereKeys.get(SphereActions.SLEFT)) {
      sphere.setFacingLeft(true);
      sphere.setState(State.WALKING);
      sphere.getBody().setLinearVelocity(new Vector2(-Sphere.SPEED, sphere.getBody().getLinearVelocity().y));
    }

    if (sphereKeys.get(SphereActions.SRIGHT)) {
      // right is pressed
      sphere.setFacingLeft(false);
      sphere.setState(State.WALKING);
      sphere.getBody().setLinearVelocity(new Vector2(Sphere.SPEED, sphere.getBody().getLinearVelocity().y));
    }

    if (sphereKeys.get(SphereActions.SUP)) {
      sphere.setFacingLeft(false);
      sphere.setState(State.JUMPING);
      sphere.getBody().setLinearVelocity(new Vector2(Sphere.SPEED, sphere.getBody().getLinearVelocity().y));
      }
    
    if (sphereKeys.get(SphereActions.SDOWN)) {
      sphere.setFacingLeft(false);
      sphere.setState(State.JUMPING);
      sphere.getBody().setLinearVelocity(new Vector2(Sphere.SPEED, sphere.getBody().getLinearVelocity().y));
      }
    }

    

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.A) keys.put(Actions.LEFT, true);
    if (keycode == Keys.D) keys.put(Actions.RIGHT, true);

    if (keycode == Keys.Z) keys.put(Actions.SPELL, true);
    if (keycode == Keys.X) keys.put(Actions.ATTACK, true);

    if (keycode == Keys.LEFT) sphereKeys.put(SphereActions.SLEFT, true);
    if (keycode == Keys.RIGHT) sphereKeys.put(SphereActions.SRIGHT, true);
    if (keycode == Keys.UP) sphereKeys.put(SphereActions.SUP, true);
    if (keycode == Keys.DOWN) sphereKeys.put(SphereActions.SDOWN, true);

    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.A) keys.put(Actions.LEFT, false);
    if (keycode == Keys.D) keys.put(Actions.RIGHT, false);
    if (keycode == Keys.Z) keys.put(Actions.SPELL, false);
    if (keycode == Keys.X) keys.put(Actions.ATTACK, false);

    if (keycode == Keys.LEFT) sphereKeys.put(SphereActions.SLEFT, false);
    if (keycode == Keys.RIGHT) sphereKeys.put(SphereActions.SRIGHT, false);
    if (keycode == Keys.UP) sphereKeys.put(SphereActions.SUP, false);
    if (keycode == Keys.DOWN) sphereKeys.put(SphereActions.SDOWN, false);


    return true;
  }
}
