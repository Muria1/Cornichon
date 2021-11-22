package com.cornichon.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TextureSetup {

  public static void main(String[] args) throws Exception {
    TexturePacker.process(
      "core/assets/images",
      "core/assets/images",
      "textureAtlas"
    );
  }
}
