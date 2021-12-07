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

public final class LevelReader {

  private static JsonReader json = new JsonReader();

  public static JsonValue readJsonFile(String fileString) {
    FileHandle file = Gdx.files.internal("levels/" + fileString);
    JsonValue value = LevelReader.json.parse(file);

    return value;
  }

  public static Array<ScreenDrawable> readLevel(String levelFile, Level level) {
    final Array<ScreenDrawable> drawables = new Array<ScreenDrawable>();

    try {
      JsonValue levelJson = LevelReader.readJsonFile(levelFile);

      for (int y = 0; y < levelJson.size; y += 1) {
        for (int x = 0; x < levelJson.get(0).size; x += 1) {
          switch (levelJson.get(y).get(x).asInt()) {
            case DrawableValues.BRICK:
            case DrawableValues.BRICK_PLATFORM:
              drawables.add(new Brick(new Vector2(x, levelJson.size - y - 1)));
              break;
            case DrawableValues.PLAYER:
              Player player = new Player(new Vector2(x, levelJson.size - y - 1));
              drawables.add(player);
              level.setPlayer(player);
              break;
            case DrawableValues.SKELETON:
              drawables.add(new Skeleton(new Vector2(x, levelJson.size - y - 1)));
              // Maybe add to the mob array in level to use later?
              break;
            case DrawableValues.BRICK_BACKGROUND:
              LevelWriter.fillBackground(levelJson, drawables, x, y);
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
