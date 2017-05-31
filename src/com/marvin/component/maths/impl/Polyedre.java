package com.marvin.component.maths.impl;

public class Polyedre extends Solide {

//	 	 f _______________  g
//	 	  /| 			 /|
//		 / |			/ |
//	 a  /__|___________/ b|
//	   |   |  	  	   |  |
//	   |   |  	 	   |  |
//     |   |  	 o 	   |  |
//     |   |___________|__|
//	   |  / e		   |  / h
//	   | /			   | /
//	   |/______________|/
//	 d		   		   c
    public Polyedre(FloatVecteur c, FloatVecteur... sommets) {
        super(c, sommets);
    }

    public static Solide cube(FloatVecteur o, float w) {

        FloatVecteur a = new FloatVecteur(w, w, w);
        FloatVecteur b = new FloatVecteur(-w, -w, w);
        FloatVecteur c = new FloatVecteur(w, -w, w);
        FloatVecteur d = new FloatVecteur(-w, w, w);

        FloatVecteur h = (FloatVecteur) FloatVecteur.opposite(c);
        FloatVecteur f = (FloatVecteur) FloatVecteur.opposite(b);
        FloatVecteur e = (FloatVecteur) FloatVecteur.opposite(a);
        FloatVecteur g = (FloatVecteur) FloatVecteur.opposite(d);

        return new Polyedre(o, a, b, c, d, e, f, g, h);
    }

}
