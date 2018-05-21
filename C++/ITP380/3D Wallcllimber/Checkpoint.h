//
//  Checkpoint.hpp
//  Game-mac
//
//  Created by Jess Zhu on 11/16/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef Checkpoint_h
#define Checkpoint_h

#include <stdio.h>

#include "Actor.h"
#include <string>

class Checkpoint: public Actor
{
public:
    Checkpoint(Game* game);
    ~Checkpoint();
    
    void UpdateActor(float deltaTime) override;
    void SetActive(bool a) { isActive = a;}
    void SetLevelString(std::string s) {mLevelString = s;}
    void SetCheckpointText(std::string s) {mCheckpointText = s;}
    
    
private:

    bool isActive = false;
    std::string mLevelString;
    
    std::string mCheckpointText;
};

#endif /* Checkpoint_h */
