package com.marvin.component.maths.impl;

import com.marvin.component.maths.IVecteur;

public abstract class Solide {

    private FloatVecteur centre;

    private FloatMatrice points;

    public Solide(FloatVecteur centre, FloatVecteur... sommets) {
        points = new FloatMatrice(sommets);
        //	   		0     			 1 					 2					3
        // 0 [	   o.x		,		o.y			,		o.z 		,		1 		]
        // 1 [	o.x + a.x	, 	  o.y + a.x		, 	 o.z + a.z 		,		1 		]
        // 2 [	o.x + b.x	, 	  o.y + b.y		, 	 o.z + b.z		,		1 		]
        // 3 [	o.x + c.x	, 	  o.y + c.y		, 	 o.z + c.z		,		1 		]
        //   [	   ...		, 		 ...		,		...			,	   ...		]	
        this.centre = centre;
        for (int i = 0; i < sommets.length; i++) {
            IVecteur<Float> old = points.getColumn(i);
            points.setColumn(i, old.add(centre));
        }
    }

    public IVecteur<Float> getCentre() {
        return centre;
    }

    public void setCentre(FloatVecteur centre) {
        this.centre = centre;
    }

    /**
     * @return the points
     */
    public FloatMatrice getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(FloatMatrice points) {
        this.points = points;
    }
}
