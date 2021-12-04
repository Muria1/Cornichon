package com.cornichon.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Brick;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.views.helpers.DrawableValues;
import com.cornichon.views.helpers.ScreenDrawable;

public class LevelReader {

  public static Array<ScreenDrawable> readLevel(String levelFile, Level level) {
    final Array<ScreenDrawable> drawables = new Array<ScreenDrawable>();

    try {
      FileHandle file = Gdx.files.internal("levels/" + levelFile);
      JsonReader json = new JsonReader();

      JsonValue arr = json.parse(file);

      for (int y = 0; y < arr.size; y += 1) {
        for (int x = 0; x < arr.get(0).size; x += 1) {
          // arr[0] 0 => (100)
          switch (arr.get(y).get(x).asInt()) {
            case DrawableValues.BRICK:
              drawables.add(new Brick(new Vector2(x, arr.size - y - 1)));
              break;
            case DrawableValues.PLAYER:
              Player player = new Player(new Vector2(x, arr.size - y - 1));
              drawables.add(player);
              level.setPlayer(player);
              break;
            case DrawableValues.SKELETON:
              drawables.add(new Skeleton(new Vector2(x, arr.size - y - 1)));
              // Maybe add to the mob array in level to use later?
              break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return drawables;
  }
}
