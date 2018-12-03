using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class AudioPlayer : MonoBehaviour {

	public static AudioSource audioSource; 
	Scene curScene;

	private static AudioPlayer instance = null;
	public static AudioPlayer Instance {
		get { return instance; }
	}

	public AudioClip sfx_jump;
	public AudioClip sfx_death;
	public AudioClip sfx_collect;
	public AudioClip sfx_collect_2;
	public AudioClip sfx_switch;
	public AudioClip sfx_portalOn;
	public AudioClip sfx_enterPortal;
	public AudioClip sfx_laugh;

	public AudioClip sting_gameOver;
	public AudioClip sting_letter;
	public AudioClip sting_enterTutorial;

	public AudioClip sting_arrive;


	public AudioClip bgm_menu;
	public AudioClip bgm_intro_office;
	public AudioClip bgm_tutorial;
	public AudioClip bgm_intro_money;
	public AudioClip bgm_subway;

	public AudioClip bgm_forest_1;

	public AudioClip bgm_level;

	public AudioClip bgm_results;

	public AudioClip bgm_nothing;

	

	void Awake() {
		//singleton stuff. idk what it means
		
		if (instance != null && instance != this) {
			SwitchClip();
			Destroy(this.gameObject);
			return;
		} else {
			instance = this;
			
		}
		DontDestroyOnLoad(this.gameObject);
			audioSource = GetComponent<AudioSource>();
			SwitchClip();
		

		//arr_bgm[0] = (AudioClip)Resources.Load("Assets/Audio/bgm_temp.wav");//("Audio/bgm_temp");


	}

	void Update(){

	}


	public void SwitchClip(){
		// AudioSource childAudio = GetComponentInChildren<AudioSource>();
		// if (audioSource.clip != childAudio.clip){
		// 	Debug.Log("Different Clip");
		// 	audioSource.Stop();
		// 	audioSource.clip = childAudio.clip;
		// 	//childAudio.Play();
		// 	audioSource.Play();
		// }
		// // audioSource.Play();




		curScene  = SceneManager.GetActiveScene();
		Debug.Log(curScene.name);

		if (curScene.name == DungeonList.dungeon_results.floorName || curScene.name == DungeonList.dungeon_upgrademenu.floorName){


			if (CheckIfPlaying(bgm_results)) return;
			

		}
		else if (curScene.name == DungeonList.dungeon_startMenu.floorName){
			

			if (CheckIfPlaying(bgm_menu)) return;
			

		}
		//else if (curScene.name.Substring(0,7) == "intro_1"){
		else if (curScene.name == "intro_1" || curScene.name == "intro_1_1"){

			if (CheckIfPlaying(bgm_intro_office)) return;
		}
		else if (curScene.name == "intro_1_2"){
			PlaySting(sting_letter);
			if (CheckIfPlaying(bgm_nothing)) return;
		}
		else if (curScene.name == "intro_1_3" || curScene.name == "intro_1_4"){

			if (CheckIfPlaying(bgm_intro_money)) return;
		}
		else if (curScene.name == "intro_1_5"){

			if (CheckIfPlaying(bgm_subway)) return;
		}
		else if (curScene.name == "intro_1_6"){

			if (CheckIfPlaying(bgm_forest_1)) return;
		}
		else if (curScene.name == "tutorial_1"){
			PlaySting(sting_enterTutorial);
			if (CheckIfPlaying(bgm_tutorial)) return;
			
		}
		else if (curScene.name == "tutorial_2" || curScene.name == "tutorial_3"){
			if (CheckIfPlaying(bgm_tutorial)) return;
		}
		else if (curScene.name == "intro_2_2"){
			PlaySting(sting_arrive);
			if (CheckIfPlaying(bgm_intro_money)) return;
		}
		else if (curScene.name.Substring(0,7) == "intro_2"){

			if (CheckIfPlaying(bgm_intro_money)) return;
		}
		else if (curScene.name == "scene_gameOver"){
			PlayGameOverSting();
			if (CheckIfPlaying(bgm_nothing)) return;
			
		}
		else{
			if (CheckIfPlaying(bgm_level)) return;
		}
		// audioSource.Play();
	}

	public bool CheckIfPlaying(AudioClip newClip){
		if (audioSource.clip  == newClip) {
				Debug.Log("Playing " + audioSource.clip.name + " already");
				return true;
			}
		audioSource.clip = newClip;
		audioSource.Play();
		return false;
	}

	public void PlayJumpSFX(){
		audioSource.PlayOneShot(sfx_jump, 0.7F);
	}

	public void PlayDeathSFX(){
		audioSource.PlayOneShot(sfx_death, 1.0F);
	}

	public void PlayCoinSFX(){
		int x = Random.Range(0,2);
		if (x == 0){
			audioSource.PlayOneShot(sfx_collect_2, 1.0F);
		}
		else{
			audioSource.PlayOneShot(sfx_collect, 1.0F);
		}
	}
	public void PlayLaughSFX(){
		int x = Random.Range(0,10);
		if (x == 0){
			audioSource.PlayOneShot(sfx_laugh, 0.7F);
		}
		
	}

	public void PlaySwitchSFX(){
		audioSource.PlayOneShot(sfx_switch, 0.7F);
	}
	public void PlayPortalOnSFX(){
		
		audioSource.PlayOneShot(sfx_portalOn, 0.7F);
	}
	public void PlayEnterPortalSFX(){
		audioSource.PlayOneShot(sfx_enterPortal, 0.7F);
	}

	public void PlayGameOverSting(){
		audioSource.PlayOneShot(sting_gameOver, 1.0F);
	}

	public void PlaySting(AudioClip sting){
		audioSource.PlayOneShot(sting, 1.0F);
	}
	

}






	// bool playingDungeonBGM = false;
	// bool playingResultsBGM = false;

	// bool playingStartMenuBGM = false;
	
	// bool playingTutorialBGM = false;
	// bool playingGameOverMenuBGM = false;
	// bool playingIntro1BGM = false;
	// bool playingIntro2BGM = false;


	//const int maxNumClips = 6;

	
	//public AudioClip[] arr_audioClip_bgm = new AudioClip[maxNumClips];
	//public AudioSource[] arr_audioSources = new AudioSource[maxNumClips]; 



		
		// curScene  = SceneManager.GetActiveScene();
		// //Debug.Log(curScene.name);
		// //if it's on the results screen, stop the music
		// if (curScene.name == DungeonList.dungeon_results.floorName || curScene.name == DungeonList.dungeon_upgrademenu.floorName){
		// 	//Debug.Log("On results");
		// 	//audioSource.Stop();

		// 	if (!playingResultsBGM){
		// 		playingResultsBGM = true;
		// 		playingDungeonBGM = false;
		// 		playingStartMenuBGM = false;
		// 		playingGameOverMenuBGM = false;
		// 		if (arr_audioClip_bgm[1] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[1];
		// 		audioSource.Play();
		// 	}
			

		// }
		// else if (curScene.name == DungeonList.dungeon_startMenu.floorName){
			

		// 	if (!playingStartMenuBGM){
		// 		playingResultsBGM = false;
		// 		playingDungeonBGM = false;
		// 		playingStartMenuBGM = true;
		// 		playingGameOverMenuBGM = false;
		// 		if (arr_audioClip_bgm[2] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[2];
		// 		audioSource.Play();
		// 	}
		// }
		// else if (curScene.name == DungeonList.dungeon_gameOver.floorName){
		// 	if (!playingGameOverMenuBGM){
		// 		playingResultsBGM = false;
		// 		playingDungeonBGM = false;
		// 		playingStartMenuBGM = false;
		// 		playingGameOverMenuBGM = true;
		// 		if (arr_audioClip_bgm[3] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[3];
		// 		audioSource.Play();
		// 	}
		// }
		// else if (curScene.name.Substring(0,7) == "intro_1"){
		// 	if (!playingIntro1BGM){
		// 		playingResultsBGM = false;
		// 		playingDungeonBGM = false;
		// 		playingStartMenuBGM = false;
		// 		playingGameOverMenuBGM = false;
		// 		playingIntro1BGM = true;
		// 		playingIntro2BGM = false;
		// 		if (arr_audioClip_bgm[6] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[6];
		// 		audioSource.Play();
		// 	}
		// }
		// else if (curScene.name.Substring(0,7) == "intro_2"){
		// 	if (!playingIntro2BGM){
		// 		playingResultsBGM = false;
		// 		playingDungeonBGM = false;
		// 		playingStartMenuBGM = false;
		// 		playingGameOverMenuBGM = false;
		// 		playingIntro2BGM = true;
		// 		if (arr_audioClip_bgm[4] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[4];
		// 		audioSource.Play();
		// 	}
		// }
		// else if (curScene.name == "tutorial_1" || curScene.name == "tutorial_2" || curScene.name == "tutorial_3"){
		// 	if (!playingTutorialBGM){
		// 		playingResultsBGM = false;
		// 		playingDungeonBGM = false;
		// 		playingStartMenuBGM = false;
		// 		playingGameOverMenuBGM = false;
		// 		playingTutorialBGM = true;
		// 		if (arr_audioClip_bgm[5] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[5];
		// 		audioSource.Play();
		// 	}
		// }
		// else{
		// 	if (!playingDungeonBGM){
		// 		playingResultsBGM = false;
		// 		playingDungeonBGM = true;
		// 		playingStartMenuBGM = false;
		// 		playingGameOverMenuBGM = false;
		// 		if (arr_audioClip_bgm[0] == null) Debug.Log("bad audio");
		// 		audioSource.clip = arr_audioClip_bgm[0];
		// 		audioSource.Play();
		// 	}
		// }