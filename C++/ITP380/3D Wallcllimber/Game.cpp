//
//  Game.cpp
//  Game-mac
//
//  Created by Sanjay Madhav on 5/31/17.
//  Copyright © 2017 Sanjay Madhav. All rights reserved.
//

#include "Game.h"
#include <algorithm>
#include "Actor.h"
#include <fstream>
#include "Renderer.h"
#include "Block.h"
#include "LevelLoader.h"
#include "Checkpoint.h"

#include <SDL/SDL_ttf.h>
#include "HUD.h"

Game::Game()
:mIsRunning(true)
{
	
}

bool Game::Initialize()
{
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_AUDIO) != 0)
	{
		SDL_Log("Unable to initialize SDL: %s", SDL_GetError());
		return false;
	}

	// TODO: Create renderer

    
    mRenderer = new Renderer(this);
    if (!mRenderer->Initialize(1024, 768)){
        return false;
    }

	Mix_OpenAudio(44100, MIX_DEFAULT_FORMAT, 2, 2048);

    
    
    TTF_Init();
    
	LoadData();

	mTicksCount = SDL_GetTicks();
    
    SDL_SetRelativeMouseMode(SDL_TRUE); // Enable relative mouse mode
    SDL_GetRelativeMouseState(nullptr, nullptr); // Clear any saved values
    
	
	return true;
}

void Game::RunLoop()
{
	while (mIsRunning)
	{
		ProcessInput();
		UpdateGame();
		GenerateOutput();
        
        
        if (mNextLevel.size() != 0){
            LoadNextLevel();
        }
        
        
        
	}
}

void Game::ProcessInput()
{
	SDL_Event event;
	while (SDL_PollEvent(&event))
	{
		switch (event.type)
		{
			case SDL_QUIT:
				mIsRunning = false;
				break;
		}
	}
	
	const Uint8 *state = SDL_GetKeyboardState(NULL);
	if (state[SDL_SCANCODE_ESCAPE])
	{
		mIsRunning = false;
	}

	for (auto actor : mActors)
	{
		actor->ProcessInput(state);
	}
}

void Game::UpdateGame()
{
	// Compute delta time
	// Wait until 16ms has elapsed since last frame
	while (!SDL_TICKS_PASSED(SDL_GetTicks(), mTicksCount + 16))
		;

	float deltaTime = (SDL_GetTicks() - mTicksCount) / 1000.0f;
	if (deltaTime > 0.05f)
	{
		deltaTime = 0.05f;
	}
	mTicksCount = SDL_GetTicks();
	
	// Make copy of actor vector
	// (iterate over this in case new actors are created)
	std::vector<Actor*> copy = mActors;
	// Update all actors
	for (auto actor : copy)
	{
		actor->Update(deltaTime);
	}

	// Add any dead actors to a temp vector
	std::vector<Actor*> deadActors;
	for (auto actor : mActors)
	{
		if (actor->GetState() == Actor::EDead)
		{
			deadActors.emplace_back(actor);
		}
	}

	// Delete any of the dead actors (which will
	// remove them from mActors)
	for (auto actor : deadActors)
	{
		delete actor;
	}
    
    mHud->Update(deltaTime);
    
    
}

void Game::GenerateOutput()
{
	// TODO: tell renderer to draw
    mRenderer->Draw();
}

void Game::LoadData()
{
    

    LoadSound("Assets/Sounds/Checkpoint.wav");
    LoadSound("Assets/Sounds/Coin.wav");
    LoadSound("Assets/Sounds/Jump.wav");
    LoadSound("Assets/Sounds/Land.wav");
    LoadSound("Assets/Sounds/Music.ogg");
    LoadSound("Assets/Sounds/Running.wav");
    LoadSound("Assets/Sounds/Respawn.wav");
    
    
	//LoadLevel("Assets/Level.txt");
    LevelLoader::Load(this, "Assets/Tutorial.json");
    
    

    mRenderer->SetProjectionMatrix(Matrix4::CreatePerspectiveFOV(1.22, 1024, 768, 10, 10000));

    mRenderer->SetViewMatrix( Matrix4::CreateLookAt(Vector3::Zero, Vector3::UnitX, Vector3::UnitZ) );
    
    

    
    
    int music = Mix_PlayChannel(-1, GetSound("Assets/Sounds/Music.ogg"), -1);
    SDL_Log("%s %i","music: ", music );
    if(music==-1) {
        printf("Mix_PlayChannel: %s\n",Mix_GetError());
        // may be critical error, or maybe just no channels were free.

    }
    
    mHud = new HUD(this);
    
}

void Game::LoadLevel(const std::string& fileName)
{
	std::ifstream file(fileName);
	if (!file.is_open())
	{
		SDL_Log("Failed to load level: %s", fileName.c_str());
	}

	const float topLeftX = -512.0f + 32.0f;
	const float topLeftY = -384.0f + 32.0f;
	size_t row = 0;
	std::string line;
	while (!file.eof())
	{
		std::getline(file, line);
		for (size_t col = 0; col < line.size(); col++)
		{
			// Calculate position at this row/column
			Vector3 pos;
			pos.x = topLeftX + 64.0f * col;
			pos.y = topLeftY + 64.0f * row;

			if (line[col] == 'B')
			{
				// TODO: Create block
                Block* b = new Block(this);
                b->SetPosition(pos);

			}
			else if (line[col] == 'P')
			{
				// TODO: Create player 1

			}
			else if (line[col] == 'Q')
			{
				// TODO: Create player 2

			}
		}
		row++;
	}
    
    
    
    
}

void Game::UnloadData()
{
	// Delete actors
	// Because ~Actor calls RemoveActor, have to use a different style loop
	while (!mActors.empty())
	{
		delete mActors.back();
	}

	// Destroy textures
	for (auto i : mTextures)
	{
		SDL_DestroyTexture(i.second);
	}
	mTextures.clear();

	// Destroy sounds
	for (auto s : mSounds)
	{
		Mix_FreeChunk(s.second);
	}
	mSounds.clear();
}

void Game::LoadSound(const std::string& fileName)
{
	Mix_Chunk* chunk = Mix_LoadWAV(fileName.c_str());
	if (!chunk)
	{
		SDL_Log("Failed to load sound file %s", fileName.c_str());
		return;
	}

	mSounds.emplace(fileName, chunk);
}

Mix_Chunk* Game::GetSound(const std::string& fileName)
{
	Mix_Chunk* chunk = nullptr;
	auto iter = mSounds.find(fileName);
	if (iter != mSounds.end())
	{
		chunk = iter->second;
	}
	return chunk;
}

void Game::Shutdown()
{
	UnloadData();
	Mix_CloseAudio();
	// TODO: Delete renderer
    mRenderer->Shutdown();
    delete mRenderer;
	SDL_Quit();
}

void Game::AddActor(Actor* actor)
{
	mActors.emplace_back(actor);
}

void Game::RemoveActor(Actor* actor)
{
	auto iter = std::find(mActors.begin(), mActors.end(), actor);
	if (iter != mActors.end())
	{
		// Swap to end of vector and pop off (avoid erase copies)
		auto iter2 = mActors.end() - 1;
		std::iter_swap(iter, iter2);
		mActors.pop_back();
	}
}


void Game::AddBlock(class Block* block){
    mBlocks.push_back(block);
}
void Game::RemoveBlock(class Block* block){
    auto iter = std::find(mBlocks.begin(), mBlocks.end(), block);
    if (iter != mBlocks.end())
    {
        // Swap to end of vector and pop off (avoid erase copies)
        auto iter2 = mBlocks.end() - 1;
        std::iter_swap(iter, iter2);
        mBlocks.pop_back();
    }
}

std::vector<class Block*> Game::GetBlocks(){
    return mBlocks;
}



void Game::AddCheckpoint(class Checkpoint *cp){
    mCheckpoints.push(cp);
    if (mCheckpoints.size() == 1){
        cp->SetActive(true);
    }
    
    
    
}
void Game::RemoveCheckpoint(){
    mCheckpoints.pop();
    
    if (mCheckpoints.size() != 0){
        mCheckpoints.front()->SetActive(true);
    }
}

std::queue<class Checkpoint*> Game::GetCheckpoints(){
    return mCheckpoints;
}



void Game::LoadNextLevel(){
    

    
    while (!mActors.empty())
    {
        delete mActors.back();
    }
    
    while (mCheckpoints.size() != 0){
        RemoveCheckpoint();
    }
    
    LevelLoader::Load(this, mNextLevel);
    
    mNextLevel.clear();
    
    
    
}
