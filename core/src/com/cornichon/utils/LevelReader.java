package com.cornichon.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.cornichon.models.construction.components.Block;
import com.cornichon.models.construction.helpers.BlockValues;

public class LevelReader {

  public static Array<Block> readLevel(String level) {
    final Array<Block> blocks = new Array<Block>();

    try {
      FileHandle file = Gdx.files.internal("levels/" + level);
      JsonReader json = new JsonReader();

      JsonValue arr = json.parse(file);

      for (int y = 0; y < arr.size; y += 1) {
        for (int x = 0; x < arr.get(0).size; x += 1) {
          // arr[0] 0 => (100)
          switch (arr.get(y).get(x).asInt()) {
            case BlockValues.BRICK:
              blocks.add(new Block(new Vector2(x, arr.size - y)));
              break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return blocks;
  }
}
