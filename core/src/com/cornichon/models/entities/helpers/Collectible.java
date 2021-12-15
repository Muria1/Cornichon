package com.cornichon.models.entities.helpers;

import com.cornichon.models.construction.Level;
import com.cornichon.models.entities.aliveEntities.Player;

public interface Collectible {
  void collected(Player player, Level level);
  void applyEffect(Player player, Level level);
}
