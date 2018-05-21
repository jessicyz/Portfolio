//
//  Block.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/19/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "Block.h"
#include "Game.h"
#include "Renderer.h"
Block::Block(Game* game)
:Actor(game){

    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/Cube.gpmesh"));
    mScale = 64;
    mCollision = new CollisionComponent(this);
    mCollision->SetSize(1.0f, 1.0f, 1.0f);
    mGame->AddBlock(this);
    
}
Block::~Block(){
    mGame->RemoveBlock(this);
}
