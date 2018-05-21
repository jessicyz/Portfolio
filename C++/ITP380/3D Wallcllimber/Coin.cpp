//
//  Coin.cpp
//  Game-mac
//
//  Created by Jess Zhu on 11/24/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include "Coin.h"
#include "Coin.h"
#include "Game.h"
#include "Renderer.h"
#include "Player.h"
#include "HUD.h"

Coin::Coin(Game* game)
:Actor(game){

    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/Coin.gpmesh"));
    mScale = 1;
    mCollision = new CollisionComponent(this);
    mCollision->SetSize(100, 100, 100);

    
}
Coin::~Coin(){

}

void Coin::UpdateActor(float deltaTime){
    
    
    mRotation += rotationSpeed*deltaTime;
    
    
    
    if (mCollision->Intersect(mGame->GetPlayer() -> GetCollision())){
        
        mGame->GetHud() -> AddCoin();
        
        SetState(EDead);
        if(Mix_PlayChannel(-1, mGame->GetSound("Assets/Sounds/Coin.wav"), 0)==-1) {
            printf("Mix_PlayChannel: %s\n",Mix_GetError());

        }
        
        
        
        
    }
    
    
}
