//
//  Tank.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/19/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "Tank.h"
#include "Game.h"
#include "Renderer.h"
#include "Turret.h"
#include "TankMove.h"
#include "Bullet.h"
Tank::Tank(Game* game)
:Actor(game){
    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/TankBase.gpmesh"));
//    mScale = 64;
    mMove = new TankMove(this);
    mTurret = new Turret(mGame);
    mCollision = new CollisionComponent(this);
    mCollision->SetSize(30, 30, 30);
    

}

void Tank::UpdateActor(float deltaTime){
    mTurret->SetPosition(this->GetPosition());
}


void Tank::SetPlayerTwo(){
    static_cast<TankMove*>(mMove)->SetPlayerTwo();
    mTurret->SetPlayerTwo();
    mMesh->SetTextureIndex(1);
    mFireKey = SDL_SCANCODE_RSHIFT;

}

void Tank::Fire(){
    Bullet* b = new Bullet(mGame);
    b->SetPosition(mPosition);
    b->SetTank(this);
    b->SetRotation(mRotation);
}

void Tank::SetInitialPos(Vector3 pos){
    mInitialPos = pos;
    
}


void Tank::Respawn(){
    SetPosition(mInitialPos);
    SetRotation(0);
    mTurret->SetRotation(0);
}


void Tank::ActorInput(const Uint8* keyState){
    if (keyState[mFireKey] && !firing){
        firing = true;
        Fire();
    }
    if (!keyState[mFireKey]){
        firing = false;
    }
    
    
    
}
