using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NextDay : MonoBehaviour
{
    public void calculatingNextDay(){
        Debug.Log("Next Day");
        for (int i = 0; i < Statistics.numPeople; i++){

            float curChanceToInteract = Random.Range(0f,1.0f);
            float chanceToInteract = 0;
            if (CrowdController.arr_people[i].healthState == 0){
                chanceToInteract = Statistics.amountHealthyInteraction;
            }
            else if (CrowdController.arr_people[i].healthState == 1){
                chanceToInteract = Statistics.amountSickInteraction;
            }
            else if (CrowdController.arr_people[i].healthState == 2){
                chanceToInteract = Statistics.amountCoronaInteraction;
            }

            while (curChanceToInteract < chanceToInteract){
                curChanceToInteract = Random.Range(0f,1.0f);
                int indexOfInteract = Random.Range(0,Statistics.numPeople);
                if (CrowdController.arr_people[indexOfInteract].hasVirus && !CrowdController.arr_people[indexOfInteract].isDead){ 
                    float chanceToCatch = Statistics.contagiousness;
                    if (CrowdController.arr_people[i].wearsMask){
                        chanceToInteract-=Statistics.wearMasksReduction;
                    }
                    if (CrowdController.arr_people[i].washesHands){
                        chanceToInteract -= Statistics.washHandsReduction;
                    }
                    float checkCaught = Random.Range(0.0f,1.0f);
                    if (checkCaught < chanceToCatch){
                        CrowdController.arr_people[i].hasVirus = true;
                        
                        Debug.Log("Got Virus: " + checkCaught);

                    }

                }
            }

            if (CrowdController.arr_people[i].hasVirus && ! CrowdController.arr_people[i].visibleSymptoms){
                if (Random.Range(0.0f,1.0f) < Statistics.chanceShowSymptoms){
                    CrowdController.arr_people[i].visibleSymptoms = true;
                }
            }

            if (CrowdController.arr_people[i].hasVirus && CrowdController.arr_people[i].visibleSymptoms){
                if (Random.Range(0.0f,1.0f) < Statistics.globalChanceOfGoingToTest){
                    //CrowdController.arr_people[i].healthState = 2;
                    CrowdController.arr_people[i].positiveVirus = true;
                }
            }

            if (CrowdController.arr_people[i].hasVirus){
                float chanceDie = Random.Range(0.0f,1.0f) ;
                if (chanceDie <= Statistics.deathRate){
                    //CrowdController.arr_people[i].healthState = 3;
                    CrowdController.arr_people[i].isDead = true;
                    Debug.Log("Dead: " + chanceDie);
                }
            }

            CrowdController.arr_people[i].updateSprite();
        }
    }
}
