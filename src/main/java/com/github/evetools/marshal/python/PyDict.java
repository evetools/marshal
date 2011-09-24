package com.github.evetools.marshal.python;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDict extends PyBase {

    /**
     * map container.
     */
    private Map<PyBase, PyBase> map;

    /**
     * PyDict.
     */
    public PyDict() {
        super(PyType.DICT);
        this.map = new HashMap<PyBase, PyBase>();
    }

    /**
     * Checks for key.
     * @param key key
     * @return object or null
     */
    public final boolean containsKey(final PyBase key) {
        return this.map.containsKey(key);
    }

    /**
     * Checks for value.
     * @param value value
     * @return object or null
     */
    public final boolean containsValue(final PyBase value) {
        return this.map.containsValue(value);
    }

    /**
     * Returns Set of entries.
     * @return entries
     */
    public final Set<Entry<PyBase, PyBase>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public final boolean equals(final Object o) {
        return this.map.equals(o);
    }

    /**
     * Returns value to key.
     * @param key key
     * @return value
     */
    public final PyBase get(final PyBase key) {
        return this.map.get(key);
    }

    /**
     * Returns value to key.
     * @param key key
     * @return value
     */
    public final PyBase get(final String key) {
        return this.map.get(new PyBuffer(key.getBytes()));
    }

    @Override
    public final int hashCode() {
        return this.map.hashCode();
    }

    /**
     * Returns set of keys.
     * @return value
     */
    public final Set<PyBase> keySet() {
        return this.map.keySet();
    }

    /**
     * Puts value at key position.
     * @param key key
     * @param value 
     * @return prev value or null
     */
    public final PyBase put(final PyBase key, final PyBase value) {
        return this.map.put(key, value);
    }

    /**
     * Returns size.
     * @return size
     */
    public final int size() {
        return this.map.size();
    }

    /**
     * Returns values.
     * @return values
     */
    public final Collection<PyBase> values() {
        return this.map.values();
    }

    @Override
    public final boolean visit(final PyVisitor visitor) {
        return (visitor.visit(this));
    }

}
