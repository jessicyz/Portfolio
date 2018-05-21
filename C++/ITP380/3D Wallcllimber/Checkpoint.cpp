//
//  Checkpoint.cpp
//  Game-mac
//
//  Created by Jess Zhu on 11/16/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include "Checkpoint.h"

#include "Game.h"
#include "Renderer.h"
#include "Player.h"
#include "HUD.h"

Checkpoint::Checkpoint(Game* game)
:Actor(game){

    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/Checkpoint.gpmesh"));

    mCollision = new CollisionComponent(this);
    mCollision->SetSize(25.0f, 25.0f, 25.0f);
    mGame->AddCheckpoint(this);
    
    
    
}
Checkpoint::~Checkpoint(){
    mGame->RemoveCheckpoint();
}

void Checkpoint::UpdateActor(float deltaTime){
    

    
    if (isActive){
        if (mCollision->Intersect(mGame->GetPlayer() -> GetCollision())){
            mGame->GetPlayer()->SetRespawnPos(GetPosition());
            
            if(Mix_PlayChannel(-1, mGame->GetSound("Assets/Sounds/Checkpoint.wav"), 0)==-1) {
                printf("Mix_PlayChannel: %s\n",Mix_GetError());
             
            }
            
            
            SetState(EDead);
            
            
            if (mLevelString.size() != 0 ){
                mGame->SetNextLevel(mLevelString);
            }
            
            
            mGame->GetHud()->SetCheckpointText(mCheckpointText);
            
            
            
        }
        mMesh->SetTextureIndex(0);
    }
    
    if (!isActive){
        mMesh->SetTextureIndex(1);
    }


    
    
    
}
