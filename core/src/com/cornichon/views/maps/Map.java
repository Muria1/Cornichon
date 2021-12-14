package com.cornichon.views.maps;

import com.badlogic.gdx.utils.Array;
import com.cornichon.models.entities.Entity;
import com.cornichon.utils.LevelWriter;

public class Map {

  private int[][] map;
  private LevelWriter levelWriter;

  public Map(int difficulty) {
    this.map = new int[70][100];
    this.levelWriter = new LevelWriter(difficulty);
  }

  public void processMap() {
    levelWriter.initMap(map);
    levelWriter.placeMobsAndCollectibles(map);
    levelWriter.placePlayer(map);
  }

  public int[][] getMapIntArr() {
    return this.map;
  }
}
