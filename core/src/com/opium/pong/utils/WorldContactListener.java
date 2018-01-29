package com.opium.pong.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.opium.pong.MyGdxGame;

import java.util.Random;

import static com.opium.pong.utils.B2DConstants.PPM;

/**
 *  Created by opium on 29.01.18.
 */

public class WorldContactListener implements ContactListener{

    OrthographicCamera camera;

    public WorldContactListener(){

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
    }


    @Override
    public void beginContact(final Contact contact) {
        //Fixture B is ball

        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyB.getUserData() == "ball"){

            if(bodyA.getUserData() == "Edge") {
                Vector2 oldVec = bodyB.getLinearVelocity();
                bodyB.setLinearVelocity(oldVec.x, -oldVec.y);
            }else if(bodyA.getUserData() == "paddleLeft") {
                bodyB.setLinearVelocity(randInt(7,10), randInt(-8,8));
            }else if(bodyA.getUserData() == "paddleRight"){
                bodyB.setLinearVelocity(randInt(-10, -7), randInt(-8,8));
            }else if(bodyA.getUserData() == "goalRight" || bodyA.getUserData() == "goalLeft"){
                String scorer = bodyA.getUserData().toString();
                System.out.println("Point to " + scorer.substring(4));

                //Reset the ball to middle of the screen
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run () {
                        contact.getFixtureB().getBody().setTransform(
                                (camera.viewportWidth / 2) / PPM,
                                (camera.viewportHeight / 2)/PPM,
                                0
                        );
                        contact.getFixtureB().getBody().setLinearVelocity(-5f,0f);
                    }
                });

            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
