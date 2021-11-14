package com.cornichon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cornichon.Cornichon;

public class DesktopLauncher {

  public static void main(String[] arg) {
    final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Cornichon";
    config.width = 480;
    config.height = 320;

    new LwjglApplication(new Cornichon(), config);
  }
}
