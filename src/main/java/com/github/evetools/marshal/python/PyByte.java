package com.github.evetools.marshal.python;

import java.io.IOException;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
 * The PyByte class wraps a value of the primitive type
 * <code>byte</code> in an object. An object of type
 * <code>PyByte</code> contains a single field whose type is
 * <code>byte</code>.
 * <p>
 * In addition, this class provides methods for converting
 * a <code>byte</code> to a <code>String</code>.
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
public class PyByte extends PyBase {

    /**
     * The value of the PyByte.
     */
    private byte value;

    /**
     * Allocates a <code>PyByte</code> object representing the
     * <code>val</code> argument.
     *
     * @param   val the value of the <code>PyByte</code>.
     * @since   0.0.1
     */
    public PyByte(final byte val) {
        super(PyType.INT8);
        this.value = val;
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyByte</code> object that
     * represents the same <code>byte</code> value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyByte</code> objects represent
     *          the same value; <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyByte) {
            return value == ((PyByte) obj).byteValue();
        }
        return false;
    }

    /**
     * Returns the value of this <tt>PyByte</tt> object as a <code>byte</code>
     * primitive.
     *
     * @return  the primitive <code>byte</code> value of this object.
     * @since   0.0.1
     */
    public final byte byteValue() {
        return this.value;
    }

    /**
     * Returns a hash code for this <tt>PyByte</tt> object.
     *
     * @return  the integer this object represents.
     * @see     Byte#hashCode()
     * @see     PyBase#getType()
     * @see     PyType#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + Byte.valueOf(this.value).hashCode();
    }

    /**
     * Returns a <code>String</code> object representing this
     * <code>PyByte</code>'s value.  The value is converted to an
     * <code>Integer</code> object and returned as a string, exactly as if
     * the <code>byte</code> value were given as an argument to the
     * {@link java.lang.Integer#toString(int)} method.
     *
     * @return  a string representation of this object.
     * @see Integer#toString()
     * @since   0.0.1
     */
    @Override
    public final String toString() {
        final int max = 0xFF;
        return Integer.toString(this.value & max);
    }

    /**
     * Visits this <tt>PyByte</tt> instance with PyVisitor.
     *
     * @param   visitor   visitor used
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
     * Compares this <tt>PyByte</tt> instance with another <tt>PyBase</tt>
     * instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyType</tt> and
     *          this object represents the same <code>byte</code> value as the
     *          argument; a positive value if this object's value is greater
     *          then the argument's value; and a negative value if
     *          this object's value is smaller then the argument's value;
     *          if both objects are of different <tt>PyType</tt> their
     *          <tt>PyType</tt>'s are compared.
     * @see     Comparable
     * @see     PyType
     * @since   0.0.1
     */
    @Override
    public final int compareTo(final PyBase other) {
        if (other.getType() == this.getType()) {
            if (other.asByte().value == value) {
                return 0;
            } else if (value > other.asByte().value) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return other.getType().compareTo(this.getType());
        }
    }

}
