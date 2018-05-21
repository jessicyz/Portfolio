//
//  Tank.h
//  Game-mac
//
//  Created by Jess Zhu on 10/19/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Tank_h
#define Tank_h
#include "Actor.h"
#include <SDL/SDL.h>


class Tank: public Actor
{
public:
    Tank(Game* game);
    void UpdateActor(float deltaTime) override;
    void SetPlayerTwo();
    void Fire();
    void Respawn();
    void SetInitialPos(Vector3 pos);
    void ActorInput(const Uint8* keyState) override;
    
    
private:
    MeshComponent* mMesh;
    class Turret* mTurret;
    Vector3 mInitialPos;
    SDL_Keycode mFireKey = SDL_SCANCODE_LSHIFT;
    bool firing = false;

};

#endif /* Tank_h */
