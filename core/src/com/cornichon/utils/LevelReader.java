package com.cornichon.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cornichon.models.construction.Level;
import com.cornichon.models.construction.components.Brick;
import com.cornichon.models.construction.components.Door;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.Spikes;
import com.cornichon.models.entities.aliveEntities.Player;
import com.cornichon.models.entities.aliveEntities.Skeleton;
import com.cornichon.models.entities.aliveEntities.Slime;
import com.cornichon.models.entities.collectibles.HealthPotion;
import com.cornichon.models.entities.collectibles.ManaPotion;
import com.cornichon.views.helpers.DrawableValues;
import java.util.ArrayList;
import java.util.List;

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
              level.setSphere(player.getSphere());
              break;
            case DrawableValues.SKELETON:
              entities.add(new Skeleton(new Vector2(x, map.length - y - 1)));
              // Maybe add to the mob array in level to use later?
              break;
            case DrawableValues.SLIME:
              entities.add(new Slime(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.POTION_HEALTH:
              entities.add(new HealthPotion(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.DOOR_CLOSED:
              Door door = new Door(new Vector2(x, map.length - y - 1), level);
              level.setDoor(door);
              break;
            case DrawableValues.POTION_MANA:
              entities.add(new ManaPotion(new Vector2(x, map.length - y - 1)));
              break;
            case DrawableValues.SPIKES:
              entities.add(new Spikes(new Vector2(x, map.length - y - 1)));
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
