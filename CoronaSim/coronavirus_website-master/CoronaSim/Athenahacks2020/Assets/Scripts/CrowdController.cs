using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CrowdController : MonoBehaviour
{
    public static List<Person> arr_people = new List<Person>();
    //public static Person[] arr_people = new Person[Statistics.numPeople];

    

    public Person prefab;

    // Start is called before the first frame update
    void Start()
    {
        updateNumPeople();
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }



    void updateNumPeople(){

        for (int i = arr_people.Count - 1; i >= 0; i--){
            Destroy(arr_people[i]);
            
        }
        arr_people.RemoveRange(0,arr_people.Count);



        
        int curNumCoronaVirus = 0;
        //temporarily hard coding
        for (int i = -5; i < 5; i++){
            for (int j = -7; j < 7; j++){
                Person newPerson = Instantiate(prefab, new Vector3(j*.7f+.2f, i*.8f, 0),Quaternion.identity);
                newPerson.healthState = 0;
                //Debug.Log(xPos + ", " + yPos);
                if (curNumCoronaVirus < Statistics.numCoronaVirusPositive){
                    curNumCoronaVirus++;
                    newPerson.hasVirus = true;
                    if (curNumCoronaVirus == 0){
                        newPerson.visibleSymptoms = true;
                        newPerson.healthState = 1;
                    }
                    Debug.Log("Added sick");
                }
                arr_people.Add(newPerson);
            }
        }

        int curNumWashHands = 0;
        int numToGiveUp = 100;
        while (numToGiveUp> 0 && curNumWashHands < Statistics.numPeopleWashHands){
            int handwasherIndex = Random.Range(0,100);
            if (!CrowdController.arr_people[handwasherIndex].washesHands){
                CrowdController.arr_people[handwasherIndex].washesHands = true;
                curNumWashHands++;
                Debug.Log("Added hand washer");
            }
            else{
                numToGiveUp--;
            }
        }

        int curNumWearMasks = 0;
        numToGiveUp = 100;
        while (numToGiveUp> 0 && curNumWearMasks < Statistics.numPeopleWashHands){
            int handwasherIndex = Random.Range(0,100);
            if (!CrowdController.arr_people[handwasherIndex].wearsMask && !CrowdController.arr_people[handwasherIndex].washesHands){
                CrowdController.arr_people[handwasherIndex].wearsMask = true;
                curNumWearMasks++;
            }
            else{
                numToGiveUp--;
            }
        }

        int curNumBoth = 0;
        numToGiveUp = 100;
        while (numToGiveUp> 0 && curNumBoth < Statistics.numPeopleWashHandsAndWearMasks){
            int handwasherIndex = Random.Range(0,100);
            if (!CrowdController.arr_people[handwasherIndex].wearsMask && !CrowdController.arr_people[handwasherIndex].washesHands){
                CrowdController.arr_people[handwasherIndex].washesHands = true;
                CrowdController.arr_people[handwasherIndex].wearsMask = true;
                curNumBoth++;
            }
            else{
                numToGiveUp--;
            }
        }

        /*
        for (int i = 0; i < Statistics.numPeople; i++){
            int numP = Statistics.numPeople;
            if (numP <= 0){
                numP = 1;
            }
            int xPos = -10 + i%20;//-Screen.width/2 + (i%20)*(Screen.width/20); //Screen.width - i*(Screen.width/(Mathf.Sqrt(numP)));
            int yPos = -5 + i%10;//-Screen.height/2 + (i/5) * (Screen.height/5); //Screen.height - i*(Screen.height/(Mathf.Sqrt(numP)));

            Person newPerson = Instantiate(prefab, new Vector3(xPos, yPos, 0),Quaternion.identity);
            newPerson.healthState = 0;
            Debug.Log(xPos + ", " + yPos);
            arr_people.Add(newPerson);
            
        }
        */

    }
    
}
