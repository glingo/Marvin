package com.marvin.component.maths.impl;

import java.util.Arrays;
import com.marvin.component.maths.IMatrice;
import com.marvin.component.maths.IVecteur;
import com.marvin.component.maths.Vecteur;
import com.marvin.component.maths.exceptions.DimensionException;

public class FloatVecteur extends Vecteur<Float> {

    public FloatVecteur(int capacity) {
        super(capacity);
    }

    public FloatVecteur(Float... values) {
        super(values);
    }

    @Override
    public FloatVecteur copy() {
        int i = 0;
        int len = this.getDimension();

        FloatVecteur v = new FloatVecteur(len);

        for (; i < len; i++) {
            v.set(i, this.get(i));
        }

        return v;
    }

    /**
     * Le produit vectoriel sert � calculer le FloatVecteur perpendiculaire �
     * deux autres FloatVecteurs. Le produit vectoriel se dit cross product
     * (produit crois�) en anglais. Le calcul du produit vectoriel se fait �
     * partir de deux FloatVecteurs et permet d'obtenir un autre FloatVecteur.
     * Ce FloatVecteur obtenu est perpendiculaire aux deux autres
     * FloatVecteurs... tout comme la normale d'une face est perpendiculaire �
     * la face elle m�me.
     * @param v
     * @return 
     */
    @Override
    public FloatVecteur cross(IVecteur<Float> v) {
        if ((this.getDimension() != 3) || (v.getDimension() != 3)) {
            throw new DimensionException(
                    "Le cross product ne peut �tre effectu�e que si les deux FloatVecteurs sont de dimensions 3");
        }

        return new FloatVecteur(
                (this.get(1) * v.get(2)) - (this.get(2) * v.get(1)),
                (this.get(2) * v.get(0)) - (this.get(0) * v.get(2)),
                (this.get(0) * v.get(1)) - (this.get(1) * v.get(0))
        );
    }

    public static float dot(FloatVecteur a, FloatVecteur b) {

        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "Le produit scalaire ne peut �tre effectu�e que si les deux FloatVecteurs ont les m�mes dimensions");
        }

        FloatVecteur r = a.copy();
        return r.dot(b);
    }

    public static FloatVecteur mult(FloatVecteur a, float k) {
        FloatVecteur r = a.copy();
        r.mult(k);

        return r;
    }

    public static FloatVecteur extend(FloatVecteur a, float... v) {
        FloatVecteur r = new FloatVecteur(v.length + a.getDimension());
        int j = 0;
        for (int i = 0; i < r.getDimension(); i++) {
            if (i < a.getDimension()) {
                r.set(i, a.get(i));
            } else {
                r.set(i, v[j++]);
            }
        }
        return r;
    }

    /**
     * Notez que si vous �changez les FloatVecteurs a et b dans les calculs, le
     * FloatVecteur obtenu sera exactement le FloatVecteur oppos� � celui que
     * vous auriez obtenu sans �changer a et b.
     *
     * @param a
     * @param b
     * @return
     */
    public static FloatVecteur cross(FloatVecteur a, FloatVecteur b) {
        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "La multiplication ne peut �tre effectu�e que si les deux FloatVecteurs ont les m�mes dimensions");
        }

        FloatVecteur r = a.copy();
        return r.cross(b);
    }

    public static IVecteur<Float> opposite(FloatVecteur a) {
        FloatVecteur r = a.copy();
        return r.opposite();
    }

    public static FloatVecteur add(FloatVecteur a, FloatVecteur b) {

        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "L'addition ne peut �tre effectu�e que si les deux FloatVecteurs ont les m�mes dimensions");
        }

        FloatVecteur r = a.copy();
        return (FloatVecteur) r.add(b);
    }

    public static FloatVecteur sub(FloatVecteur a, FloatVecteur b) {
        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "La substraction ne peut �tre effectu�e que si les deux FloatVecteurs ont les m�mes dimensions");
        }

        FloatVecteur r = a.copy();
        r.sub(b);

        return r;
    }

    public static IVecteur<Float> transform(FloatVecteur v, IMatrice<Float> m) {
        return v.transform(m);
    }

    @Override
    public IVecteur<Float> transform(IMatrice<Float> m) {
        return FloatMatrice.multiply(this, m).getRow(0);
    }

    @Override
    public IVecteur<Float> homogene(int size, Float c) {
        FloatVecteur v = new FloatVecteur(this.getDimension() + (size - this.getDimension()));
        for (int i = 0; i < size; i++) {
            if (i < getDimension()) {
                v.set(i, this.get(i));
            } else {
                v.set(i, c);
            }
        }
        return v;
    }

    @Override
    public int compareTo(IVecteur<Float> o) {
        if (this.getDimension() != o.getDimension()) {
            throw new DimensionException();
        }
        return (int) (this.length() - o.length());
    }

    @Override
    public IVecteur<Float> add(IVecteur<Float> v) {
        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) + v.get(i));
        }

        return this;
    }

    @Override
    public IVecteur<Float> truncate(int dim) {

        if (this.getDimension() < dim) {
            throw new DimensionException();
        }

        FloatVecteur v = new FloatVecteur(dim);

        int i = 0;
        int len = v.getDimension();

        for (; i < len; i++) {
            v.set(i, this.get(i));
        }

        return v;
    }

    @Override
    public void sub(IVecteur<Float> v) {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) - v.get(i));
        }

    }

    @Override
    public IVecteur<Float> mult(Float n) {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) * n);
        }

        return this;
    }

    @Override
    public void div(Float n) {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) / n);
        }

    }

    @Override
    public Float dot(IVecteur<Float> vecteur) {

        int i = 0;
        int len = this.getDimension();
        float dot = 0;

        for (; i < len; i++) {
            dot += this.get(i) * this.get(i);
        }

        return dot;
    }

    @Override
    public IVecteur<Float> opposite() {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, -this.get(i));
        }

        return this;
    }

    @Override
    public Float length() {
        return (float) Math.sqrt(this.dot(this));
    }

    @Override
    public IVecteur<Float> normalize() {
        float len = this.length();
        if (len > 0) {
            this.div(len);
        }

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FloatVecteur other = (FloatVecteur) obj;
        if (getDimension() != other.getDimension()) {
            return false;
        }
        return Arrays.equals(getValues(), other.getValues());
    }
}
