//
//  Block.h
//  Game-mac
//
//  Created by Jess Zhu on 10/19/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Block_h
#define Block_h
#include "Actor.h"

class Block: public Actor
{
public:
    Block(Game* game);
    ~Block();
    
private:
    MeshComponent* mMesh;
};

#endif /* Block_h */
