//
//  Player.cpp
//  Game-mac
//
//  Created by Jess Zhu on 10/26/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include <stdio.h>
#include "Player.h"
#include "Game.h"
#include "PlayerMove.h"
#include "CameraComponent.h"
Player::Player(Game* game)
:Actor(game){
    mMove = new PlayerMove(this);
    mCollision = new CollisionComponent(this);
    mCollision->SetSize(50, 125, 50);
    mCamera = new CameraComponent(this);
    
}
