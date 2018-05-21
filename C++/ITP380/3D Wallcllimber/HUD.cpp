#include "HUD.h"
#include "Texture.h"
#include "Shader.h"
#include "Game.h"
#include "Renderer.h"
#include "Font.h"
#include <sstream>
#include <iomanip>

HUD::HUD(Game* game)
	:mGame(game)
	,mFont(nullptr)
{
	// Load font
	mFont = new Font();
	mFont->Load("Assets/Inconsolata-Regular.ttf");
    mTimerText = mFont->RenderText("00:00.00");
    mCoinCount = mFont->RenderText( "00/55" );
    mCheckpointText = mFont->RenderText("");

    
}

HUD::~HUD()
{
	// Get rid of font
	if (mFont)
	{
		mFont->Unload();
		delete mFont;
	}
}

void HUD::Update(float deltaTime)
{
	// TODO
//    

    time += deltaTime;
    mTimerText->Unload();
    delete mTimerText;
    //SDL_Log("%f", time);

    
    int minutes = time/60;
    int seconds = (int)time%60;
    int milliseconds = (time - minutes*60 - seconds) * 100;
    
    std::stringstream ssMin;
    ssMin << std::setw(2) << std::setfill('0')<<minutes;
    std::string minuteString = ssMin.str();
    
    std::stringstream ssSec;
    ssSec << std::setw(2) << std::setfill('0')<<seconds;
    std::string secondString = ssSec.str();
    
    std::stringstream ssMil;
    ssMil << std::setw(2) << std::setfill('0')<<milliseconds;
    std::string millisecondString = ssMil.str();
    
    
    std::string text = (minuteString + "::" + secondString + "." + millisecondString);
    
    mTimerText = mFont->RenderText(text);
    
    
    
    timeSinceLastMessage += deltaTime;
    
    
    
    
}

void HUD::Draw(Shader* shader)
{
	// TODO
    SDL_Log("%s", "set");
    DrawTexture(shader, mTimerText, Vector2(-420.0f, -325.0f));
    DrawTexture(shader, mCoinCount, Vector2(-420.0f, -355.0f));
    
    if (timeSinceLastMessage <= 5){
        DrawTexture(shader, mCheckpointText, Vector2::Zero);
    }
    
    
    
}

void HUD::DrawTexture(class Shader* shader, class Texture* texture,
				 const Vector2& offset, float scale)
{
	// Scale the quad by the width/height of texture
	Matrix4 scaleMat = Matrix4::CreateScale(
		static_cast<float>(texture->GetWidth()) * scale,
		static_cast<float>(texture->GetHeight()) * scale,
		1.0f);
	// Translate to position on screen
	Matrix4 transMat = Matrix4::CreateTranslation(
		Vector3(offset.x, offset.y, 0.0f));	
	// Set world transform
	Matrix4 world = scaleMat * transMat;
	shader->SetMatrixUniform("uWorldTransform", world);
	// Set current texture
	texture->SetActive();
	// Draw quad
	glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, nullptr);
    
    
    
}


void HUD::AddCoin(){
    coins++;
    
    mCoinCount->Unload();
    delete mCoinCount;
    
    
    
    std::stringstream ssCoin;
    ssCoin << std::setw(2) << std::setfill('0')<<coins;
    std::string coinString = ssCoin.str();
    
    
    std::string text = coinString + "/55";
    mCoinCount = mFont->RenderText( text );
    
    
    
}



void HUD::SetCheckpointText(std::string s){
    timeSinceLastMessage = 0;
    
    if (mCheckpointText != nullptr){
        mCheckpointText->Unload();
        delete mCheckpointText;
    }
    
    
    mCheckpointText = mFont->RenderText(s);
    
    
}
