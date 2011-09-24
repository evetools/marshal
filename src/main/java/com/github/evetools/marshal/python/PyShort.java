package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyShort extends PyBase implements Comparable<PyBase> {

    /**
     * short value.
     */
    private short value;

    /**
     * Short.
     * @param val
     *            value
     */
    public PyShort(final short val) {
        super(PyType.INT16);
        this.value = val;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final PyShort other = (PyShort) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    /**
     * Returns value.
     * @return short
     */
    public final short getValue() {
        return this.value;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result) + this.value;
        return result;
    }

    @Override
    public final String toString() {
        return Short.toString(this.value);
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            return Integer.valueOf(o.asShort().hashCode()).compareTo(
                    this.hashCode());
        } else {
            return 1;
        }
    }
}
