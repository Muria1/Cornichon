package com.cornichon.views.maps;

public class Map {

  private int[][] map;

  public Map(int config) {
    this.map = new int[70][100];
  }

  public void processMap() {
    for (int i = 0; i < 10; i += 1) {
      map[54][i] = 1;
      map[69][i] = 1;
    }

    for (int i = 0; i < 15; i += 1) {
      map[70 - i - 1][0] = 1;
      map[70 - i - 1][10] = 1;
    }

    map[55][1] = -1;

    map[68][2] = 90;
    map[68][5] = 91;
    map[68][7] = 91;
  }

  public int[][] getMapIntArr() {
    return this.map;
  }
}
