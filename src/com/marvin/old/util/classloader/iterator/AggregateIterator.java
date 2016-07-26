package com.marvin.old.util.classloader.iterator;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Aggregates Enumeration instances into one iterator and filters out duplicates.  Always keeps one
 * ahead of the enumerator to protect against returning duplicates.
 * @param <E>
 */
public class AggregateIterator<E> implements Iterator<E> {

    private LinkedList<Enumeration<E>> enums = new LinkedList<>();
    private Enumeration<E> cur = null;
    private E next = null;
    private Set<E> loaded = new HashSet<>();

    public AggregateIterator<E> addEnumeration(Enumeration<E> e) {
        if (e.hasMoreElements()) {
            if (cur == null) {
                cur = e;
                next = e.nextElement();
                loaded.add(next);
            } else {
                enums.add(e);
            }
        }
        return this;
    }

    @Override
    public boolean hasNext() {
        return (next != null);
    }

    @Override
    public E next() {
        if (next != null) {
            E prev = next;
            next = loadNext();
            return prev;
        } else {
            throw new NoSuchElementException();
        }
    }

    private Enumeration<E> determineCurrentEnumeration() {
        if (cur != null && !cur.hasMoreElements()) {
            if (enums.size() > 0) {
                cur = enums.removeLast();
            } else {
                cur = null;
            }
        }
        return cur;
    }

    private E loadNext() {
        if (determineCurrentEnumeration() != null) {
            E tmp = cur.nextElement();
            int loadedSize = loaded.size();
            while (loaded.contains(tmp)) {
                tmp = loadNext();
                if (tmp == null || loaded.size() > loadedSize) {
                    break;
                }
            }
            if (tmp != null) {
                loaded.add(tmp);
            }
            return tmp;
        }
        return null;

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}