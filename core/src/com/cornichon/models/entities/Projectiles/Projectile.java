package com.cornichon.models.entities.Projectiles;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.cornichon.models.entities.Entity;
import com.cornichon.models.entities.MovingEntity;

public class Projectile extends MovingEntity {
    protected int damage;

    public Projectile(int damage, Vector2 position, float sizeHeight, float sizeWidth, Shape2D bounds, float speed,
            float jumpVelocity, Vector2 acceleration, Vector2 velocity) {
        super(position, sizeHeight, sizeWidth, bounds, speed, jumpVelocity, acceleration, velocity);

        this.damage = damage;
    }

    public void update(float delta) {
        this.position.add(new Vector2(velocity.x * delta, velocity.y * delta));
    }

}
