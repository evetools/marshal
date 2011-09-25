package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.List;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
 * The PyDBRow class represents a single database row.
 * An object of type <code>PyDBRow</code> contains a field whose type is
 * <code>PyDBRowDescriptor</code> describing the database row and a
 * field whose type is <code>PyDict</code> representing the
 * rows columns.
 * <p>
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * <p>
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 *
 * @since   0.0.1
 */
public class PyDBRow extends PyBase implements Comparable<PyBase> {

    /**
     * The columns contained in this database row.
     */
    private PyDict columns;

    /**
     * The description of this database row.
     */
    private PyDBRowDescriptor descriptor;

    /**
     * Allocates a <code>PyDBRow</code> class representing the
     * <code>desc</code> argument.
     *
     * @param   desc the PyDBRowDescriptor of the <code>PyDBRow</code>
     * @since   0.0.1
     */
    public PyDBRow(final PyDBRowDescriptor desc) {
        super(PyType.DBROW);
        this.columns = new PyDict();
        this.descriptor = desc;
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
        if (obj instanceof PyDBRow) {
            return ((((PyDBRow) obj).getDescriptor().equals(this.descriptor))
                    && ((((PyDBRow) obj).columns.equals(this.columns))));
        }
        return false;
    }

    /**
     * Returns a hash code for this <tt>PyDBRow</tt> object.
     *
     * @return  the integer this object represents.
     * @see     Byte#hashCode()
     * @see     PyBase#getType()
     * @see     PyDict#hashCode()
     * @see     PyDBRowDescriptor#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + this.descriptor.hashCode()
                + this.columns.hashCode();
    }

    /**
     * Returns the column value to which the specified key is mapped,
     * or {@code null} if this object contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this object contains no mapping for the key
     * @see    PyDict#get(PyBase)
     * @since   0.0.1
     */
    public final PyBase get(final PyBase key) {
        return this.columns.get(key);
    }

    /**
     * Returns the column value to which the specified key is mapped,
     * or {@code null} if this object contains no mapping for the key.
     *
     * The key is wrapped into a PyBuffer object.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this object contains no mapping for the key
     * @see    PyDict#get(PyBase)
     * @since   0.0.1
     */
    public final PyBase get(final String key) {
        return this.columns.get(new PyBuffer(key));
    }

    /**
     * Returns a <code>List</code> of <code>PyDBColumn</code> objects
     * representing values stored in this <code>PyDBRow</code> object.
     *
     * @return the <code>List</code> of <code>PyDBColumn</code> objects
     *         representing values stored in this <code>PyDBRow</code> object.
     * @see    PyDBRowDescriptor#getColumns()
     * @since   0.0.1
     */
    public final List<PyDBColumn> getColumns() {
        return this.descriptor.getColumns();
    }

    /**
     * Returns the description of this <tt>PyDBRow</tt> object
     * as a <code>PyDBRowDescriptor</code> object.
     *
     * @return  the <code>PyDBRowDescriptor</code> description of this object.
     * @since   0.0.1
     */
    public final PyDBRowDescriptor getDescriptor() {
        return this.descriptor;
    }

    /**
     * Associates the specified value with the specified key in this object.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @see    PyDict#put(PyBase, PyBase)
     * @since   0.0.1
     */
    public final PyBase put(final PyBase key, final PyBase value) {
        return this.columns.put(key, value);
    }

    /**
     * Returns a <code>String</code> object representing this
     * <code>PyDBRow</code>.
     *
     * @return  a string representation of this object.
     * @see PyDict#toString()
     * @since   0.0.1
     */
    @Override
    public final String toString() {
        return this.columns.toString();
    }

    /**
     * Visits this <tt>PyDBRow</tt> instance with PyVisitor.
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
     * Compares this <tt>PyDBRow</tt> instance with another <tt>PyBase</tt>
     * instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyDBRow</tt> and
     *          this object represents the same database row as the
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
            int res = other.asDBRow().getDescriptor().compareTo(
                    this.descriptor);
            if (res != 0) {
                return res;
            }
            return other.asDBRow().columns.compareTo(this.columns);
        } else {
            return other.getType().compareTo(this.getType());
        }
    }

}
