package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDBRow extends PyBase implements Comparable<PyBase> {

    /**
     * Columns.
     */
    private PyDict columns;

    /**
     * Head.
     */
    private PyDBRowDescriptor head;

    /**
     * PyDBRow.
     */
    public PyDBRow() {
        super(PyType.DBROW);
        this.columns = new PyDict();
    }

    /**
     * Sets head.
     * @param header head
     */
    public final void setHead(final PyDBRowDescriptor header) {
        this.head = header;
    }

    /**
     * Returns set of entries.
     * @return entries
     */
    public final Set<Entry<PyBase, PyBase>> entrySet() {
        return this.columns.entrySet();
    }

    @Override
    public final boolean equals(final Object o) {
        return this.columns.equals(o);
    }

    /**
     * Returns value for key.
     * @param key key
     * @return object or null
     */
    public final PyBase get(final PyBase key) {
        return this.columns.get(key);
    }

    /**
     * Returns columns.
     * @return columns
     */
    public final PyDict getColumns() {
        return this.columns;
    }

    /**
     * Returns head.
     * @return head
     */
    public final PyDBRowDescriptor getHead() {
        return this.head;
    }

    @Override
    public final int hashCode() {
        return this.columns.hashCode();
    }

    /**
     * Returns set of keys.
     * @return keys
     */
    public final Set<PyBase> keySet() {
        return this.columns.keySet();
    }

    /**
     * Set value at key position.
     * @param key key
     * @param value value
     * @return prev value or null
     */
    public final PyBase put(final PyBase key, final PyBase value) {
        return this.columns.put(key, value);
    }

    /**
     * Returns size of columns.
     * @return columns no
     */
    public final int size() {
        return this.columns.size();
    }

    @Override
    public final String toString() {
        return this.columns.toString();
    }

    /**
     * Returns values.
     * @return values
     */
    public final Collection<PyBase> values() {
        return this.columns.values();
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            if (this == o.asDBRow()) {
                return 0;
            }
            return -1;
        } else {
            return 1;
        }
    }

}
