package com.cornichon.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Brick;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.views.helpers.DrawableValues;

public final class LevelReader {

  public static List<Array<Entity>> readLevel(Level level) {
    final Array<Entity> entities = new Array<Entity>();
    final LevelWriter levelWriter = new LevelWriter(level.getDifficulty());
    final Array<Entity> background = new Array<Entity>();

    try {
      int[][] map = level.getMap().getMapIntArr();

      levelWriter.fillBackground(map, background);

      for (int y = 0; y < map.length; y += 1) {
        for (int x = 0; x < map[0].length; x += 1) {
          switch (map[y][x]) {
            case DrawableValues.BRICK:
            case DrawableValues.BRICK_PLATFORM:
              entities.add(new Brick(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.PLAYER:
              Player player = new Player(new Vector2(x, map.length - y - 1));
              level.setPlayer(player);
              break;
            case DrawableValues.SKELETON:
              entities.add(new Skeleton(new Vector2(x, map.length - y - 1)));
              // Maybe add to the mob array in level to use later?
              break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<Array<Entity>> levelList = new ArrayList<Array<Entity>>();
    levelList.add(entities);
    levelList.add(background);
    return levelList;
  }

}
