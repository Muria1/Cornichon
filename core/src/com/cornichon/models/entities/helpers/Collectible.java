package com.cornichon.models.entities.helpers;

import com.cornichon.models.entities.aliveEntities.Player;

public interface Collectible {
  void collected(Player player);
}
