package com.cornichon.controllers;

import com.badlogic.gdx.Input.Keys;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Player.State;
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

  private Player player;

  static Map<Actions, Boolean> keys = new HashMap<PlayerController.Actions, Boolean>();

  static {
    keys.put(Actions.LEFT, false);
    keys.put(Actions.RIGHT, false);
    keys.put(Actions.JUMP, false);
    keys.put(Actions.SPELL, false);
    keys.put(Actions.ATTACK, false);
  }

  public PlayerController(Player player) {
    this.player = player;
  }

  /** The main update method **/

  public void update(float delta) {
    this.processInput();
    this.player.update(delta);
  }

  /** Change playerplayer's state and parameters based on input controls **/

  private void processInput() {
    if (keys.get(Actions.LEFT)) {
      // left is pressed
      player.setFacingLeft(true);
      player.setState(State.WALKING);
      player.getVelocity().x = -Player.SPEED;
    }

    if (keys.get(Actions.RIGHT)) {
      // right is pressed
      player.setFacingLeft(false);
      player.setState(State.WALKING);
      player.getVelocity().x = Player.SPEED;
    }

    /** TRASH CODE START */

    if (keys.get(Actions.JUMP)) {
      // player.b2bBody.gravityScale += 80f;
      player.setJumpVelocity(0.3f);
      player.setPosition(player.getPosition().add(0, player.getJumpVelocity()));
      // player.b2bBody.applyForceToCenter(0, 80f, true);
    }

    if (!keys.get(Actions.JUMP)) {
      player.setJumpVelocity(0);
    }

    /** TRASH CODE END */

    if (
      (keys.get(Actions.LEFT) && keys.get(Actions.RIGHT)) ||
      (!keys.get(Actions.LEFT) && !(keys.get(Actions.RIGHT)))
    ) {
      player.setState(State.IDLE);
      // acceleration is 0 on the x
      player.getAcceleration().x = 0;
      // horizontal speed is 0
      player.getVelocity().x = 0;
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.A) keys.put(Actions.LEFT, true);
    if (keycode == Keys.D) keys.put(Actions.RIGHT, true);
    if (keycode == Keys.SPACE) keys.put(Actions.JUMP, true);
    if (keycode == Keys.Z) keys.put(Actions.SPELL, true);
    if (keycode == Keys.X) keys.put(Actions.ATTACK, true);

    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.A) keys.put(Actions.LEFT, false);
    if (keycode == Keys.D) keys.put(Actions.RIGHT, false);
    if (keycode == Keys.SPACE) keys.put(Actions.JUMP, false);
    if (keycode == Keys.Z) keys.put(Actions.SPELL, false);
    if (keycode == Keys.X) keys.put(Actions.ATTACK, false);

    return true;
  }
}
