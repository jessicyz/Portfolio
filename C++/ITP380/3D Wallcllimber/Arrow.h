//
//  Arrow.h
//  Game-mac
//
//  Created by Jess Zhu on 11/21/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Arrow_h
#define Arrow_h

#include "Actor.h"

class Arrow: public Actor
{
public:
    Arrow(Game* game);
    ~Arrow();
    void UpdateActor(float deltaTime) override;
    
private:

};


#endif /* Arrow_h */
