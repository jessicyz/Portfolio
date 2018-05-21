//
//  PlayerMove.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/26/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "PlayerMove.h"
#include <SDL/SDL.h>
#include "Math.h"
#include "Actor.h"
#include "Game.h"
#include "Block.h"
#include "Player.h"
PlayerMove::PlayerMove(class Actor* owner)
:MoveComponent(owner)
{
    ChangeState(MoveState::Falling);
    mVelocity = Vector3(0.0f, 0.0f, 0.0f);
    mAcceleration = Vector3(0.0f, 0.0f, 0.0f);
    mPendingForces = Vector3(0.0f, 0.0f, 0.0f);
    
    mRunningSFX = Mix_PlayChannel(-1, mOwner->GetGame()->GetSound("Assets/Sounds/Running.wav"), -1);
    
    Mix_Pause(mRunningSFX);
    
    SDL_Log("%s %i","running: ", mRunningSFX );
    
}



PlayerMove::~PlayerMove(){
    Mix_HaltChannel(mRunningSFX);
}
void PlayerMove::Update(float deltaTime){

    
//    • Now in PlayerMove::Update, add your check for z-position< -750.0f. If this happens,
//        “respawn” the player:
//        o Reset the position to the respawn pos
//        o Reset the rotation to 0
//        o Set both mVelocity and mPendingForces to Vector3::Zero
//        o Change the state to falling
    
    
    if (mOwner->GetPosition().z < -750){
        Player* player = reinterpret_cast<Player*>(mOwner);
        mOwner->SetPosition( player->GetRespawnPos()  );
        
        mOwner->SetRotation(0);
        mVelocity = Vector3::Zero;
        mPendingForces = Vector3::Zero;
        mCurrentState = Falling;
        if(Mix_PlayChannel(-1, mOwner->GetGame()->GetSound("Assets/Sounds/Respawn.wav"), 0)==-1) {
            printf("Mix_PlayChannel: %s\n",Mix_GetError());
            // may be critical error, or maybe just no channels were free.
            // you could allocated another channel in that case...
        }
        
        
    }
    
    
    
    if (mCurrentState == MoveState::OnGround){
        UpdateOnGround(deltaTime);
    }
    else if (mCurrentState == MoveState::Jump){
        UpdateJump(deltaTime);
    }
    else if (mCurrentState == MoveState::Falling){
        UpdateFalling(deltaTime);
    }
    else if (mCurrentState == MoveState::WallClimb){
        UpdateWallClimb(deltaTime);
    }
    else if (mCurrentState == MoveState::WallRun){
        UpdateWallClimb(deltaTime);
    }
    //shouldn't happen but
    else{
//        MoveComponent::Update(deltaTime);
    }
    
    
    
//    OnGround and the velocity’s length is > 50.0f or the
//    current state is WallClimb or the current state is WallRun
    if( (mCurrentState == OnGround && mVelocity.Length() > 50 )
       || mCurrentState == WallClimb
       || mCurrentState == WallRun
       ){
        Mix_Resume(mRunningSFX);
    }
    else{
        Mix_Pause(mRunningSFX);
    }
    
    
    
    
    
    
    
    
}

void PlayerMove::ProcessInput(const Uint8* keyState){
    
    
    if (keyState[SDL_SCANCODE_SPACE]&& !spaceDown ){
        if (mCurrentState == OnGround){
            spaceDown = true;

            AddForce(mJumpForce);
            ChangeState(Jump);
            if(Mix_PlayChannel(-1, mOwner->GetGame()->GetSound("Assets/Sounds/Jump.wav"), 0)==-1) {
                printf("Mix_PlayChannel: %s\n",Mix_GetError());

            }
//            SDL_Log("%s"," jump ");
            
        }
        
    }
    spaceDown = keyState[SDL_SCANCODE_SPACE];
    
    
    if (keyState[SDL_SCANCODE_W] &&keyState[SDL_SCANCODE_S]){
        SetForwardSpeed(0);
    }
    else if (keyState[SDL_SCANCODE_W]){

        AddForce( mOwner->GetForward()*700 );
        
    }
    else if (keyState[SDL_SCANCODE_S]){

        AddForce( mOwner->GetForward()*-700 );
        
    }
    else{
        SetForwardSpeed(0);
        
    }
    
    if (keyState[SDL_SCANCODE_A] &&keyState[SDL_SCANCODE_D]){
        SetStrafeSpeed(0);
    }
    else if (keyState[SDL_SCANCODE_D]){

        AddForce( mOwner->GetRight()*700 );
        
    }
    else if (keyState[SDL_SCANCODE_A]){

        AddForce( mOwner->GetRight()*-700 );
        
    }
    else{
        SetStrafeSpeed(0);
        
    }
    
    
    int x, y;
    SDL_GetRelativeMouseState(&x, &y);

    SetAngularSpeed(Math::Pi * 10.0f*x/500.0f);
    

    
    mOwner->GetCamera()->SetPitchSpeed(Math::Pi * 10.0f*y/500.0f);
    
    

    
    
}

void PlayerMove::ChangeState(MoveState ms){
    mCurrentState = ms;
}

void PlayerMove::UpdateOnGround(float deltaTime){

    
    PhysicsUpdate(deltaTime);
    
    
    
    bool hasTop = false;

    std::vector<Block*> blocks = mOwner->GetGame()->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        PlayerMove::CollSide check = FixCollision(mOwner->GetCollision(), blocks[i]->GetCollision());
        if (check == SideX1){
//            SDL_Log("%s", "Checking wallclimbing X1");
            if (CanWallClimb(check)){
                
//                SDL_Log("%s", "wallclimbing X1");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
        }
        else if (check == SideX2){

            if (CanWallClimb(check)){
//                SDL_Log("%s", "wallclimbing X2");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
        }
        else if (check == SideY1){

            if (CanWallClimb(check)){
//                SDL_Log("%s", "wallclimbing Y1");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
        }
        else if (check == SideY2){

            if (CanWallClimb(check)){
//                SDL_Log("%s", "wallclimbing Y2");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
        }
        else if (check == Top){
            
            hasTop = true;
        }
        
    }
    if (!hasTop){
//        SDL_Log("%s","Falling");
        ChangeState(Falling);
    }

    
    
}


void PlayerMove::UpdateJump(float deltaTime){

    AddForce(mGravity);
    PhysicsUpdate(deltaTime);

    std::vector<Block*> blocks = mOwner->GetGame()->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        PlayerMove::CollSide check = FixCollision(mOwner->GetCollision(), blocks[i]->GetCollision());
        if (check == Bottom){
//            SDL_Log("%s","Hit head");
            mVelocity.z = 0;
        }
        
        
        if (check == SideX1){
            //            SDL_Log("%s", "Checking wallclimbing X1");
//            SDL_Log("%s", "Check wallrunning X1");
            if (CanWallClimb(check)){
                
//                SDL_Log("%s", "wallclimbing X1");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
            else if (CanWallRun(check)){
                mCurrentState = WallRun;
                mWallRunTimer = 0;
//                SDL_Log("%s", "Wallrunning X1");
                return;
            }
        }
        else if (check == SideX2){
//            SDL_Log("%s", "Check wallrunning X2");
            
            if (CanWallClimb(check)){
//                SDL_Log("%s", "wallclimbing X2");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
            else if (CanWallRun(check)){
                mCurrentState = WallRun;
//                SDL_Log("%s", "Wallrunning X2");
                mWallRunTimer = 0;
                return;
            }
        }
        else if (check == SideY1){
//            SDL_Log("%s", "Check wallrunning Y1");
            if (CanWallClimb(check)){
//                SDL_Log("%s", "wallclimbing Y1");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
            else if (CanWallRun(check)){
                mCurrentState = WallRun;
                mWallRunTimer = 0;
//                SDL_Log("%s", "Wallrunning Y1");
                return;
            }
            
        }
        else if (check == SideY2){
//            SDL_Log("%s", "Check wallrunning Y2");
            if (CanWallClimb(check)){
//                SDL_Log("%s", "wallclimbing Y2");
                mCurrentState = WallClimb;
                mWallClimbTimer = 0;
                return;
            }
            else if (CanWallRun(check)){
                mCurrentState = WallRun;
                mWallRunTimer = 0;
//                SDL_Log("%s", "Wallrunning Y2");
                return;
            }
        }

        
    
    }
    if (mVelocity.z <= 0){
        ChangeState(Falling);
//        SDL_Log("%s","Jump fall");
    }
    
    

}


void PlayerMove::UpdateFalling(float deltaTime){

    AddForce(mGravity);

    PhysicsUpdate(deltaTime);
    
    std::vector<Block*> blocks = mOwner->GetGame()->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        PlayerMove::CollSide check = FixCollision(mOwner->GetCollision(), blocks[i]->GetCollision());
        if (check == Top){
            mVelocity.z = 0;
            ChangeState(OnGround);
            if(Mix_PlayChannel(-1, mOwner->GetGame()->GetSound("Assets/Sounds/Land.wav"), 0)==-1) {
                printf("Mix_PlayChannel: %s\n",Mix_GetError());

            }
//            SDL_Log("%s","Landed");
        }
    }
    
}


PlayerMove::CollSide PlayerMove::FixCollision(CollisionComponent* self, CollisionComponent* block){
    

        if ( block -> Intersect(self)){
            float dx1 = ( (self->GetMax().x) -
                         (block->GetMin().x) );
            float dx2 = ( (block->GetMax().x) -
                         (self->GetMin().x) );
            
            float dy1 = ( (self->GetMax().y) -
                         (block->GetMin().y) );
            float dy2 = ( (block->GetMax().y) -
                         (self->GetMin().y) );
            
            float dz1 = ( (self->GetMax().z) -
                         (block->GetMin().z) );
            float dz2 = ( (block->GetMax().z) -
                         (self->GetMin().z) );
            //if closer to front
            if (dx1 < dx2
                && dx1 < dy1 && dx1 <dy2
                && dx1 < dz1 && dx1 < dz2){
                mOwner->SetPosition(Vector3( block->GetMin().x - self->GetDepth()/2 , mOwner->GetPosition().y, mOwner->GetPosition().z  ) );
                AddForce(Vector3( -700, 0, 0 ) );
                return SideX1;
            }
            //if close to back
            else if (dx2 < dx1 &&
                     dx2 < dy1 && dx2 <dy2
                     && dx2 < dz1 && dx2 < dz2){
                mOwner->SetPosition(Vector3( block->GetMax().x + self->GetDepth()/2, mOwner->GetPosition().y, mOwner->GetPosition().z  ) );
                AddForce(Vector3( 700, 0, 0 ) );
                return SideX2;
            }
            //if close to left
            else if (dy2 < dx1 && dy2 < dx2
                     && dy2 <dy1
                     && dy2 < dz1 && dy2 < dz2){
                mOwner->SetPosition(Vector3( mOwner->GetPosition().x, block->GetMax().y + self->GetWidth()/2, mOwner->GetPosition().z  ) );
                AddForce(Vector3( 0, 700, 0 ) );
                return SideY2;
                
            }
            //if close to right
            else if (dy1 < dx1 && dy1 < dx2
                     && dy1< dy2
                     && dy1 < dz1 && dy1 < dz2){
                mOwner->SetPosition(Vector3( mOwner->GetPosition().x, block->GetMin().y - self->GetWidth()/2, mOwner->GetPosition().z  ) );
                AddForce(Vector3( 0, -700, 0 ) );
                return SideY1;
                
            }
            //if bottom
            else if (dz1 < dx1 && dz1 < dx2
                     && dz1 < dy1 && dz1 <dy2
                     && dz1 < dz1){
                mOwner->SetPosition(Vector3( mOwner->GetPosition().x, mOwner->GetPosition().y, block->GetMin().z - self->GetHeight()/2 ) );
//                SDL_Log("%s"," bottom ");
                return Bottom;
                
            }
            //if top
            else{
                //enough for it to not intersect with the top of the block constantly
                mOwner->SetPosition(Vector3( mOwner->GetPosition().x, mOwner->GetPosition().y, block->GetMax().z + self->GetHeight()/2) );
//
//                SDL_Log("%s"," top ");
                return Top;
            }
            
        }

    
    //}
    return None;
}

void PlayerMove::PhysicsUpdate(float deltaTime){

    
    mAcceleration = mPendingForces*(1.0f/mMass);
    mVelocity += mAcceleration*deltaTime;
    
    FixXYVelocity();
    
    mOwner->SetPosition( mOwner->GetPosition() + mVelocity*deltaTime  );
    mOwner->SetRotation(mOwner->GetRotation() +  GetAngularSpeed()*deltaTime );
    mPendingForces= Vector3::Zero;
    
    
}


void PlayerMove::AddForce(const Vector3& force){

    mPendingForces += force;
    
}


void PlayerMove::FixXYVelocity(){

    Vector2 xyVelocity = Vector2( mVelocity.x, mVelocity.y );
    if (xyVelocity.Length() > 400.0f){
        xyVelocity = Vector2::Normalize(xyVelocity)*400.0f;
    }
    if (mCurrentState == OnGround || mCurrentState == WallClimb
        //||  mCurrentState == WallRun
        ){
        if ( Math::NearZero(mAcceleration.x)
            ||(
             (mAcceleration.x < 0 && xyVelocity.x > 0) ||
             (mAcceleration.x > 0 && xyVelocity.x < 0)
             )
             ){
            xyVelocity.x = xyVelocity.x*.9;
        }
        
        if ( Math::NearZero(mAcceleration.y)
            ||(
             (mAcceleration.y < 0 && xyVelocity.y > 0) ||
             (mAcceleration.y > 0 && xyVelocity.y < 0)
             )
            ){
            xyVelocity.y = xyVelocity.y*.9;
        }
    }
    mVelocity = Vector3(xyVelocity.x, xyVelocity.y , mVelocity.z );
    
    
    
}


void PlayerMove::UpdateWallClimb(float deltaTime){

  
    
    AddForce(mGravity);
    
    mWallClimbTimer += deltaTime;
    if (mWallClimbTimer < .4){
        AddForce(mWallClimbForce);
    }
    
    PhysicsUpdate(deltaTime);
    
    bool collideWithSide = false;
    
    std::vector<Block*> blocks = mOwner->GetGame()->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        PlayerMove::CollSide check = FixCollision(mOwner->GetCollision(), blocks[i]->GetCollision());
        if (check == SideX1 || check == SideX2
            || check == SideY1 || check == SideY2){
            collideWithSide = true;
        }
    }
    if (mVelocity.z <= 0 || !collideWithSide){
        mVelocity.z = 0;
        mCurrentState = Falling;
    }
    
    
    
}

bool PlayerMove::CanWallClimb(CollSide wallSide){

    Vector2 xyVelocity = Vector2( mVelocity.x, mVelocity.y );

    //front of wall
    if (wallSide == SideX1){
//        SDL_Log("%s", "checking side x1");

        if ((Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(-1.0f, 0.0f, 0.0f)) <= -0.9f)
            &&
            (Vector2::Dot(Vector2::Normalize(xyVelocity), Vector2(-1.0f, 0.0f)) <= -0.9f)
            &&
            (xyVelocity.Length()>350.0f)) {
            
            return true;
        }
    }
    else if (wallSide == SideX2){
        //back of wall
//        SDL_Log("%s", "checking side x2");
        
        if ((Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(1.0f, 0.0f, 0.0f)) <= -0.9f)
            &&
            (Vector2::Dot( Vector2::Normalize(xyVelocity), Vector2(1.0f, 0.0f)) <= -0.9f)
            &&
            (xyVelocity.Length()>350.0f)) {
            return true;
        }
    }
    else if (wallSide == SideY1){
        //right of wall
//        SDL_Log("%s", "checking side y1");
        
        if ((Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(0.0f, 1.0f, 0.0f)) >= 0.9f)
            &&
            (Vector2::Dot(Vector2::Normalize(xyVelocity), Vector2(0.0f, 1.0f)) >= 0.9f)
            &&
            (xyVelocity.Length()>350.0f)) {
            
            return true;
        }
    }
    //left of wall
    else if (wallSide == SideY2){
//        SDL_Log("%s", "checking side y2");
        
        if ((Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(0.0f, -1.0f, 0.0f)) >= 0.9f)
            &&
            (Vector2::Dot(Vector2::Normalize(xyVelocity), Vector2(0.0f, -1.0f)) >= 0.9f)
            &&
            (xyVelocity.Length()>350.0f)) {
            
            return true;
        }
    }
    
    

    return false;
    
    
}


void PlayerMove::UpdateWallRun(float deltaTime){

    
    AddForce(mGravity);
    
    mWallRunTimer += deltaTime;
    if (mWallRunTimer < .4){
        AddForce(mWallRunForce);
    }
    
    PhysicsUpdate(deltaTime);
    
//    bool collideWithSide = false;
    
    std::vector<Block*> blocks = mOwner->GetGame()->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        PlayerMove::CollSide check = FixCollision(mOwner->GetCollision(), blocks[i]->GetCollision());

    }
    if (mVelocity.z <= 0){
        mVelocity.z = 0;
        mCurrentState = Falling;
    }
    
    
    

    
}


bool PlayerMove::CanWallRun(CollSide wallSide){

    Vector2 xyVelocity = Vector2( mVelocity.x, mVelocity.y );
    
    
    float forwardFacing = (Vector2::Dot(Vector2::Normalize(xyVelocity), Vector2::Normalize(Vector2( mOwner->GetForward().x,  mOwner->GetForward().y))) );
    
    
    //front of wall
    if (wallSide == SideX1){
        float facing = (Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(-1.0f, 0.0f, 0.0f)));
        //        SDL_Log("%s", "checking side x1");
        
        if (( facing <= 0.5f ||  facing >= -.5)
            &&
            forwardFacing >= 0.5f
            &&
            (xyVelocity.Length()>350.0f)) {
            
            return true;
        }
    }
    else if (wallSide == SideX2){
        //back of wall
        //        SDL_Log("%s", "checking side x2");
        float facing = (Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(1.0f, 0.0f, 0.0f)));
        
        
        if ( ( facing <= 0.5f ||  facing >= -.5)
            &&
            forwardFacing >= 0.5f
            &&
            (xyVelocity.Length()>350.0f)) {
            return true;
        }
    }
    else if (wallSide == SideY1){
        //right of wall
        //        SDL_Log("%s", "checking side y1");
        
        float facing = (Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(0.0f, 1.0f, 0.0f)));
        
        if (( facing <= 0.5f ||  facing >= -.5)
            &&
            forwardFacing >= 0.5f
            &&
            (xyVelocity.Length()>350.0f)) {
            
            return true;
        }
    }
    //left of wall
    else if (wallSide == SideY2){
        //        SDL_Log("%s", "checking side y2");
        float facing = std::abs(Vector3::Dot(Vector3::Normalize(this->mOwner->GetForward()), Vector3(0.0f, -1.0f, 0.0f)));
        

        
        if (( facing <= 0.5f ||  facing >= -.5)
            &&
             forwardFacing >= 0.5f
            &&
            (xyVelocity.Length()>350.0f)) {
            
            return true;
        }
    }
    
    
    
    return false;
    
    
}



