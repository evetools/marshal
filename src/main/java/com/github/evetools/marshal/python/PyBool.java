package com.github.evetools.marshal.python;

import java.io.IOException;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
 * The PyBool class wraps a value of the primitive type
 * <code>boolean</code> in an object. An object of type
 * <code>PyBool</code> contains a single field whose type is
 * <code>boolean</code>.
 * <p>
 * In addition, this class provides methods for converting
 * a <code>boolean</code> to a <code>String</code>.
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
public class PyBool extends PyBase {

    /**
     * The value of the PyBool.
     */
    private boolean value;

    /**
     * Allocates a <code>PyBool</code> object representing the
     * <code>bool</code> argument.
     *
     * @param   bool the value of the <code>PyBool</code>.
     * @since   0.0.1
     */
    public PyBool(final boolean bool) {
        super(PyType.BOOL);
        this.value = bool;
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyBool</code> object that
     * represents the same <code>boolean</code> value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyBool</code> objects represent
     *          the same value; <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyBool) {
            return value == ((PyBool) obj).booleanValue();
        }
        return false;
    }

    /**
     * Returns the value of this <tt>PyBool</tt> object as a
     * <code>boolean</code> primitive.
     *
     * @return  the primitive <code>boolean</code> value of this object.
     * @since   0.0.1
     */
    public final boolean booleanValue() {
        return this.value;
    }

    /**
     * Returns a hash code for this <tt>PyBool</tt> object.
     *
     * @return  the integer this object represents.
     * @see     Boolean#hashCode()
     * @see     PyBase#getType()
     * @see     PyType#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + Boolean.valueOf(this.value).hashCode();
    }

    /**
     * Returns a <tt>String</tt> object representing this PyBool's
     * value.  If this object represents the value <code>true</code>,
     * a string equal to {@code "true"} is returned. Otherwise, a
     * string equal to {@code "false"} is returned.
     *
     * @return  a string representation of this object.
     * @see     Boolean#toString()
     * @since   0.0.1
     */
    @Override
    public final String toString() {
        return Boolean.toString(this.value);
    }

    /**
     * Visits this <tt>PyBool</tt> instance with PyVisitor.
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
     * Compares this <tt>PyBool</tt> instance with another <tt>PyBase</tt>
     * instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyType</tt> and
     *          this object represents the same <code>boolean</code> value as
     *          the argument; a positive value if this object represents true
     *          and the argument represents false; and a negative value if
     *          this object represents false and the argument represents true;
     *          if both objects are of different <tt>PyType</tt> their
     *          <tt>PyType</tt>'s are compared.
     * @see     Comparable
     * @see     PyType
     * @since   0.0.1
     */
    @Override
    public final int compareTo(final PyBase other) {
        if (other.getType() == this.getType()) {
            if (other.asBool().value == value) {
                return 0;
            } else if (value) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return other.getType().compareTo(this.getType());
        }
    }

}
