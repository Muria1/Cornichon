// package com.cornichon.models.entities.aliveEntities;

// import com.badlogic.gdx.math.Rectangle;
// import com.badlogic.gdx.math.Shape2D;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.BodyDef;
// import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
// import com.cornichon.models.entities.MovingEntity;
// import com.cornichon.models.entities.helpers.State;

// public class Sphere extends MovingEntity {

//     public static final float SIZE_HEIGTH = 0.3f; // half a uni
//     public static final float SIZE_WIDTH = 0.3f; // half a uni
//     public static final float SPEED = 6f; // unit per second
//     public static final float JUMP_VELOCITY = 1f;
//     public static final Rectangle BOUNDS = new Rectangle()
//             .setWidth(SIZE_HEIGTH)
//             .setHeight(SIZE_WIDTH);

//     private State state;
//     private boolean facingLeft;
//     public BodyDef b2bBody;

//     public Sphere(Vector2 position) {
//         super(
//                 position,
//                 SIZE_HEIGTH,
//                 SIZE_WIDTH,
//                 BOUNDS,
//                 SPEED,
//                 JUMP_VELOCITY,
//                 new Vector2(), // Acceleration
//                 new Vector2() // Velocity
//         );

//         this.state = State.IDLE;
//         this.facingLeft = false;
//         this.b2bBody = new BodyDef();
//         b2bBody.type = BodyType.KinematicBody;
//     }

//     public void update(float delta) {
//         this.position.add(new Vector2(velocity.x * delta, velocity.y * delta));
//     }

//     public State getState() {
//         return state;
//     }

//     public void setState(State state) {
//         this.state = state;
//     }

//     public boolean isFacingLeft() {
//         return facingLeft;
//     }

//     public void setFacingLeft(boolean facingLeft) {
//         this.facingLeft = facingLeft;
//     }

// }