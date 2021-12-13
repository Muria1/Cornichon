package com.cornichon.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.aliveEntities.Sphere;
import com.cornichon.models.entities.helpers.State;
import com.cornichon.views.LevelRenderer;
import java.util.HashMap;
import java.util.Map;

public class SphereController extends GeneralController {

  enum Actions {
    LEFT,
    RIGHT,
    JUMP
  }

  private Sphere sphere;

  static Map<Actions, Boolean> keys = new HashMap<SphereController.Actions, Boolean>();

  static {
    keys.put(Actions.LEFT, false);
    keys.put(Actions.RIGHT, false);
    keys.put(Actions.JUMP, false);
  }

  public SphereController(Sphere sphere) {
    this.sphere = sphere;
  }

  /** The main update method **/

  public void update(float delta) {
    this.processInput();
    this.sphere.update(delta);
  }

  /** Change sphere's state and parameters based on input controls **/

  private void processInput() {

    if (keys.get(Actions.LEFT)) {
      sphere.setFacingLeft(true);
      sphere.setState(State.WALKING);
      sphere.getBody().setLinearVelocity(new Vector2(-Sphere.SPEED, sphere.getBody().getLinearVelocity().y));
    }

    if (keys.get(Actions.RIGHT)) {
      // right is pressed
      sphere.setFacingLeft(false);
      sphere.setState(State.WALKING);
      sphere.getBody().setLinearVelocity(new Vector2(Sphere.SPEED, sphere.getBody().getLinearVelocity().y));
    }

    if (!keys.get(Actions.JUMP)) {
      sphere.setJumpVelocity(0);

      if (sphere.getBody().getPosition().y <= 0) {
        sphere.getBody().setLinearVelocity(new Vector2(sphere.getBody().getLinearVelocity().x, 0));
        sphere.getBody().setGravityScale(0);
      } else {
        sphere
          .getBody()
          .setLinearVelocity(
            new Vector2(sphere.getBody().getLinearVelocity().x, sphere.getBody().getLinearVelocity().y)
          );
          sphere.getBody().setGravityScale(1f);
      }
    }

    
    if (
      (keys.get(Actions.LEFT) && keys.get(Actions.RIGHT)) || (!keys.get(Actions.LEFT) && !(keys.get(Actions.RIGHT)))
    ) {
      sphere.setState(State.IDLE);
      // acceleration is 0 on the x
      sphere.getAcceleration().x = 0;
      // horizontal speed is 0
      sphere.getBody().setLinearVelocity(new Vector2(0, sphere.getBody().getLinearVelocity().y));
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.LEFT) keys.put(Actions.LEFT, true);
    if (keycode == Keys.RIGHT) keys.put(Actions.RIGHT, true);
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.LEFT) keys.put(Actions.LEFT, false);
    if (keycode == Keys.RIGHT) keys.put(Actions.RIGHT, false);

    return true;
  }
}
