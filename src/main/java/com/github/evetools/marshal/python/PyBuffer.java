package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.Arrays;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyBuffer extends PyBase {

    /**
     * Byte array.
     */
    private byte[] value;

    /**
     * Byte array.
     * @param string bytes
     */
    public PyBuffer(final String string) {
        super(PyType.BUFFER);
        byte[] b = string.getBytes();
        this.value = new byte[b.length];
        System.arraycopy(b, 0, this.value, 0, b.length);
    }

    /**
     * Byte array.
     * @param bytes bytes
     */
    public PyBuffer(final byte[] bytes) {
        super(PyType.BUFFER);
        this.value = new byte[bytes.length];
        System.arraycopy(bytes, 0, this.value, 0, bytes.length);
    }

    /**
     * Byte array.
     * @param type type
     * @param bytes bytes
     */
    protected PyBuffer(final PyType type, final byte[] bytes) {
        super(type);
        this.value = new byte[bytes.length];
        System.arraycopy(bytes, 0, this.value, 0, bytes.length);
    }

    /**
     * Returns value.
     * @return bytes
     */
    public final byte[] getValue() {
        return this.value;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            PyBuffer other = (PyBuffer) obj;

            if (!Arrays.equals(value, other.value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final String toString() {
        return new String(this.value);
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o instanceof PyBuffer) {
            return o.asBuffer().toString().compareTo(this.toString());
        } else {
            return 1;
        }
    }

}
