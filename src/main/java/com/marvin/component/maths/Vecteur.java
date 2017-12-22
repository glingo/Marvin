package com.marvin.component.maths;

import java.util.Arrays;

public abstract class Vecteur<T> implements IVecteur<T> {

    /**
     * Holds the dimension.
     */
    private int dimension;

    /**
     * Holds the values.
     */
    private T[] values;

    public Vecteur(int capacity) {
        super();
        this.setDimension(capacity);
    }

    public Vecteur(T[] values) {
        this.setValues(values);
        this.setDimension(values.length);
    }

    @Override
    public T get(int index) {
        if (index >= dimension) {
            throw new IndexOutOfBoundsException();
        }

        return values[index];
    }

    @Override
    public void set(int index, T val) {
        if (index >= dimension) {
            throw new IndexOutOfBoundsException();
        }

        values[index] = val;
    }

    @Override
    public abstract IVecteur<T> add(IVecteur<T> v);

    @Override
    public abstract IVecteur<T> truncate(int dim);

    @Override
    public abstract void sub(IVecteur<T> v);

    @Override
    public abstract IVecteur<T> mult(T n);

    @Override
    public abstract void div(T n);

    @Override
    public abstract T dot(IVecteur<T> vecteur);

    @Override
    public abstract IVecteur<T> opposite();

    @Override
    public abstract T length();

    @Override
    public abstract IVecteur<T> normalize();

    @Override
    public abstract IVecteur<T> transform(IMatrice<T> m);

    @Override
    public abstract IVecteur<T> homogene(int size, T c);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int compareTo(IVecteur<T> o);

    @Override
    public String toString() {
        int i = 0;
        int len = this.getDimension();
        String text = " {";

        for (; i < len; i++) {
            text += this.get(i);

            if (i + 1 < len) {
                text += ", ";
            }
        }

        text += "} ";

        return text;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dimension;
        result = prime * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the values
     */
    public T[] getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(T[] values) {
        this.values = values;
    }
}
