//
//  PlayerMove.h
//  Game-mac
//
//  Created by Jess Zhu on 10/26/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef PlayerMove_h
#define PlayerMove_h
#include "MoveComponent.h"
#include "Math.h"
class PlayerMove : public MoveComponent{
public:

    
    PlayerMove(class Actor* owner);
    ~PlayerMove();
    void Update(float deltaTime) override;
    void ProcessInput(const Uint8* keyState) override;
    
    enum MoveState
    {
        OnGround,
        Jump,
        Falling,
        WallClimb,
        WallRun
    };
    
    void ChangeState(MoveState ms);
    
    
    
    enum CollSide{
        None,
        Top,
        Bottom,
        SideX1,
        SideX2,
        SideY1,
        SideY2
    };
    void PhysicsUpdate(float deltaTime);
    void AddForce(const Vector3& force);
    void FixXYVelocity();
    
protected:
    void UpdateOnGround(float deltaTime);
    void UpdateJump(float deltaTime);
    void UpdateFalling(float deltaTime);
    CollSide FixCollision(class CollisionComponent* self, class CollisionComponent* block);
    
    void UpdateWallClimb(float deltaTime);
    bool CanWallClimb(CollSide wallSide);
    
    void UpdateWallRun(float deltaTime);
    bool CanWallRun(CollSide wallSide);
    
    
private:
    
    MoveState mCurrentState;
//    float mZSpeed = 0.0f;
//    const float Gravity = -980.0f;
//    const float JumpSpeed = 500.0f;

    Vector3 mVelocity;
    Vector3 mAcceleration;
    Vector3 mPendingForces;
    
    float mMass = 1.0f;
    Vector3 mGravity = Vector3(0.0f,0.0f,-980.0f);
    Vector3 mJumpForce = Vector3(0.0f,0.0f,35000.0f);
    bool spaceDown = false;
    Vector3 mWallClimbForce = Vector3( 0,0, 1800);
    float mWallClimbTimer = 0;
    
    Vector3 mWallRunForce = Vector3(0, 0, 1200);
    float mWallRunTimer = 0;
    
    int mRunningSFX;
    
};


#endif /* PlayerMove_h */
