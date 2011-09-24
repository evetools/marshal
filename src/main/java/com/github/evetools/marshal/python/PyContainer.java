package com.github.evetools.marshal.python;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public abstract class PyContainer extends PyBase {

    /**
     * Container.
     */
    private List<PyBase> container = new ArrayList<PyBase>();

    /**
     * PyContainer.
     * @param type type
     */
    protected PyContainer(final PyType type) {
        super(type);
    }

    /**
     * Add PyBase at position.
     * @param index index
     * @param element PyBase
     */
    public final void add(final int index, final PyBase element) {
        this.container.add(index, element);
    }

    /**
     * Adds PyBase.
     * @param e PyBase
     * @return result
     */
    public final boolean add(final PyBase e) {
        return this.container.add(e);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final PyContainer other = (PyContainer) obj;
        if (this.container == null) {
            if (other.container != null) {
                return false;
            }
        } else if (!this.container.equals(other.container)) {
            return false;
        }
        return true;
    }

    /**
     * Returns PyBase.
     * @param index index
     * @return object
     */
    public final PyBase get(final int index) {
        return this.container.get(index);
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result);
        if (this.container != null) {
            result += this.container.hashCode();
        }
        return result;
    }

    /**
     * Returns iterator.
     * @return iterator
     */
    public final Iterator<PyBase> iterator() {
        return this.container.iterator();
    }

    /**
     * Returns size.
     * @return size
     */
    public final int size() {
        return this.container.size();
    }

    @Override
    public final String toString() {
        return this.container.toString();
    }
    

}
