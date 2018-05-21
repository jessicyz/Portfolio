//
//  TankMove.h
//  Game-mac
//
//  Created by Jess Zhu on 10/24/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef TankMove_h
#define TankMove_h
#include <SDL/SDL.h>


#include "MoveComponent.h"



class TankMove: public MoveComponent
{
public:
    TankMove(class Actor* owner);
    void Update(float deltaTime) override;
    void ProcessInput(const Uint8* keyState) override;
    void SetPlayerTwo();
    
private:
    SDL_Keycode mForwardKey = SDL_SCANCODE_W;
    SDL_Keycode mBackKey = SDL_SCANCODE_S;
    SDL_Keycode mLeftKey = SDL_SCANCODE_A;
    SDL_Keycode mRightKey = SDL_SCANCODE_D;
    
    
};


#endif /* TankMove_h */
