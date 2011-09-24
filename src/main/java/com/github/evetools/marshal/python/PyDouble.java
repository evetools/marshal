package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDouble extends PyBase {

    /**
     * double value.
     */
    private double value;

    /**
     * PyDouble.
     * @param val double
     */
    public PyDouble(final double val) {
        super(PyType.DOUBLE);
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
        final PyDouble other = (PyDouble) obj;
        if (Double.doubleToLongBits(this.value) != Double
                .doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }

    /**
     * Returns value.
     * @return double
     */
    public final double getValue() {
        return this.value;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(this.value);
        result = (prime * result) + (int) (temp ^ (temp >>> Integer.SIZE));
        return result;
    }

    @Override
    public final String toString() {
        return Double.toString(this.value);
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            return Integer.valueOf(o.asDouble().hashCode()).compareTo(
                    this.hashCode());
        } else {
            return 1;
        }
    }

}
