package com.cornichon.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.cornichon.models.construction.background.BackgroundBrick;
import com.cornichon.models.entities.Entity;
import com.cornichon.views.helpers.DrawableValues;

public final class LevelWriter {

  /**
   * Starts from x, y which has the value -1
   * Finds width and heigth of the rectangular room
   * Fills the inside with {@link BackgroundBrick}s
   *
   *
   * @param level {@link JsonValue} from {@link LevelReader}
   * @param entities {@link Array}
   * @param x -1's x value
   * @param y -1's y value
   */
  public static void fillBackground(int[][] level, Array<Entity> entities, int x, int y) {
    int roomWidth = 0;
    int roomHeight = 0;

    for (int _x = x; level[y][_x] != DrawableValues.BRICK; _x += 1) {
      roomWidth += 1;
    }

    for (int _y = y; level[_y][x] != DrawableValues.BRICK; _y += 1) {
      roomHeight += 1;
    }

    for (int i = 0; i < roomHeight; i += 1) {
      for (int j = 0; j < roomWidth; j += 1) {
        entities.add(new BackgroundBrick(new Vector2(x + j, level.length - y - i - 1)));
      }
    }
  }
}
