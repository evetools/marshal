package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
 * The PyBool class wraps a <code>SortedMap</code> of
 * <code>PyBase</code> pairs.
 * An object of type <code>PyDict</code> contains a
 * field whose type is <code>SortedMap<PyBase, PyBase></code>.
 * <p>
 * In addition, this class provides methods for accessing
 * the <code>SortedMap</code>.
 * <br>
 * <br>
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * <br>
 * <br>
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 *
 * @since   0.0.1
 */
public class PyDict extends PyBase implements Comparable<PyBase> {

    /**
     * The SortedMap of PyBase pairs.
     */
    private SortedMap<PyBase, PyBase> map;

    /**
     * Allocates a <code>PyDict</code> object.
     *
     * @since   0.0.1
     */
    public PyDict() {
        super(PyType.DICT);
        this.map = new TreeMap<PyBase, PyBase>();
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * @return a set view of the mappings contained in this map,
     *         sorted in ascending key order
     * @see    Map#entrySet()
     * @since   0.0.1
     */
    public final Set<Entry<PyBase, PyBase>> entrySet() {
        return this.map.entrySet();
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyDicy</code> object that
     * represents the same <code>SortedMap</code> of <code>PyBase</code> pairs
     * as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyDict</code> objects represent
     *          the same value; <code>false</code> otherwise.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyDict) {
            return this.map.equals(((PyDict) obj).map);
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @see    Map#get(Object)
     * @since   0.0.1
     */
    public final PyBase get(final PyBase key) {
        return this.map.get(key);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @see    Map#get(Object)
     * @since   0.0.1
     */
    public final PyBase get(final String key) {
        return this.map.get(new PyBuffer(key.getBytes()));
    }

    /**
     * Returns a hash code for this <tt>PyDict</tt> object.
     *
     * @return  the integer this object represents.
     * @see     SortedMap#hashCode()
     * @see     PyBase#getType()
     * @see     PyType#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + this.map.hashCode();
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * @return  a set view of the keys contained in this map, sorted in
     *          ascending order
     */
    public final Set<PyBase> keySet() {
        return this.map.keySet();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @see    Map#put(Object, Object)
     * @since   0.0.1
     */
    public final PyBase put(final PyBase key, final PyBase value) {
        return this.map.put(key, value);
    }

    /**
     * Returns the number of key-value mappings.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    public final int size() {
        return this.map.size();
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * @return a collection view of the values contained in this map,
     *         sorted in ascending key order
     */
    public final Collection<PyBase> values() {
        return this.map.values();
    }

    /**
     * Visits this and instance of <tt>PyDict</tt> with an object
     * implementing <code>PyVisitor</code> interface.
     *
     * @param   visitor visitor used
     * @return  <code>true</code> if the visit was successfull;
     *          <code>false</code> otherwise.
     * @throws  IOException if an error occurred
     * @see     PyVisitable
     * @since   0.0.1
     */
    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    /**
     * Compares an instance of <tt>PyDict</tt> with another
     * <tt>PyBase</tt> instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyType</tt> and
     *          this object represents the same <code>SortedMap</code>
     *          of <code>PyBase</code> pairs as the argument;
     *          a positive value if this object's value is greater then
     *          the argument's value;
     *          and a negative value if this object's value is smaller then
     *          the argument's value;
     *          if both objects are of different <tt>PyType</tt> their
     *          <tt>PyType</tt>'s are compared.
     * @see     Comparable
     * @see     PyType
     * @see     PyDict#equals(Object)
     * @since   0.0.1
     */
    @Override
    public final int compareTo(final PyBase other) {
        if (other instanceof PyDict) {
            if (this.map.equals(((PyDict) other).map)) {
                return 0;
            } else if (this.map.size()
                    > ((PyDict) other).map.size()) {
                return 1;
            }
            return -1;
        } else {
            return other.getType().compareTo(this.getType());
        }
    }

}
