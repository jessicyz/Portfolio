#include "Actor.h"
#include "Game.h"
#include "Component.h"
#include <algorithm>

Actor::Actor(Game* game)
	:mGame(game)
	,mState(EActive)
	,mPosition(Vector3::Zero)
	,mScale(1.0f)
	,mRotation(0.0f)
{
	// TODO
    
    mMove = nullptr;
    mGame->AddActor(this);
    mCollision = nullptr;
    //SDL_Log("%s", "here");
    mMesh = nullptr;
}

Actor::~Actor()
{
	// TODO
    //SDL_Log("%s", "Deleting Sprite");


    delete mMove;
    delete mCollision;
    
    delete mMesh;
    
    //SDL_Log("%s", "Removing Actor");
    mGame->RemoveActor(this);
}

void Actor::Update(float deltaTime)
{
	// TODO

//    

    
    
    if (mState == EActive){
        
        //update all relevant components and then call UpdateActor
        if (mCollision != nullptr){
            mCollision->Update(deltaTime);
        }
        
        if (mMove != nullptr){
            mMove -> Update(deltaTime);
        }
        
        if (mMesh != nullptr){
            mMove -> Update(deltaTime);
        }
        

        
        
        
        UpdateActor(deltaTime);
        
        

        Matrix4 scaleMatrix = Matrix4::CreateScale(mScale);
        Matrix4 rotationMatrix = Matrix4::CreateRotationZ(mRotation);
        Matrix4 positionMatrix = Matrix4::CreateTranslation(mPosition);
        mWorldTransform = scaleMatrix * rotationMatrix * positionMatrix;
        
        
    }
    //if (mState == EDead){
        //mGame->RemoveActor(this);
        //delete this;
    //}
}

void Actor::UpdateActor(float deltaTime)
{

    
    
}

void Actor::ProcessInput(const Uint8* keyState)
{
	// TODO
    if (mState == EActive){
        // call ProcessInput on all relevant components and then ActorInput
       
        if (mCollision != nullptr){
            mCollision->ProcessInput(keyState);
        }
        
        if (mMove != nullptr){
            mMove->ProcessInput(keyState);
        }
        
        if (mMesh != nullptr){
            mMesh->ProcessInput(keyState);
        }
        

        
        ActorInput(keyState);
        
    }
}

void Actor::ActorInput(const Uint8* keyState)
{
    
}




//Vector3 Actor::GetForward(){
//    return Vector3( cosf(mRotation), -sinf(mRotation), 0 );
//}


Vector3 Actor::GetForward() const
{
    return Vector3(Math::Cos(mRotation), Math::Sin(mRotation), 0.0f);
}

MoveComponent* Actor::GetMove(){
    return mMove;
}

void Actor::SetMove(MoveComponent* mc){
    mMove = mc;
}



CollisionComponent* Actor::GetCollision(){
    return mCollision;
}

void Actor::SetCollision(CollisionComponent* cc){
    mCollision = cc;
}
