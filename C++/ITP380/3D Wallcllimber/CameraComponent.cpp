//
//  CameraComponent.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/26/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>

#include "CameraComponent.h"
#include "Math.h"
#include "Actor.h"
#include "Game.h"
#include "Renderer.h"

CameraComponent::CameraComponent(class Actor* owner)
:Component(owner)
{
    
}

void CameraComponent::Update(float deltaTime){

    mPitchAngle += mPitchSpeed * deltaTime;
    if (mPitchAngle > Math::PiOver2/2){
        mPitchAngle = Math::PiOver2/2;
    }
    else if (mPitchAngle < -Math::PiOver2/2){
        mPitchAngle = -Math::PiOver2/2;
    }
    
    Matrix4 pitch = Matrix4::CreateRotationY( mPitchAngle );
    Matrix4 yaw = Matrix4::CreateRotationZ( mOwner->GetRotation() );
    
    
    
    Vector3 cameraForward = Vector3::Transform( Vector3(1,0,0) , pitch*yaw );
    
    Matrix4 lookAt = Matrix4::CreateLookAt(mOwner->GetPosition(),
             
                                           mOwner->GetPosition() + cameraForward,
                                           Vector3(0,0,1) );
    
    mOwner->GetGame()->GetRenderer()->SetViewMatrix(lookAt);
    
    
}
