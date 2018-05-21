//
//  Arrow.cpp
//  Game-mac
//
//  Created by Jess Zhu on 11/21/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include "Arrow.h"

#include "Game.h"
#include "Renderer.h"
#include "Checkpoint.h"
#include "Player.h"
Arrow::Arrow(Game* game)
:Actor(game){

    mMesh = new MeshComponent(this);
    mMesh->SetMesh(mGame->GetRenderer()->GetMesh("Assets/Arrow.gpmesh"));
    mScale = .15;
    mCollision = new CollisionComponent(this);
    mCollision->SetSize(1.0f, 1.0f, 1.0f);
    SDL_Log("%s", "Arrow Created");
//    mGame->AddBlock(this);
    
}
Arrow::~Arrow(){
//    mGame->RemoveBlock(this);
}

void Arrow::UpdateActor(float deltaTime){
//    To update the quaternion rotation of the arrow to point to the checkpoint, you need to:
//    o Make a vector from the player position to the active checkpoint, and normalize it
//    o Use the dot product/cross product between <1, 0, 0> and the player-to-checkpoint
//    vector to compute the angle of rotation and the axis of rotation
//    o Make sure you handle the case where the cross product length is near zero
//    o Once you have an axis/angle, use the Quaternion constructor that takes in a
//    Vector3 (for the axis) and a float (for the angle), and set mQuat to that!
//    o If the current active Checkpoint* is null, just set mQuat to Quaternion::Identity
//    o If you don’t understand this, consult the lecture notes
    
    
    
    
    
    
    if ( mGame->GetCheckpoints().size() == 0 || mGame->GetCheckpoints().front() == NULL){
        mQuat = Quaternion::Identity;
//        mRotation = 0;
        SDL_Log("%s", "End");

    }
    else{
        Vector3 norPlayerToActiveCP = mGame->GetCheckpoints().front()->GetPosition() - mGame->GetPlayer()->GetPosition();

        norPlayerToActiveCP.Normalize();
        //SDL_Log("%f %s %f %s %f", norPlayerToActiveCP.x, " ",norPlayerToActiveCP.y, " ", norPlayerToActiveCP.z );
        float angleOfRotation = acosf(Vector3::Dot( Vector3(1,0,0), norPlayerToActiveCP))  ;
        Vector3 axisOfRotation = Vector3::Cross( Vector3(1,0,0), norPlayerToActiveCP);
        axisOfRotation.Normalize();
        
        
        
        mQuat = Quaternion(axisOfRotation, angleOfRotation);
        
//        
//        mRotation = angleOfRotation;
//        
        if (Math::NearZero(axisOfRotation.Length())){
            mQuat= Quaternion::Identity;
//            mRotation = 0;
        }
        
        
        //SDL_Log("%f %s %f %s %f", axisOfRotation.x, " ",axisOfRotation.y, " ", axisOfRotation.z );
    }

    
    mPosition =  mGame->GetRenderer()->Unproject(Vector3(0,250,.1));
    
    
    
    

    
    
    
}
