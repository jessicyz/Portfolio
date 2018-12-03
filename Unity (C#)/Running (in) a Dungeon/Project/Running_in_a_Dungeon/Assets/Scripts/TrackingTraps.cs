using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TrackingTraps : MonoBehaviour {

    // Use this for initialization
    public GameObject portal;
    public List<GameObject> switches;
    public bool playedPortalSound = false;
    void Update()
    {
       if(AllTrapsOn())
        {
            portal.SetActive(true);
            if (!playedPortalSound){
                AudioPlayer.Instance.PlayPortalOnSFX();
                playedPortalSound = true;
            }
        }
        else{
            portal.SetActive(false);
            playedPortalSound = false;
        }
    }

    bool AllTrapsOn()
    {
        for (int i = 0; i < switches.Count; i++)
        {
            if (switches[i].GetComponent<Switch>().trapOn == false)
            {
                return false;
            }
        }
        
        return true;
    }
}
