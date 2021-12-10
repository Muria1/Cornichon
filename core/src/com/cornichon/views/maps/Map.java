package com.cornichon.views.maps;

import com.badlogic.gdx.utils.Array;
import com.cornichon.models.entities.Entity;
import com.cornichon.utils.LevelWriter;

public class Map {

  private int[][] map;

  public Map(int difficulty) {
    this.map = new int[70][100];
  }

  public void processMap() {
    LevelWriter.initMap(map);
    LevelWriter.placePlayer(map);
  }

  public int[][] getMapIntArr() {
    return this.map;
  }
}
