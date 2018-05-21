//
//  CameraComponent.h
//  Game-mac
//
//  Created by Jess Zhu on 10/26/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#ifndef CameraComponent_h
#define CameraComponent_h
#include "Component.h"

class CameraComponent : public Component{
public:
    CameraComponent(class Actor* owner);
    void Update(float deltaTime) override;
    
//    void SetPitchAngle(float p) {mPitchAngle = p;}
    void SetPitchSpeed(float p) {mPitchSpeed = p;}
    
//    float GetPitchAngle() {return mPitchAngle;}
    float GetPitchSpeed() {return mPitchSpeed;}
private:
    float mPitchAngle = 0;
    float mPitchSpeed = 0;
    
};

#endif /* CameraComponent_h */
