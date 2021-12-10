package com.cornichon.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.cornichon.models.construction.background.BackgroundBrick;
import com.cornichon.models.entities.Entity;
import com.cornichon.views.helpers.DrawableValues;

public final class LevelWriter {

  public static final Maze MAZE = new Maze(12);

  public static void initMap(int[][] map) {
    MAZE.solve();
    final char[][] mazeArr = MAZE.getGridArr();
    MAZE.draw();

    for (int r = 0; r < mazeArr.length; r += 1) {
      for (int c = 0; c < mazeArr[0].length; c += 1) {
        char current = mazeArr[r][c];
        if (current == 'X') {
          map[r][2 * c] = DrawableValues.BRICK;
          if (c < mazeArr[0].length - 1) {
            if (mazeArr[r][c + 1] == 'X') {
              map[r][2 * c + 1] = DrawableValues.BRICK;
            }
          }
        }
      }
    }
  }

  public static void placePlayer(int[][] map) {
    map[2][1] = DrawableValues.PLAYER;
  }

  public static void placeMobs(int[][] map) {
    map[2][2] = DrawableValues.SKELETON;
  }

  public static void fillBackground(int[][] map, Array<Entity> entities) {
    final char[][] mazeArr = MAZE.getGridArr();

    for (int r = 0; r < mazeArr.length; r += 1) {
      for (int c = 0; c < mazeArr[0].length * 2 - 1; c += 1) {
        if (map[r][c] != 1) {
          entities.add(new BackgroundBrick(new Vector2(c, map.length - r - 1)));
        }
      }
    }
  }
}
