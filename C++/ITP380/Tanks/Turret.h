//
//  Turret.h
//  Game-mac
//
//  Created by Jess Zhu on 10/19/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Turret_h
#define Turret_h
#include "Actor.h"
#include <SDL/SDL.h>

class Turret: public Actor
{
public:
    Turret(Game* game);
    void ActorInput(const Uint8* keyState) override;
    void SetPlayerTwo();
    
private:
    MeshComponent* mMesh;
    SDL_Keycode mLeftKey = SDL_SCANCODE_Q;
    SDL_Keycode mRightKey = SDL_SCANCODE_E;
};


#endif /* Turret_h */
