//
//  Bullet.h
//  Game-mac
//
//  Created by Jess Zhu on 10/25/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Bullet_h
#define Bullet_h



#include "Actor.h"
#include <SDL/SDL.h>

class Bullet: public Actor
{
public:
    Bullet(Game* game);
    void SetTank(class Tank* tank) {mTank = tank;}
    void UpdateActor(float deltaTime) override;
    
private:
    MeshComponent* mMesh;
    class Tank* mTank;
};


#endif /* Bullet_h */
