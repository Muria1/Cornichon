package com.cornichon.utils;

public final class Constants {

  public static final String GAME_NAME = "Cornichon";
  public static final int WINDOW_WIDTH = 1280;
  public static final int WINDOW_HEIGHT = 720;

  public static final short CATEGORY_PLAYER = 0x0001;
  public static final short CATEGORY_MOB = 0x0002;
  public static final short CATEGORY_PROJECTILE = 0x0004;
  public static final short CATEGORY_BLOCK = 0x0008;
  public static final short CATEGORY_COLLECTIBLE = 0x0010;
  public static final short CATEGORY_DOOR = 0x0020;
  public static final short CATEGORY_TOP = 0x0040;
  public static final short CATEGORY_SPIKES = 0x0080;

  public static final short MASK_PLAYER = CATEGORY_MOB | CATEGORY_PROJECTILE | CATEGORY_COLLECTIBLE | CATEGORY_BLOCK
      | CATEGORY_TOP | CATEGORY_SPIKES | CATEGORY_DOOR;

  public static final short MASK_PROJECTILES = CATEGORY_PLAYER | CATEGORY_BLOCK | CATEGORY_TOP;

  public static final short MASK_COLLECTIBLES = CATEGORY_PLAYER | CATEGORY_TOP | CATEGORY_BLOCK;

  public static final short MASK_MOB = CATEGORY_PLAYER | CATEGORY_BLOCK | CATEGORY_TOP;

  public static final short MASK_BLOCK = -1;

  public static final short MASK_DOOR = CATEGORY_PLAYER | CATEGORY_BLOCK;

  public static final short MASK_TOP = CATEGORY_PLAYER | CATEGORY_BLOCK | CATEGORY_MOB | CATEGORY_PROJECTILE
      | CATEGORY_COLLECTIBLE;

  public static final short MASK_SPIKES = CATEGORY_PLAYER | CATEGORY_BLOCK;

  public static final short GROUP_PLAYER_MOBS_SPHERE_DOOR = 1;
  public static final short GROUP_PROJECTILES = 2;
  public static final short GROUP_COLLETIBLES = 3;
  public static final short GROUP_BLOCKS = 10;

  public static final int PLAYER_HEALTH = 100;
  public static final int SPHERE_DAMAGE = 25;
  public static final int SPHERE_BUFFED_DAMAGE = 50;

  public static final int MOB_HEALTH_GENERAL = 100;

  public static final int SPIKE_DAMAGE = 25;

  public static final int SKELETON_DAMAGE = 20;
  public static final int WIZARD_DAMAGE = 25;
  public static final int FIREBALL_DAMAGE = 25;

  public static final int SLIME_DAMAGE = 15;

}
