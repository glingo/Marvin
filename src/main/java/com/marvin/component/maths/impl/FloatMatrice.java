package com.marvin.component.maths.impl;

import com.marvin.component.maths.IMatrice;
import com.marvin.component.maths.IVecteur;
import com.marvin.component.maths.Matrice;
import com.marvin.component.maths.exceptions.DimensionException;

public class FloatMatrice extends Matrice<Float> {

    /* Constructeurs */
    public FloatMatrice(FloatVecteur... cols) {
        super(cols, false);
    }

    public FloatMatrice(int m, int n, boolean transposed) {
        super(m, n, transposed);
    }

    public FloatMatrice(float[] vals, int m) {
        super(m);

        this.n = (m != 0 ? vals.length / m : 0);

        if (this.m * this.n != vals.length) {
            throw new IllegalArgumentException("Array length must be a multiple of m.");
        }

        values = new Float[this.m][this.n];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                set(i, j, vals[i + j * this.m]);
            }
        }
    }

    public FloatMatrice(int m, int n, float s) {
        super(m, n, s);
    }

    public FloatMatrice(int m, int n) {
        super(m, n);
    }

    /* Overriding */
    @Override
    public FloatMatrice copy() {
        FloatMatrice floatMatrice = new FloatMatrice(m, n, transposed);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floatMatrice.set(i, j, this.get(i, j));
            }
        }
        return floatMatrice;
    }

    @Override
    public FloatMatrice opposite() {
        for (int i = 0; i < m; i++) {
            this.getRow(i).opposite();
        }
        return this;
    }

    @Override
    public FloatMatrice transpose() {
        FloatMatrice floatMatrice = new FloatMatrice(n, m, transposed);
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColSize(); j++) {
                floatMatrice.set(j, i, this.get(i, j));
            }
        }
        return floatMatrice;
    }

    /* Static acces */
    public static FloatMatrice identity(int m, int n, float c) {
        FloatMatrice floatMatrice = new FloatMatrice(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floatMatrice.set(i, j, (i == j ? c : 0.0f));
            }
        }
        return floatMatrice;
    }

    public static FloatMatrice random(int m, int n) {
        FloatMatrice floatMatrice = new FloatMatrice(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floatMatrice.set(i, j, (float) Math.random());
            }
        }
        return floatMatrice;
    }

    public static FloatMatrice random(int m, int n, int coef) {
        FloatMatrice floatMatrice = new FloatMatrice(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floatMatrice.set(i, j, (float) Math.random() * coef);
            }
        }
        return floatMatrice;
    }

    public static FloatMatrice rotationX(double alpha) {
//		| 		1		,  		0		,		0		|
//		|		0		,  	cos(alpha)	,	-sin(alpha)	|
//		|		0		,	sin(alpha)	, 	cos(alpha)	|
        FloatMatrice floatMatrice = new FloatMatrice(3, 3);
        floatMatrice.set(0, 0, 1f);
        floatMatrice.set(1, 1, (float) Math.cos(alpha));
        floatMatrice.set(1, 2, (float) Math.sin(alpha));
        floatMatrice.set(2, 1, (float) -Math.sin(alpha));
        floatMatrice.set(2, 2, (float) Math.cos(alpha));
        return floatMatrice;
    }

    public static FloatMatrice rotationY(double alpha) {
//		| 	cos(alpha)	,  		0		,	sin(alpha)	|
//		|		0		,  		1		,		0		|
//		|	-sin(alpha)	,		0		, 	cos(alpha)	|
        FloatMatrice floatMatrice = new FloatMatrice(3, 3);
        floatMatrice.set(0, 0, (float) Math.cos(alpha));
        floatMatrice.set(0, 2, (float) -Math.sin(alpha));
        floatMatrice.set(1, 1, 1f);
        floatMatrice.set(2, 0, (float) Math.sin(alpha));
        floatMatrice.set(2, 2, (float) Math.cos(alpha));
        return floatMatrice;
    }

    public static FloatMatrice rotationZ(double alpha) {
//		| 	cos(alpha)	, -sin(alpha) 	,	0	|
//		|	sin(alpha)	,  cos(alpha)	,	0	|
//		|		0		,		0		, 	1	|
//		|		0		, 	 	0		,	0	|	
        FloatMatrice floatMatrice = new FloatMatrice(3, 3);
        floatMatrice.set(0, 0, (float) Math.cos(alpha));
        floatMatrice.set(0, 1, (float) -Math.sin(alpha));
        floatMatrice.set(1, 0, (float) Math.sin(alpha));
        floatMatrice.set(1, 1, (float) Math.cos(alpha));
        floatMatrice.set(2, 2, 1f);
        return floatMatrice;
    }

    public static FloatMatrice clipMatrix(float angle, float aspectRatio, float near, float far) {
//		| 		fov * ar  		,	  		0 				,			0				|
//		|     		0			,	 	   fov				,			0				|
//		|	 		0			,-(2*near*far)/(far-near)	, 			-1				|
//		|			0			,-(far+near)/(far-near)		,			0				|	
        FloatMatrice floatMatrice = new FloatMatrice(4, 4);
        float fov = 1.0f / (float) Math.tan(angle / 2.0);
        floatMatrice.set(0, 0, fov * aspectRatio);
        floatMatrice.set(1, 1, fov);
        floatMatrice.set(2, 2, -(far + near) / (far - near));
        floatMatrice.set(2, 3, -(2 * near * far) / (far - near));
        floatMatrice.set(3, 2, -1f);
        return floatMatrice;
    }

    /**
     * Multiplication standard de FloatMatrices.
     * @param a
     * @param b
     * @return la FloatMatrice a * b
     */
    public static FloatMatrice multiply(FloatMatrice a, FloatMatrice b) throws DimensionException {
        if (a.getColSize() != b.getRowSize()) {
            throw new DimensionException();
        }

        float somme;
        FloatMatrice c = new FloatMatrice(a.getColSize(), b.getRowSize());
        for (int i = 0; i < a.getRowSize(); i++) {
            for (int j = 0; j < b.getColSize(); j++) {
                somme = 0.0f;
                for (int k = 0; k < a.getColSize(); k++) {
                    somme += a.get(i, k) * b.get(k, j);
                }
                c.set(i, j, somme);
            }
        }
        return c;
    }

    /**
     * Multiplication standard de matrices.
     *
     * @param a
     * @param b
     * @return la matrice a * b
     */
    public static IMatrice<Float> multiply(IMatrice<Float> a, IMatrice<Float> b) throws DimensionException {
        // verification des dimensions 
        if (a.getColSize() != b.getRowSize()) {
            throw new DimensionException();
        }

        float somme;

        FloatMatrice c = new FloatMatrice(a.getColSize(), b.getRowSize());
        for (int i = 0; i < a.getRowSize(); i++) {
            for (int j = 0; j < b.getColSize(); j++) {
                somme = 0.0f;
                for (int k = 0; k < a.getColSize(); k++) {
                    somme += a.get(i, k) * b.get(k, j);
                }
                c.set(i, j, somme);
            }
        }
        return c;
    }

    public static IMatrice<Float> multiply(IMatrice<Float> a, FloatMatrice... m) throws DimensionException {
        IMatrice<Float> r = a.copy();
        for (FloatMatrice m1 : m) {
            r = FloatMatrice.multiply(r, m1);
        }
        return r;
    }

    public static IMatrice<Float> multiply(IMatrice<Float> a, FloatVecteur b) throws DimensionException {
        return multiply(a, new FloatMatrice(b));
    }

    public static IMatrice<Float> multiply(FloatVecteur a, IMatrice<Float> b) throws DimensionException {
        return multiply(new FloatMatrice(a), b);
    }

    public static IMatrice<Float> opposite(IMatrice<Float> m) {
        return m.copy().opposite();
    }

    public static FloatMatrice homogene(FloatMatrice solide) {
        return homogene(solide, 4, 0, 1);
    }

    public static FloatMatrice homogene(FloatMatrice solide, int size, float r, float c) {
        FloatMatrice m = new FloatMatrice(size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i < solide.getRowSize() && j < solide.getColSize()) {
                    m.set(i, j, solide.get(i, j));
                } else if (i > solide.getRowSize()) {
                    m.set(i, j, r);
                } else if (i < solide.getColSize()) {
                    m.set(i, j, c);
                }
            }
        }
        return m;
    }

    public static FloatMatrice diagonal(IVecteur<Float> d) {
        FloatMatrice m = new FloatMatrice(d.getDimension(), d.getDimension());
        m.setDiagonal(d);
        return null;
    }

    @Override
    public IVecteur<Float> getRow(int i) {
        int m = getRowSize();
        int n = getColSize();

        if ((i < 0) || (i >= m)) {
            throw new DimensionException();
        }

        if (!transposed) {
            return new FloatVecteur(values[i]);
        }

        IVecteur<Float> v = new FloatVecteur(n);
        for (int j = 0; j < n; j++) {
            v.set(j, values[j][i]);
        }

        return v;
    }

    @Override
    public IVecteur<Float> getColumn(int j) {
        int m = this.getRowSize();
        int n = this.getColSize();

        if ((j < 0) || (j >= n)) {
            throw new DimensionException();
        }

        FloatVecteur v = new FloatVecteur(m);
        for (int i = 0; i < m; i++) {
            v.set(i, values[i][j]);
        }

        return v;
    }

    @Override
    public IVecteur<Float> getDiagonal() {
        int m = this.getRowSize();
        int n = this.getColSize();
        int dimension = Math.min(m, n);

        FloatVecteur v = new FloatVecteur(dimension);
        for (int i = 0; i < dimension; i++) {
            v.set(i, this.get(i, i));
        }

        return v;
    }

}
