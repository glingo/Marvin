package com.marvin.component.maths;

import java.util.ArrayList;
import com.marvin.component.maths.exceptions.DimensionException;

public abstract class Matrice<T> implements IMatrice<T> {

    protected int m = 0;

    protected int n = 0;

    protected boolean transposed = false;

    protected T[][] values;

    public Matrice(int m) {
        this.m = m;
    }

    public Matrice(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public Matrice(T[][] vals, int m, int n) {
        this(m, n);
        this.values = vals;
    }

    public Matrice(int m, int n, boolean transposed) {
        this(m, n);
        this.transposed = transposed;
    }

    public Matrice(int m, int n, T s) {
        this(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                values[i][j] = s;
            }
        }
    }

    public Matrice(T[][] vals) {

        if (vals == null) {
            throw new IllegalArgumentException("Argument should not be null.");
        }

        this.m = vals.length;
        this.n = vals[0].length;

        for (int i = 0; i < this.m; i++) {
            if (vals[i].length != this.n) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }

        this.values = vals;
    }

    public Matrice(ArrayList<IVecteur<T>> rows, boolean transposed) {
        if (rows == null) {
            rows = new ArrayList<>();
        }

        this.m = this.n = rows.size();
        this.transposed = transposed;

        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                set(i, j, rows.get(i).get(j));
            }
        }

    }

    public Matrice(IVecteur<T>[] cols, boolean b) {
        if (cols == null) {
            throw new IllegalArgumentException("Argument should not be null.");
        }

        this.m = cols[0].getDimension();
        this.n = cols.length;
        this.transposed = b;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                set(j, i, cols[i].get(j));
            }
        }
    }

    @Override
    public void setRow(int i, IVecteur<T> row) {
        int m = getRowSize();
        int n = getColSize();

        if ((i < 0) || (i >= m)) {
            throw new DimensionException();
        }

        for (int j = 0; j < n; j++) {
            this.set(i, j, row.get(j));
        }
    }

    @Override
    public void setDiagonal(IVecteur<T> row) {
        int m = getRowSize();
        int n = getColSize();

        if ((m != n) || (n < row.getDimension())) {
            throw new DimensionException();
        }

        for (int i = 0; i < n; i++) {
            this.set(i, i, row.get(i));
        }
    }

    @Override
    public void setColumn(int j, IVecteur<T> column) {
        int m = getRowSize();
        int n = getColSize();

        if ((j < 0) || (j >= n)) {
            throw new DimensionException();
        }

        for (int i = 0; i < m; i++) {
            this.set(i, j, column.get(i));
        }
    }

    @Override
    public String toString() {
        final int m = this.getRowSize();
        String text = "";
        for (int i = 0; i < m; i++) {
            text += "|" + getRow(i) + "|";
            if (i != m - 1) {
                text += "\n";
            }
        }

        return text;
    }

    @Override
    public int getColSize() {
        return transposed ? m : n;
    }

    @Override
    public int getRowSize() {
        return transposed ? n : m;
    }

    @Override
    public T get(int i, int j) {
        return transposed ? values[j][i] : values[i][j];
    }

    @Override
    public void set(int i, int j, T s) {
        values[i][j] = s;
    }

    @Override
    public abstract IMatrice<T> copy();

    @Override
    public abstract IMatrice<T> opposite();

}
