package com.marvin.component.maths.impl;

import com.marvin.component.maths.IMatrice;
import com.marvin.component.maths.IVecteur;
import com.marvin.component.maths.Matrice;
import com.marvin.component.maths.exceptions.DimensionException;

public class IntMatrice extends Matrice<Integer> {

    /* Constructeurs */
    public IntMatrice(IntVecteur... cols) {
        super(cols, false);
    }

    public IntMatrice(int m, int n, boolean transposed) {
        super(m, n, transposed);
    }

    public IntMatrice(int[] vals, int m) {
        super(m);

        this.n = (m != 0 ? vals.length / m : 0);

        if (this.m * this.n != vals.length) {
            throw new IllegalArgumentException("Array length must be a multiple of m.");
        }

        values = new Integer[this.m][this.n];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                set(i, j, vals[i + j * this.m]);
            }
        }
    }

    public IntMatrice(int m, int n, int s) {
        super(m, n, s);
    }

    public IntMatrice(int m, int n) {
        super(m, n);
    }

    /* Overriding */
    @Override
    public IntMatrice copy() {
        IntMatrice IntMatrice = new IntMatrice(m, n, transposed);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                IntMatrice.set(i, j, this.get(i, j));
            }
        }
        return IntMatrice;
    }

    @Override
    public IntMatrice opposite() {
        for (int i = 0; i < m; i++) {
            this.getRow(i).opposite();
        }
        return this;
    }

    @Override
    public IntMatrice transpose() {
        IntMatrice IntMatrice = new IntMatrice(n, m, transposed);
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColSize(); j++) {
                IntMatrice.set(j, i, this.get(i, j));
            }
        }
        return IntMatrice;
    }

    /* Static acces */
    public static IntMatrice identity(int m, int n, int c) {
        IntMatrice IntMatrice = new IntMatrice(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                IntMatrice.set(i, j, (i == j ? c : 0));
            }
        }
        return IntMatrice;
    }

    public static IntMatrice random(int m, int n) {
        IntMatrice IntMatrice = new IntMatrice(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                IntMatrice.set(i, j, (int) Math.random());
            }
        }
        return IntMatrice;
    }

    public static IntMatrice random(int m, int n, int coef) {
        IntMatrice IntMatrice = new IntMatrice(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                IntMatrice.set(i, j, (int) (Math.random() * coef));
            }
        }
        return IntMatrice;
    }

    /**
     * Multiplication standard de IntMatrices.
     *
     * @param a
     * @param b
     * @return la IntMatrice a * b
     */
    public static IntMatrice multiply(IntMatrice a, IntMatrice b) throws DimensionException {
        // verification des dimensions 
        if (a.getColSize() != b.getRowSize()) {
            throw new DimensionException();
        }

        int somme;

        IntMatrice c = new IntMatrice(a.getColSize(), b.getRowSize());
        for (int i = 0; i < a.getRowSize(); i++) {
            for (int j = 0; j < b.getColSize(); j++) {
                somme = 0;
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
    public static IMatrice<Integer> multiply(IMatrice<Integer> a, IMatrice<Integer> b) throws DimensionException {
        // verification des dimensions 
        if (a.getColSize() != b.getRowSize()) {
            throw new DimensionException();
        }

        int somme;

        IntMatrice c = new IntMatrice(a.getColSize(), b.getRowSize());
        for (int i = 0; i < a.getRowSize(); i++) {
            for (int j = 0; j < b.getColSize(); j++) {
                somme = 0;
                for (int k = 0; k < a.getColSize(); k++) {
                    somme += a.get(i, k) * b.get(k, j);
                }
                c.set(i, j, somme);
            }
        }
        return c;
    }

    public static IMatrice<Integer> multiply(IMatrice<Integer> a, IntMatrice... m) throws DimensionException {
        IMatrice<Integer> r = a.copy();
        for (IntMatrice m1 : m) {
            r = IntMatrice.multiply(r, m1);
        }
        return r;
    }

    public static IMatrice<Integer> multiply(IMatrice<Integer> a, IntVecteur b) throws DimensionException {
        return multiply(a, new IntMatrice(b));
    }

    public static IMatrice<Integer> multiply(IntVecteur a, IMatrice<Integer> b) throws DimensionException {
        return multiply(new IntMatrice(a), b);
    }

    public static IMatrice<Integer> opposite(IMatrice<Integer> m) {
        return m.copy().opposite();
    }

    public static IntMatrice homogene(IntMatrice solide) {
        return homogene(solide, 4, 0, 1);
    }

    public static IntMatrice homogene(IntMatrice solide, int size, int r, int c) {
        IntMatrice m = new IntMatrice(size, size);

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

    public static IntMatrice diagonal(IVecteur<Integer> d) {
        IntMatrice m = new IntMatrice(d.getDimension(), d.getDimension());
        m.setDiagonal(d);
        return null;
    }

    @Override
    public IVecteur<Integer> getRow(int i) {
        int m = getRowSize();
        int n = getColSize();

        if ((i < 0) || (i >= m)) {
            throw new DimensionException();
        }

        if (!transposed) {
            return new IntVecteur(values[i]);
        }

        IVecteur<Integer> v = new IntVecteur(n);
        for (int j = 0; j < n; j++) {
            v.set(j, values[j][i]);
        }

        return v;
    }

    @Override
    public IVecteur<Integer> getColumn(int j) {
        int m = this.getRowSize();
        int n = this.getColSize();

        if ((j < 0) || (j >= n)) {
            throw new DimensionException();
        }

        IntVecteur v = new IntVecteur(m);
        for (int i = 0; i < m; i++) {
            v.set(i, values[i][j]);
        }

        return v;
    }

    @Override
    public IVecteur<Integer> getDiagonal() {
        int m = this.getRowSize();
        int n = this.getColSize();
        int dimension = Math.min(m, n);

        IntVecteur v = new IntVecteur(dimension);
        for (int i = 0; i < dimension; i++) {
            v.set(i, this.get(i, i));
        }

        return v;
    }

}
