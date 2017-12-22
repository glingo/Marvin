package com.marvin.component.maths;

public interface IMatrice<T> {

    /* Object operations */
    IMatrice<T> copy();

    IVecteur<T> getDiagonal();

    IVecteur<T> getRow(int i);

    void setRow(int i, IVecteur<T> row);

    void setColumn(int j, IVecteur<T> column);

    IVecteur<T> getColumn(int j);

    IMatrice<T> opposite();

    IMatrice<T> transpose();

    T get(int i, int j);

    void set(int i, int j, T s);

    int getColSize();

    int getRowSize();

    void setDiagonal(IVecteur<T> d);

}
