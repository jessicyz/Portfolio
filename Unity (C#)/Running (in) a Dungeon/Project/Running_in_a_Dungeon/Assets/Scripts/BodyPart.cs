using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BodyPart : MonoBehaviour {

    private SpriteRenderer spriteRenderer;
    //because renderer doesn't exist here
    private Renderer r;
    //this too
    
    private Color start;
    private Color end;
    private float t = 0.0f;
    public float timeMultiplier = 1.0f;

	// Use this for initialization
	protected void Start () {
        spriteRenderer = GetComponent<SpriteRenderer>();
        r = GetComponent<Renderer>();
        
        start = spriteRenderer.color;
        end = new Color(start.r, start.g, start.b, 0.0f);
	}
	
	// Update is called once per frame
	protected void Update () {
        t += Time.deltaTime * timeMultiplier;

        r.material.color = Color.Lerp(start, end, t / 2);
        if (r.material.color.a <= 0.0f)
        {
            Destroy(gameObject);
        }
	}
}
