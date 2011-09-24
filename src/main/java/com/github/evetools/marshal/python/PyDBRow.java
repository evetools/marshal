package com.github.evetools.marshal.python;

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
public class PyDBRow extends PyBase {

    private PyDict columns;

    private PyDBRowDescriptor head;

    public PyDBRow() {
        super(PyType.DBROW);
        this.columns = new PyDict();
    }

    public void setHead(PyDBRowDescriptor head) {
        this.head = head;
    }

    public boolean containsKey(PyBase key) {
        return this.columns.containsKey(key);
    }

    public boolean containsValue(PyBase value) {
        return this.columns.containsValue(value);
    }

    public Set<Entry<PyBase, PyBase>> entrySet() {
        return this.columns.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return this.columns.equals(o);
    }

    public PyBase get(PyBase key) {
        return this.columns.get(key);
    }

    public PyDict getColumns() {
        return this.columns;
    }

    public PyDBRowDescriptor getHead() {
        return this.head;
    }

    @Override
    public int hashCode() {
        return this.columns.hashCode();
    }

    public Set<PyBase> keySet() {
        return this.columns.keySet();
    }

    public PyBase put(PyBase key, PyBase value) {
        return this.columns.put(key, value);
    }

    public int size() {
        return this.columns.size();
    }

    @Override
    public String toString() {
        return this.columns.toString();
    }

    public Collection<PyBase> values() {
        return this.columns.values();
    }

    @Override
    public boolean visit(PyVisitor visitor) {
        return (visitor.visit(this));
    }

}
