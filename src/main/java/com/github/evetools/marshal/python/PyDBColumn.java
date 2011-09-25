package com.github.evetools.marshal.python;

import java.io.IOException;

import com.github.evetools.marshal.python.PyBase.PyType;
import com.github.evetools.marshal.python.PyDBRowDescriptor.DBColumnTypes;

/**
 * The PyDBColumn class represents a single datbalse column.
 * An object of type <code>PyDBColumn</code> contains a field whose type is
 * <code>DBColumnTypes</code> representing the columns database type and a
 * field whose type is derived from <code>PyBase</code> representing the
 * columns name.
 * <p>
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * <p>
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 *
 * @since   0.0.1
 */
public class PyDBColumn extends PyBase {

    /**
     * The value representing the columns name.
     */
    private PyBase name;

    /**
     * The value representing the columns database type.
     */
    private final DBColumnTypes dbtype;

    /**
     * Allocates a <code>PyDBColumn</code> object representing a single
     * database columns of the type provided by the <code>type</code> argument
     * and with a name provided by the <code>columnName</code> argument.
     *
     * @param   type the database type of the <code>PyDBColumn</code>.
     * @param   columnName the name of the <code>PyDBColumn</code>.
     * @since   0.0.1
     */
    public PyDBColumn(final DBColumnTypes type, final PyBase columnName) {
        super(PyType.DBCOLUMN);
        this.dbtype = type;
        this.name = columnName;
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyDBColumn</code> object that
     * represents the same database column as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyDBColumn</code> objects
     *          represent the same value; <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyDBColumn) {
            if (this.getDBType() != ((PyDBColumn) obj).dbtype) {
                return false;
            }
            return this.name.equals(((PyDBColumn) obj).name);
        }
        return false;
    }

    /**
     * Returns a hash code for this <tt>PyDBColumn</tt> object.
     *
     * @return  the integer this object represents.
     * @see     Byte#hashCode()
     * @see     PyBase#getType()
     * @see     DBColumnTypes#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + this.name.hashCode()
                + this.dbtype.hashCode();
    }

    /**
     * Returns the name of this <tt>PyDBColumn</tt> object as a
     * <code>PyBase</code> derived object.
     *
     * @return  the <code>PyBase</code> name of this object.
     * @since   0.0.1
     */
    public final PyBase getName() {
        return name;
    }

    /**
     * Returns the database type of this <tt>PyDBColumn</tt> object as a
     * <code>DBColumnTypes</code>.
     *
     * @return  the <code>DBColumnTypes</code> database type of this object.
     * @since   0.0.1
     */
    public final DBColumnTypes getDBType() {
        return dbtype;
    }

    /**
     * Visits this <tt>PyDBColumn</tt> instance with PyVisitor.
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
     * Compares this <tt>PyDBColumn</tt> instance with another <tt>PyBase</tt>
     * instance.
     *
     * @param   other the <tt>PyBase</tt> instance to be compared
     * @return  zero if both objects share the same <tt>PyDBColumn</tt> and
     *          this object represents the same database column as the
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
            if (other.asDBColumn().getDBType() == getDBType()
                    && other.asDBColumn().getName().equals(this.name)) {
                return 0;
            } else {
                return this.dbtype.compareTo(other.asDBColumn().getDBType());
            }
        } else {
            return other.getType().compareTo(this.getType());
        }
    }
};
