package com.marvin.component.maths;

/**
 * Interface des Vecteurs.
 * 
 * @author f.cailly
 *
 */
public interface IVecteur<T> extends Comparable<IVecteur<T>>{
	
	/* Object operations */
	
	IVecteur<T> copy();
	
	/* Additions */
	
	IVecteur<T> add(IVecteur<T> vecteur);
	
	/* Soustractions */
	
	void sub(IVecteur<T> vecteur);
	
	/* Multiplications */
	
	IVecteur<T> mult(T n);

	/* Divisions */
	
	void div(T n);
	
	/* Produits */
	
	T dot(IVecteur<T> vecteur);
	
	IVecteur<T> cross(IVecteur<T> vecteur);
	
	/* Vector operation */
	
	T length();
	
	IVecteur<T> normalize();
	
	IVecteur<T> opposite();
	
	/* Getter and Setter */
	
	int getDimension();
	
	T get(int index);
	
	void set(int index, T val);

	IVecteur<T> transform(IMatrice<T> m);

	IVecteur<T> homogene(int size, T c);

	IVecteur<T> truncate(int dim);

}
