package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyBool extends PyBase {

    /**
     * Bool value.
     */
    private boolean value;

    /**
     * Bool value.
     * @param val bool
     */
    public PyBool(final boolean val) {
        super(PyType.BOOL);
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
        final PyBool other = (PyBool) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    /**
     * Returns value.
     * @return boolean
     */
    public final boolean getValue() {
        return this.value;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result);
        if (this.value) {
            result += Boolean.valueOf(this.value).hashCode();
        } else {
            result += Boolean.valueOf(!this.value).hashCode();
        }

        return result;
    }

    @Override
    public final String toString() {
        return Boolean.toString(this.value);
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

}
