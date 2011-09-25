package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.evetools.marshal.python.PyBase.PyType;

/**
 * The PyContainer as an abstract class wraps a <code>List</code> of
 * <code>PyBase</code> derived objects. An derived <code>PyContainer</code> type
 * contains a field whose type is a <code>List</code> of <code>PyBase</code>
 * objects.
 * <p>
 * In addition, this class provides methods for accessing a <code>List</code> of
 * <code>PyBase</code> objects. <br>
 * <br>
 * Copyright (C)2011 by Gregor Anders All rights reserved. <br>
 * <br>
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 *
 * @since 0.0.1
 */
public abstract class PyContainer extends PyBase {

    /**
     * The List of PyBase objects.
     */
    private List<PyBase> container = new ArrayList<PyBase>();

    /**
     * Allocates a <code>PyContainer</code> object representing the
     * <code>type</code> argument.
     *
     * @param type the <code>PyType</code> of the <code>PyContainer</code>.
     * @since   0.0.1
     */
    protected PyContainer(final PyType type) {
        super(type);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param   obj object to be appended to this list
     * @return  <tt>true</tt> (as specified by {@link List#add})
     * @see     List#get(int)
     * @since   0.0.1
     */
    public final boolean add(final PyBase obj) {
        return this.container.add(obj);
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyContainer</code> object that
     * represents the same <code>List</code> of <code>PyBase</code> objects as
     * this object and both share the same <code>PyType</code>.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyContainer</code> objects
     *          represent the same value; <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyContainer
                && ((PyContainer) obj).getType() == this.getType()) {
            return this.container.equals(((PyContainer) obj).container);
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param   index index of the element to return
     * @return  the element at the specified position in this list
     * @see     List#get(int)
     * @since   0.0.1
     */
    public final PyBase get(final int index) {
        return this.container.get(index);
    }

    /**
     * Returns a hash code for this <tt>PyContainer</tt> object.
     *
     * @return  the integer this object represents.
     * @see     List#hashCode()
     * @see     PyBase#getType()
     * @see     PyType#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = this.getType().hashCode();
        result = (prime * result);
        if (this.container != null) {
            result += this.container.hashCode();
        }
        return result;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return  an iterator over the elements in this list in proper sequence
     * @see     List#iterator()
     * @since   0.0.1
     */
    public final Iterator<PyBase> iterator() {
        return this.container.iterator();
    }

    /**
    * Returns the number of elements in this list.  If this list contains
    * more than <tt>Integer.MAX_VALUE</tt> elements, returns
    * <tt>Integer.MAX_VALUE</tt>.
    *
    * @return the number of elements in this list
    * @see     List#size()
    * @since   0.0.1
    */
    public final int size() {
        return this.container.size();
    }

    /**
     * Visits this and instance of <tt>PyContainer</tt> with an object
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
     * Compares an instance of <tt>PyContainer</tt> with another
     * <tt>PyBase</tt> instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyType</tt> and
     *          this object represents the same <code>List</code>
     *          of <code>PyBase</code> objects as the argument;
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
            if (this.container.equals(((PyContainer) other).container)) {
                return 0;
            } else if (this.container.size()
                    > ((PyContainer) other).container.size()) {
                return 1;
            }
            return -1;
        } else {
            return other.getType().compareTo(this.getType());
        }
    }
}
