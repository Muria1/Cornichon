package com.cornichon.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Brick;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.models.entities.collectibles.Chest;
import com.cornichon.models.entities.collectibles.HealthPotion;
import com.cornichon.views.helpers.DrawableValues;

public final class LevelReader {

  public static Array<Entity> readLevel(Level level) {
    final Array<Entity> entities = new Array<Entity>();
    final LevelWriter levelWriter = new LevelWriter(level.getDifficulty());

    try {
      int[][] map = level.getMap().getMapIntArr();

      levelWriter.fillBackground(map, entities);

      for (int y = 0; y < map.length; y += 1) {
        for (int x = 0; x < map[0].length; x += 1) {
          switch (map[y][x]) {
            case DrawableValues.BRICK:
            case DrawableValues.BRICK_PLATFORM:
              entities.add(new Brick(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.PLAYER:
              Player player = new Player(new Vector2(x, map.length - y - 1));
              entities.add(player);
              level.setPlayer(player);
              break;
            case DrawableValues.SKELETON:
              entities.add(new Skeleton(new Vector2(x, map.length - y - 1)));
              // Maybe add to the mob array in level to use later?
              break;
            case DrawableValues.POTION_HEALTH:
              entities.add(new HealthPotion(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.CHEST:
              entities.add(
                new Chest(new Vector2(x, map.length - y - 1), new HealthPotion(new Vector2(x, map.length - y - 1)))
              );
              break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return entities;
  }
}
