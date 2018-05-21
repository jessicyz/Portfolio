#include "MoveComponent.h"
#include "Actor.h"

MoveComponent::MoveComponent(class Actor* owner)
:Component(owner)
,mAngularSpeed(0.0f)
,mForwardSpeed(0.0f)
{
	
}

void MoveComponent::Update(float deltaTime)
{
    float ang = mOwner->GetRotation() + mAngularSpeed * deltaTime;
    mOwner->SetRotation(ang);
    Vector3 pos = mOwner->GetPosition() + mForwardSpeed * deltaTime * mOwner->GetForward();
//    if (pos.x > 1024.0f)
//        pos.x -= 1024.0f;
//    else if (pos.x < 0.0f)
//        pos.x += 1024.0f;
//    if (pos.y > 768.0f)
//        pos.y -= 768.0f;
//    else if (pos.y < 0.0f)
//        pos.y += 768.0f;
    mOwner->SetPosition(pos);
    
    Vector3 strafePos = mOwner->GetPosition() + mStrafeSpeed * deltaTime * mOwner->GetRight();
    mOwner->SetPosition(strafePos);
    
}
