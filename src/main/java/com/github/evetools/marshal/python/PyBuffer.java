package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.Arrays;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
* The PyBuffer class wraps an array of the primitive type
* <code>byte</code> in an object. An object of type
* <code>PyBuffer</code> contains an array field of <code>byte</code> primitives.
* <p>
* In addition, this class provides methods for converting
* an array of <code>byte</code>s to a <code>String</code>.
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
public class PyBuffer extends PyBase {

    /**
     * The byte array of the PyBuffer.
     */
    private byte[] value;

    /**
     * Allocates a <code>PyBuffer</code> object representing the
     * <code>string</code> argument.
     *
     * @param string the value of the <code>PyBuffer</code>.
     * @since   0.0.1
     */
    public PyBuffer(final String string) {
        super(PyType.BUFFER);
        byte[] b = string.getBytes();
        this.value = new byte[b.length];
        System.arraycopy(b, 0, this.value, 0, b.length);
    }

    /**
     * Allocates a <code>PyBuffer</code> object representing the
     * <code>bytes</code> argument.
     *
     * @param bytes the value of the <code>PyBuffer</code>.
     * @since   0.0.1
     */
    public PyBuffer(final byte[] bytes) {
        super(PyType.BUFFER);
        this.value = new byte[bytes.length];
        System.arraycopy(bytes, 0, this.value, 0, bytes.length);
    }

    /**
     * Allocates a <code>PyBuffer</code> object representing the
     * <code>bytes</code> argument.
     *
     * @param type the <code>PyType</code> of the <code>PyBuffer</code>.
     * @param bytes the value of the <code>PyBuffer</code>.
     * @since   0.0.1
     */
    protected PyBuffer(final PyType type, final byte[] bytes) {
        super(type);
        this.value = new byte[bytes.length];
        System.arraycopy(bytes, 0, this.value, 0, bytes.length);
    }

    /**
     * Returns a hash code for this <tt>PyBuffer</tt> object.
     *
     * @return  the integer this object represents.
     * @see     Arrays#hashCode()
     * @see     PyBase#getType()
     * @see     PyType#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + Arrays.hashCode(value);
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyBuffer</code> object that
     * represents the same array of <code>byte</code> primitives as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyShort</code> objects represent
     *          the same value; <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyBuffer) {
            return Arrays.equals(value, ((PyBuffer) obj).value);
        }
        return false;
    }

    /**
     * Returns a <code>String</code> object representing this
     * <code>PyBuffer</code>'s value.  The value is converted to an
     * <code>String</code> object and returned as a string, exactly as if
     * the array of <code>byte</code>s were given as an argument to the
     * {@link java.lang.String(byte[] bytes)} method.
     *
     * @return  a string representation of this object.
     * @see     String#toString()
     * @since   0.0.1
     */
    @Override
    public final String toString() {
        return new String(this.value);
    }

    /**
     * Visits this <tt>PyBuffer</tt> instance with an object
     * implementing <code>PyVisitor</code> interface.
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
     * Compares this <tt>PyBuffer</tt> instance with another <tt>PyBase</tt>
     * instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyType</tt> and
     *          this object represents the same array of <code>byte</code>
     *          primitives as the argument;
     *          a positive value if this object's value is greater then
     *          the argument's value;
     *          and a negative value if this object's value is smaller then
     *          the argument's value;
     *          if both objects are of different <tt>PyType</tt> their
     *          <tt>PyType</tt>'s are compared.
     * @see     Comparable
     * @see     PyType
     * @see     String#compareTo(String)
     * @since   0.0.1
     */
    @Override
    public final int compareTo(final PyBase other) {
        if (other.getType() == this.getType()) {
            return this.toString().compareTo(other.toString());
        } else {
            return other.getType().compareTo(this.getType());
        }
    }

}
