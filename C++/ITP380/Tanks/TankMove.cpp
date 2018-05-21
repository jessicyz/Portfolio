//
//  TankMove.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/24/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "TankMove.h"
#include <SDL/SDL.h>
#include "Math.h"
#include "Block.h"
#include "Game.h"
//In TankMove:
//o Add an override of Update. It should just call MoveComponent::Update for now
//o Add an override of ProcessInput. Make it so that the following keys move the
//tank (remember, you need to use SetLinearSpeed or SetAngularSpeed):
// W – Move forward (speed 250)
// S – Move backwards (speed -250)
// A – Rotate counter-clockwise (speed Math::TwoPi)
// D – Rotate clockwise (speed -Math::TwoPi)


TankMove::TankMove(class Actor* owner):MoveComponent(owner)
{

}

void TankMove::ProcessInput(const Uint8 *keyState){
    if (keyState[mForwardKey]){
        SetForwardSpeed(250);
    }
    else if (keyState[mBackKey]){
        SetForwardSpeed(-250);
        
    }
    else if (keyState[mLeftKey]){
        SetAngularSpeed(Math::TwoPi);
    }
    else if (keyState[mRightKey]){
        SetAngularSpeed(-Math::TwoPi);
    }
    else{
        SetAngularSpeed(0);
        SetForwardSpeed(0);
    }
}


void TankMove::Update(float deltaTime){
    MoveComponent::Update(deltaTime);
    
    std::vector<Block*> blocks = mOwner->GetGame()->GetBlocks();
    for (int i = 0; i < blocks.size(); i++){
        if ( mOwner->GetCollision()->Intersect(blocks[i]->GetCollision() )){
            float dx1 = ( (mOwner->GetCollision()->GetMax().x) -
                         (blocks[i]->GetCollision()->GetMin().x) );
            float dx2 = ( (blocks[i]->GetCollision()->GetMax().x) -
                         (mOwner->GetCollision()->GetMin().x) );
            float dy1 = ( (mOwner->GetCollision()->GetMax().y) -
                         (blocks[i]->GetCollision()->GetMin().y) );
            float dy2 = ( (blocks[i]->GetCollision()->GetMax().y) -
                         (mOwner->GetCollision()->GetMin().y) );
            //if closer to left
            if (dx1 < dx2 && dx1 < dy1 && dx1 <dy2){
                mOwner->SetPosition(Vector3( blocks[i]->GetCollision()->GetMin().x - mOwner->GetCollision()->GetWidth()/2 , mOwner->GetPosition().y, 0  ) );
            }
            //if close to right
            else if (dx2 < dx1 && dx2 < dy1 && dx2 <dy2){
                mOwner->SetPosition(Vector3( blocks[i]->GetCollision()->GetMax().x + mOwner->GetCollision()->GetWidth()/2, mOwner->GetPosition().y,0  ) );
            }
            //if close to lower
            else if (dy2 < dx1 && dy2 < dy1 && dy2 <dx2){
                mOwner->SetPosition(Vector3( mOwner->GetPosition().x, blocks[i]->GetCollision()->GetMax().y + mOwner->GetCollision()->GetHeight()/2,0  ) );

            }
            //if above
            else{
                mOwner->SetPosition(Vector3( mOwner->GetPosition().x, blocks[i]->GetCollision()->GetMin().y - mOwner->GetCollision()->GetHeight()/2,0  ) );

            }
            
        }
        
    }
    
        
}

void TankMove::SetPlayerTwo(){
    
    //    O – Forward
    //    o L – Back
    //    o K – Rotate counter-clockwise
    //    o SDL_SCANCODE_SEMICOLON – Rotate clockwise
    mForwardKey = SDL_SCANCODE_O;
    mBackKey = SDL_SCANCODE_L;
    mLeftKey = SDL_SCANCODE_K;
    mRightKey = SDL_SCANCODE_SEMICOLON;
}
