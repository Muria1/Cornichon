package com.cornichon.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Brick;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.views.helpers.DrawableValues;
import com.cornichon.views.helpers.ScreenDrawable;
import com.cornichon.views.maps.Map;

public final class LevelReader {

  public static Array<ScreenDrawable> readLevel(String levelFile, Level level) {
    final Array<ScreenDrawable> drawables = new Array<ScreenDrawable>();

    try {
      // JsonValue map = LevelReader.readJsonFile(levelFile);

      Map mapObj = new Map(1);
      mapObj.processMap();

      int[][] map = mapObj.getMap();

      for (int y = 0; y < map.length; y += 1) {
        for (int x = 0; x < map[0].length; x += 1) {
          switch (map[y][x]) {
            case DrawableValues.BRICK:
            case DrawableValues.BRICK_PLATFORM:
              drawables.add(new Brick(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.PLAYER:
              Player player = new Player(new Vector2(x, map.length - y - 1));
              drawables.add(player);
              level.setPlayer(player);
              break;
            case DrawableValues.SKELETON:
              drawables.add(new Skeleton(new Vector2(x, map.length - y - 1)));
              // Maybe add to the mob array in level to use later?
              break;
            case DrawableValues.BRICK_BACKGROUND:
              LevelWriter.fillBackground(map, drawables, x, y);
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
