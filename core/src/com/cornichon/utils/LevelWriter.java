package com.cornichon.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.background.BackgroundBrick;
import com.cornichon.models.entities.Entity;
import com.cornichon.views.helpers.DrawableValues;

public final class LevelWriter {

  public Maze maze;

  public LevelWriter(int difficulty) {
    int d = difficulty + 4;
    this.maze = new Maze(d, d * 7 / 10);
  }

  public void initMap(int[][] map) {
    this.maze.solve();
    final char[][] mazeArr = maze.getGridArr();
    maze.draw();

    for (int r = 0; r < mazeArr.length; r += 1) {
      for (int c = 0; c < mazeArr[0].length; c += 1) {
        char current = mazeArr[r][c];
        if (current == 'X') {
          map[2 * c][r] = DrawableValues.BRICK;
          if (c < mazeArr[0].length - 1) {
            if (mazeArr[r][c + 1] == 'X') {
              map[2 * c + 1][r] = DrawableValues.BRICK;
            }
          }
        }
      }
    }
  }

  public void placePlayer(int[][] map) {
    map[2][1] = DrawableValues.PLAYER;
  }

  public void placeMobs(int[][] map) {
    map[2][2] = DrawableValues.SKELETON;
  }

  public void fillBackground(int[][] map, Array<Entity> entities) {
    final char[][] mazeArr = maze.getGridArr();

    for (int r = 0; r < mazeArr[0].length * 2 - 1; r += 1) {
      for (int c = 0; c < mazeArr.length; c += 1) {
        if (map[r][c] != 1) {
          entities.add(new BackgroundBrick(new Vector2(c, map.length - r - 1)));
        }
      }
    }
  }
}
