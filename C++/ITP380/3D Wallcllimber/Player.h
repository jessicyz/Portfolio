//
//  Player.h
//  Game-mac
//
//  Created by Jess Zhu on 10/26/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Player_h
#define Player_h
#include "Actor.h"





class Player: public Actor
{
public:

    Player(Game* game);
//    ~Player();
    Vector3 GetRespawnPos(){ return respawnPos; }
    void SetRespawnPos(Vector3 pos){ respawnPos = pos; }

    
private:
    Vector3 respawnPos;

};


#endif /* Player_h */
