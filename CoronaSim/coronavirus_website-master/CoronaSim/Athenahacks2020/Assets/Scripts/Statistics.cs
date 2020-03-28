using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public static class Statistics
{

    public static float contagiousness = .8f;

    public static float deathRate = .1f;
    public static float washHandsReduction = .5f;
    public static float wearMasksReduction = .25f;
    public static float coronaRecoveryRate = .2f;

    public static float fluRecoveryRate = .3f;

    public static float chanceShowSymptoms = .5f;

    public static float globalChanceOfGoingToTest = .3f;
    

    //temporarily hard coding
    public static int numPeople = 100;
    //public static List<Person> arr_people = new List<Person>();
    public static int numCoronaVirusPositive = 2;


    public static int curDay = 0;
    public static List<Day> arr_day = new List<Day>();
    public static float amountHealthyInteraction = 0.5f;
    public static float amountSickInteraction = 0.2f;
    public static float amountCoronaInteraction = 0.0f;


    public static int numPeopleWashHands = 50;
    public static int numPeopleWearMasks = 10;
    public static int numPeopleWashHandsAndWearMasks = 25;




    //If we have time, false negative and positive testing
    public static float falseNegativeChance = 0;
    public static float falsePositiveChance = 0;

    //If we have time, separate by household
    public static int maxNumPeoplePerHousehold = 6;
    public static int maxNumHouseholds = 20;
    public static int numHouseholds = 0;
    public static List<Household> arr_households = new List<Household>();
    static Statistics(){

    }


}
