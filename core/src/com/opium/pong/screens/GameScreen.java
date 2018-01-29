package com.opium.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;
import com.opium.pong.MyGdxGame;
import com.opium.pong.utils.B2DBodyBuilder;
import com.opium.pong.utils.B2DJointBuilder;
import com.opium.pong.utils.WorldContactListener;

import static com.opium.pong.utils.B2DConstants.PPM;

/**
 *  Created by opium on 27.01.18.
 */

public class GameScreen extends AbstractScreen {

    OrthographicCamera camera;

    //Box2D
    World world;
    Box2DDebugRenderer b2dr;

    //Game items
    Body ball;
    Body paddleLeft, goalLeft;
    Body paddleRight, goalRight;

    public GameScreen(final MyGdxGame app) {
        super(app);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

        world = new World(new Vector2(0f,0f), false);
        initField();
        app.batch.setProjectionMatrix(camera.combined);
        app.shapeBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void update(float delta) {
        world.step(1f / MyGdxGame.APP_FPS,6,2);

        float mouseToWorld = -(Gdx.input.getY() - camera.viewportHeight) / PPM;
        float mLerp = paddleLeft.getPosition().y + (mouseToWorld - paddleLeft.getPosition().y) * .2f;

        if(mLerp * PPM < camera.viewportHeight - 25f * PPM){
            mLerp = (camera.viewportHeight - 25f) / PPM;
        }else if(mLerp * PPM < 25f){
            mLerp = 25f / PPM;
        }

        paddleLeft.setTransform(paddleLeft.getPosition().x, mLerp, paddleLeft.getAngle());
        paddleRight.setTransform(paddleRight.getPosition().x, mLerp, paddleRight.getAngle());
        stage.act(delta);

    }

    @Override
    public void render(float delta){
        super.render(delta);
        b2dr.render(world, camera.combined.cpy().scl(PPM));
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }

    private void createWalls() {

        Vector2[] v = new Vector2[5];
        v[0] = new Vector2(1f / PPM, 0);
        v[1] = new Vector2(camera.viewportWidth / PPM , 0);
        v[2] = new Vector2(camera.viewportWidth / PPM, (camera.viewportHeight -1f) / PPM);
        v[3] = new Vector2(1f / PPM,(camera.viewportHeight -1f)/PPM);
        v[4] = new Vector2(1f / PPM, 0);
        B2DBodyBuilder.createChainShape(world, v);

    }

    private void initField() {

        createWalls();
        ball = B2DBodyBuilder.createBall(
                world,
                camera.viewportWidth / 2,
                camera.viewportHeight / 2,
                8f
        );

        ball.applyLinearImpulse(new Vector2(-1f,0f), ball.getWorldCenter(), true);

        paddleLeft = B2DBodyBuilder.createBox(
                world,
                40,
                camera.viewportHeight / 2,
                10,
                50,
                false,
                false
        );

        paddleRight = B2DBodyBuilder.createBox(
                world,
                camera.viewportWidth - 40,
                camera.viewportHeight / 2,
                10,
                50,
                false,
                false
        );

        goalLeft = B2DBodyBuilder.createBox(
                world,
                5, camera.viewportHeight / 2,
                10, camera.viewportHeight,
                true,
                true
        );


        goalRight = B2DBodyBuilder.createBox(
                world,
                camera.viewportWidth - 10,
                camera.viewportHeight / 2,
                10, camera.viewportHeight,
                true,
                true
        );

        B2DJointBuilder.createPrisnaticJoint(
                world,
                goalLeft,
                paddleLeft,
                camera.viewportHeight / 2,
                - camera.viewportHeight / 2,
                new Vector2(16 / PPM, 0),
                new Vector2(0,0)
        );
        B2DJointBuilder.createPrisnaticJoint(
                world,
                goalRight,
                paddleRight,
                camera.viewportHeight / 2,
                - camera.viewportHeight / 2,
                new Vector2(-16 / PPM, 0),
                new Vector2(0,0)
        );

        ball.setUserData("ball");
        paddleLeft.setUserData("paddleLeft");
        paddleRight.setUserData("paddleRight");
        goalLeft.setUserData("goalLeft");
        goalRight.setUserData("goalRight");

        world.setContactListener(new WorldContactListener());

    }

}
