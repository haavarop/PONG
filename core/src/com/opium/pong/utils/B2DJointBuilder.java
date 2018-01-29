package com.opium.pong.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;

import static com.opium.pong.utils.B2DConstants.PPM;

/**
 *  Created by opium on 28.01.18.
 */

public class B2DJointBuilder {

    private B2DJointBuilder() {}

    public static Joint createPrisnaticJoint(World world, Body bodyA, Body bodyB,
                                             float upperLim, float lowerLim,
                                             Vector2 anchorA, Vector2 anchorB) {
        Joint joint;
        PrismaticJointDef pDef = new PrismaticJointDef();

        pDef.bodyA = bodyA;
        pDef.bodyB = bodyB;
        pDef.collideConnected = false;
        pDef.enableLimit = true;
        pDef.localAnchorA.set(anchorA);
        pDef.localAnchorB.set(anchorB);
        pDef.localAxisA.set(0,1);
        pDef.upperTranslation = upperLim / PPM;
        pDef.lowerTranslation = lowerLim / PPM;

        return world.createJoint(pDef);
    }
}
