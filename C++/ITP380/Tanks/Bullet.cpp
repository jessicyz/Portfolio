//
//  Bullet.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/25/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "Bullet.h"
#include "Game.h"
#include "Renderer.h"
#include "Block.h"
#include "Tank.h"
//• Create a Bullet class, it needs:
//o A MoveComponent (just a normal one)
//o A MeshComponent (use “Assets/Sphere.gpmesh”)
//o A CollisionComponent (use width, height, depth of 10.0f)
Bullet::Bullet(Game* game)
:Actor(game){
    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/Sphere.gpmesh"));
    mScale = .5;
    mMove = new MoveComponent(this);
    mCollision = new CollisionComponent(this);
    mCollision->SetSize(10.0f, 10.0f, 10.0f);
    mMove->SetForwardSpeed(400);
    
    
}
//If the Bullet collides with a Block, make the bullet (not the block) EDead,
//and don’t collide with anything else
// If the Bullet collides with one of the two tanks, and that tank is not the
//firing tank, call respawn on the Tank, and make the bullet EDead


void Bullet::UpdateActor(float deltaTime){
    std::vector<Block*> blocks = mGame->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        if ( GetCollision()->Intersect(blocks[i]->GetCollision() )){
            SetState(Actor::EDead);
            
        }
        
    }
    
    if (mCollision->Intersect(mGame->GetTank1()->GetCollision() )){
        if (mTank != mGame->GetTank1()){
            mGame->GetTank1()->Respawn();
            SetState(Actor::EDead);
        }
        
        
    }
    
    if (mCollision->Intersect( mGame->GetTank2()->GetCollision() ) ){
        if (mTank != mGame->GetTank2()){
            mGame->GetTank2()->Respawn();
            SetState(Actor::EDead);
            
        }
    }
    
    
    
    
}
