package com.marvin.component.maths;

import java.util.ArrayList;
import com.marvin.component.maths.impl.FloatVecteur;
import com.marvin.component.maths.impl.Polyedre;

public abstract class Polygone extends Polyedre {

    public Polygone(FloatVecteur o, FloatVecteur... v) {
        super(o, v);
    }

    public Polygone(FloatVecteur o, ArrayList<FloatVecteur> v) {
        super(o, (FloatVecteur[]) v.toArray());
    }

}
