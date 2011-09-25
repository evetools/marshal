package com.github.evetools.marshal.python;

import java.io.IOException;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
* The PyNone class wraps a value of <code>null</code> in an object.
* An object of type <code>PyNone</code> contains not fields.
* <p>
* In addition, this class provides methods for converting
* a <code>null</code> to a <code>String</code>.
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
public class PyNone extends PyBase {

    /**
     * Allocates a <code>PyNone</code> object representing <code>null</code>.
     *
     * @since   0.0.1
     */
    public PyNone() {
        super(PyType.NONE);
    }

    /**
     * Returns <code>true</code> if and only ifthe argument is
     * <code>null</code> or an instance of <code>PyNone</code>.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyNone</code> objects represent
     *          the same value or argument is null;
     *          <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null || obj instanceof PyNone) {
            return true;
        }
        return false;
    }

    /**
     * Returns a hash code for this <tt>PyNone</tt> object.
     *
     * @return  the integer this object represents.
     * @see     PyBase#getType()
     * @see     PyType#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode());
    }

    /**
     * Returns a <code>String</code> object representing <code>null</code>.
     *
     * @return  a string representation of this object.
     * @since   0.0.1
     */
    @Override
    public final String toString() {
        return "NULL";
    }

    /**
     * Visits this <tt>PyNone</tt> instance with an object
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
     * Compares this <tt>PyNone</tt> instance with another <tt>PyBase</tt>
     * instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyType</tt> or
     *          argument is <code>null</code>;
     *          if both objects are of different <tt>PyType</tt> their
     *          <tt>PyType</tt>'s are compared.
     * @see     Comparable
     * @see     PyType
     * @since   0.0.1
     */
    @Override
    public final int compareTo(final PyBase other) {
        if (other == null || other.getType() == this.getType()) {
            return 0;
        } else {
            return other.getType().compareTo(this.getType());
        }
    }
}
