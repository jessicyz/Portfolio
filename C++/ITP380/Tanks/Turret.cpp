//
//  Turret.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/19/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "Turret.h"
#include "Game.h"
#include "Renderer.h"
Turret::Turret(Game* game)
:Actor(game){
    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/TankTurret.gpmesh"));
    mScale = 1.8;
    mMove = new MoveComponent(this);
    

}


void Turret::ActorInput(const Uint8* keyState){
//    In Turret, add an override of ActorInput and rotate the turret, via mMove, based on the
//following:
//    o Q – Rotate counter-clockwise (speed Math::TwoPi)
//    o E – Rotate counter-clockwise (speed -Math::TwoPi)
    
    if (keyState[mLeftKey]){
        mMove->SetAngularSpeed(Math::TwoPi);
    }
    else if (keyState[mRightKey]){
        mMove->SetAngularSpeed(-Math::TwoPi);
    }
    else{
        mMove->SetAngularSpeed(0);
    }
}

void Turret::SetPlayerTwo(){

    mLeftKey = SDL_SCANCODE_I;
    mRightKey = SDL_SCANCODE_P;
    mMesh->SetTextureIndex(1);
    
    
}
