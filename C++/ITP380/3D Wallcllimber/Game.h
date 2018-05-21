#pragma once
#include "SDL/SDL.h"
#include "SDL/SDL_mixer.h"
#include <unordered_map>
#include <string>
#include <vector>
#include "Math.h"
#include <queue>

class Game
{
public:
	Game();
	bool Initialize();
	void RunLoop();
	void Shutdown();

	void AddActor(class Actor* actor);
	void RemoveActor(class Actor* actor);

	void LoadSound(const std::string& fileName);
	Mix_Chunk* GetSound(const std::string& fileName);

	void LoadLevel(const std::string& fileName);

	class Renderer* GetRenderer() {	return mRenderer; }
    
    void AddBlock(class Block* block);
    void RemoveBlock(class Block* block);
    
    std::vector<class Block*> GetBlocks();
    
    void AddCheckpoint (class Checkpoint* cp);
    void RemoveCheckpoint ();
    
    std::queue<class Checkpoint*> GetCheckpoints();
    
    void SetPlayer(class Player* p){player = p;}
    class Player* GetPlayer(){ return player; }
    
    void SetNextLevel( std::string s ){mNextLevel = s; }
    void LoadNextLevel();
    
    class HUD* GetHud(){return mHud;}
    

    
    
private:
	void ProcessInput();
	void UpdateGame();
	void GenerateOutput();
	void LoadData();
	void UnloadData();

	// Map of textures loaded
	std::unordered_map<std::string, SDL_Texture*> mTextures;
	std::unordered_map<std::string, Mix_Chunk*> mSounds;

	// All the actors in the game
	std::vector<class Actor*> mActors;

	class Renderer* mRenderer;

	Uint32 mTicksCount;
	bool mIsRunning;
    

    std::vector<class Block*> mBlocks;
    
    std::queue<class Checkpoint*> mCheckpoints;
    
    class Player* player;
    
    std::string mNextLevel;
    
    class HUD* mHud;

    
    

};
