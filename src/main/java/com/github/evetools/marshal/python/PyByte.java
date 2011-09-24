package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * 
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyByte extends PyBase {

    /**
     * Bool value.
     */
    private byte value;

    /**
     * Byte value.
     * @param val byte
     */
    public PyByte(final byte val) {
        super(PyType.INT8);
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final PyByte other = (PyByte) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    /**
     * Returns value.
     * @return byte
     */
    public final byte getValue() {
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
        return Integer.toString(this.value);
    }

    @Override
    public final boolean visit(final PyVisitor visitor) {
        return (visitor.visit(this));
    }

}
