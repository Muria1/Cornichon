package com.cornichon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cornichon.Cornichon;
import com.cornichon.utils.Constants;

public class DesktopLauncher {

  public static void main(String[] args) {
    final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = Constants.GAME_NAME;
    config.width = Constants.WINDOW_WIDTH;
    config.height = Constants.WINDOW_HEIGHT;
    config.resizable = true; // Has some issues in the menus but it's ok

    new LwjglApplication(new Cornichon(), config);
  }
}
