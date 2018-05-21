//
//  Coin.h
//  Game-mac
//
//  Created by Jess Zhu on 11/24/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Coin_h
#define Coin_h
#include "Actor.h"

class Coin: public Actor
{
public:
    Coin(Game* game);
    ~Coin();
    void UpdateActor(float deltaTime) override;
    
private:
    float rotationSpeed = 10;
};

#endif /* Coin_h */
