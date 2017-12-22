package com.marvin.component.maths.impl;

import java.util.Arrays;
import com.marvin.component.maths.IMatrice;
import com.marvin.component.maths.IVecteur;
import com.marvin.component.maths.Vecteur;
import com.marvin.component.maths.exceptions.DimensionException;

public class IntVecteur extends Vecteur<Integer> {

    public IntVecteur(int capacity) {
        super(capacity);
    }

    public IntVecteur(Integer... values) {
        super(values);
    }

    @Override
    public IntVecteur copy() {
        int i = 0;
        int len = this.getDimension();

        IntVecteur v = new IntVecteur(len);

        for (; i < len; i++) {
            v.set(i, this.get(i));
        }

        return v;
    }

    /**
     * Le produit vectoriel sert � calculer le IntegerVecteur perpendiculaire �
     * deux autres IntegerVecteurs. Le produit vectoriel se dit cross product
     * (produit crois�) en anglais. Le calcul du produit vectoriel se fait �
     * partir de deux IntegerVecteurs et permet d'obtenir un autre
     * IntegerVecteur. Ce IntegerVecteur obtenu est perpendiculaire aux deux
     * autres IntegerVecteurs... tout comme la normale d'une face est
     * perpendiculaire � la face elle m�me.
     */
    @Override
    public IntVecteur cross(IVecteur<Integer> v) {

        if ((this.getDimension() != 3) || (v.getDimension() != 3)) {
            throw new DimensionException(
                    "Le cross product ne peut �tre effectu�e que si les deux IntegerVecteurs sont de dimensions 3");
        }

        return new IntVecteur(
                (this.get(1) * v.get(2)) - (this.get(2) * v.get(1)),
                (this.get(2) * v.get(0)) - (this.get(0) * v.get(2)),
                (this.get(0) * v.get(1)) - (this.get(1) * v.get(0))
        );
    }

    public static int dot(IntVecteur a, IntVecteur b) {

        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "Le produit scalaire ne peut �tre effectu�e que si les deux IntegerVecteurs ont les m�mes dimensions");
        }

        IntVecteur r = a.copy();
        return r.dot(b);
    }

    public static IntVecteur mult(IntVecteur a, int k) {

        IntVecteur r = a.copy();
        r.mult(k);

        return r;
    }

    public static IntVecteur extend(IntVecteur a, int... v) {
        IntVecteur r = new IntVecteur(v.length + a.getDimension());
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
     * Notez que si vous �changez les IntegerVecteurs a et b dans les calculs,
     * le IntegerVecteur obtenu sera exactement le IntegerVecteur oppos� � celui
     * que vous auriez obtenu sans �changer a et b.
     *
     * @param a
     * @param b
     * @return
     */
    public static IntVecteur cross(IntVecteur a, IntVecteur b) {

        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "La multiplication ne peut �tre effectu�e que si les deux IntegerVecteurs ont les m�mes dimensions");
        }

        IntVecteur r = a.copy();
        return r.cross(b);
    }

    public static IVecteur<Integer> opposite(IntVecteur a) {
        IntVecteur r = a.copy();
        return r.opposite();
    }

    public static IntVecteur add(IntVecteur a, IntVecteur b) {

        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "L'addition ne peut �tre effectu�e que si les deux IntegerVecteurs ont les m�mes dimensions");
        }

        IntVecteur r = a.copy();
        return (IntVecteur) r.add(b);
    }

    public static IntVecteur sub(IntVecteur a, IntVecteur b) {

        if (a.getDimension() != b.getDimension()) {
            throw new DimensionException(
                    "La substraction ne peut �tre effectu�e que si les deux IntegerVecteurs ont les m�mes dimensions");
        }

        IntVecteur r = a.copy();
        r.sub(b);

        return r;
    }

    public static IVecteur<Integer> transform(IntVecteur v, IMatrice<Integer> m) {
        return v.transform(m);
    }

    @Override
    public IVecteur<Integer> transform(IMatrice<Integer> m) {
        return IntMatrice.multiply(this, m).getRow(0);
    }

    @Override
    public IVecteur<Integer> homogene(int size, Integer c) {
        IntVecteur v = new IntVecteur(this.getDimension() + (size - this.getDimension()));
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
    public int compareTo(IVecteur<Integer> o) {
        if (this.getDimension() != o.getDimension()) {
            throw new DimensionException();
        }
        return (int) (this.length() - o.length());
    }

    @Override
    public IVecteur<Integer> add(IVecteur<Integer> v) {
        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) + v.get(i));
        }

        return this;
    }

    @Override
    public IVecteur<Integer> truncate(int dim) {

        if (this.getDimension() < dim) {
            throw new DimensionException();
        }

        IntVecteur v = new IntVecteur(dim);

        int i = 0;
        int len = v.getDimension();

        for (; i < len; i++) {
            v.set(i, this.get(i));
        }

        return v;
    }

    @Override
    public void sub(IVecteur<Integer> v) {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) - v.get(i));
        }

    }

    @Override
    public IVecteur<Integer> mult(Integer n) {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) * n);
        }

        return this;
    }

    @Override
    public void div(Integer n) {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, this.get(i) / n);
        }

    }

    @Override
    public Integer dot(IVecteur<Integer> vecteur) {

        int i = 0;
        int len = this.getDimension();
        int dot = 0;

        for (; i < len; i++) {
            dot += this.get(i) * this.get(i);
        }

        return dot;
    }

    @Override
    public IVecteur<Integer> opposite() {

        int i = 0;
        int len = this.getDimension();

        for (; i < len; i++) {
            this.set(i, -this.get(i));
        }

        return this;
    }

    @Override
    public Integer length() {
        return (int) Math.sqrt(this.dot(this));
    }

    @Override
    public IVecteur<Integer> normalize() {
        int len = this.length();
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
        IntVecteur other = (IntVecteur) obj;
        if (getDimension() != other.getDimension()) {
            return false;
        }
        if (!Arrays.equals(getValues(), other.getValues())) {
            return false;
        }
        return true;
    }
}
