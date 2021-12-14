
package com.cornichon.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.helpers.State;
import com.cornichon.utils.CornichonListener;
import com.cornichon.views.LevelRenderer;
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
  private CornichonListener listener;

  static Map<Actions, Boolean> keys = new HashMap<PlayerController.Actions, Boolean>();

  static {
    keys.put(Actions.LEFT, false);
    keys.put(Actions.RIGHT, false);
    keys.put(Actions.JUMP, false);
    keys.put(Actions.SPELL, false);
    keys.put(Actions.ATTACK, false);
  }

  public PlayerController(Level level) {
    this.player = level.getPlayer();
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

    if ((keys.get(Actions.LEFT) && keys.get(Actions.RIGHT))
        || (!keys.get(Actions.LEFT) && !(keys.get(Actions.RIGHT)))) {
      player.setState(State.IDLE);

      // horizontal speed is 0
      player.getBody().setLinearVelocity(new Vector2(0, player.getBody().getLinearVelocity().y));
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.A)
      keys.put(Actions.LEFT, true);
    if (keycode == Keys.D)
      keys.put(Actions.RIGHT, true);

    if (keycode == Keys.Z)
      keys.put(Actions.SPELL, true);
    if (keycode == Keys.X)
      keys.put(Actions.ATTACK, true);
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.A)
      keys.put(Actions.LEFT, false);
    if (keycode == Keys.D)
      keys.put(Actions.RIGHT, false);
    if (keycode == Keys.Z)
      keys.put(Actions.SPELL, false);
    if (keycode == Keys.X)
      keys.put(Actions.ATTACK, false);

    return true;
  }
}
