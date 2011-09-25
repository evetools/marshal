package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.github.evetools.marshal.python.PyBase.PyType;
import com.github.evetools.marshal.python.PyDBColumn.DBColumnSize;
import com.github.evetools.marshal.python.PyDBColumn.DBColumnType;

/**
 * The PyDBRowDescriptor class describes  a single database row.
 * An object of type <code>PyDBRowDescriptor</code> contains a
 * field whose type is <code>int</code> representing the size of the
 * uncompressed buffer holding compressed values, a List of
 * <code>PyDBColumn</code> objects describing each column in a database row
 * and a SortedMap of <code>DBColumnSize, List<PyDBColumn></code> pairs
 * used for reading a row from the buffer.
 * <p>
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * <p>
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 *
 * @since   0.0.1
 */
public class PyDBRowDescriptor extends PyBase {


    /**
     * The size of teh uncompressed buffer used for storing numeric or boolean
     * values.
     */
    private int size;

    /**
     * The List of the database rows columns.
     */
    private List<PyDBColumn> columns;

    /**
     * SortedMap used for reading values from Buffer.
     *
     * Values are stored in the uncompressed buffer in the order of the byte
     * size followed by the order of the underlying object.
     * Boolean values are stored as single bits so one byte holds room for 8
     * boolean values. String values are not stored in the zero compressed
     * buffer and are part of the normal buffer though handled by the reader.
     */
    private SortedMap<DBColumnSize, List<PyDBColumn>> typeMap =
            new TreeMap<DBColumnSize, List<PyDBColumn>>() {

        private static final long serialVersionUID = 1L;

        {
            put(DBColumnSize.BIT64, new ArrayList<PyDBColumn>());
            put(DBColumnSize.BIT32, new ArrayList<PyDBColumn>());
            put(DBColumnSize.BIT16, new ArrayList<PyDBColumn>());
            put(DBColumnSize.BIT8, new ArrayList<PyDBColumn>());
            put(DBColumnSize.BIT1, new ArrayList<PyDBColumn>());
            put(DBColumnSize.BIT0, new ArrayList<PyDBColumn>());
        }
    };

    /**
     * Allocates a <code>PyDBRowDescriptor</code> class representing the
     * <code>object</code> argument.
     *
     * @param   object used to create <code>PyDBRowDescriptor</code>.
     * @throws  IOException on error
     * @since   0.0.1
     */
    public PyDBRowDescriptor(final PyObjectEx object) throws IOException {

        super(PyType.DBROWDESCRIPTOR);

        this.size = 0;

        this.columns = this.init(object);
    }

    /**
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>PyDBRowDescriptor</code> object that
     * represents the same database row as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the <code>PyDBRowDescriptor</code> objects
     *          represent the same value; <code>false</code> otherwise.
     * @since   0.0.1
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof PyDBRowDescriptor) {
            return this.columns.equals(((PyDBRowDescriptor) obj).columns);
        }
        return false;
    }

    /**
     * Returns a hash code for this <tt>PyDBColumn</tt> object.
     *
     * @return  the integer this object represents.
     * @see     Byte#hashCode()
     * @see     PyBase#getType()
     * @see     List#hashCode()
     * @since   0.0.1
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        return (prime * super.getType().hashCode())
                + this.columns.hashCode();
    }

    /**
     * Returns a List of <code>PyDBColumn</code> objects representing the
     * columns of a <code>PyDBRow</code> object described by this object.
     *
     * @return  List of <code>PyDBColumn</code> objects.
     * @since   0.0.1
     */
    public final List<PyDBColumn> getColumns() {
        return columns;
    }

    /**
     * Initialize typeMap for this <code>PyDBRowDescriptor</code> object.
     * @param object used to create <code>PyDBRowDescriptor</code>
     * @return List of <code>PyDBColumn</code> objects describing a
     * <code>PyDBRow</code> object.
     * @throws IOException on error
     * @since   0.0.1
     */
    private List<PyDBColumn> init(final PyObjectEx object) throws IOException {

        if (!(object.getHead().isTuple())) {
            throw new IOException("Invalid Packed Row header");
        }

        final PyTuple tuple = object.getHead().asTuple();

        if (!(tuple.get(1).isTuple())) {
            throw new IOException("Invalid Packed Row header");
        }

        PyTuple header = tuple.get(1).asTuple();

        if (!(header.get(0).isTuple())) {
            throw new IOException("Invalid Packed Row header");
        }

        header = header.get(0).asTuple();
        int boolcount = 0;

        for (final Iterator<PyBase> iterator = header.iterator(); iterator
                .hasNext();) {

            final PyBase type = iterator.next();

            if (type != null) {

                if (!(type.isTuple())) {
                    throw new IOException("Invalid DB column type");
                }

                final PyTuple info = type.asTuple();

                int dbtype = 0;

                if (info.get(1) instanceof PyByte) {
                    dbtype = (((PyByte) info.get(1)).byteValue());
                } else if (info.get(1) instanceof PyShort) {
                    dbtype = (((PyShort) info.get(1)).shortValue());
                }

                DBColumnType columnType = DBColumnType.get(Integer
                        .valueOf(dbtype));

                if (columnType == DBColumnType.BOOL) {

                    if (boolcount == 0) {
                        size += DBColumnSize.BIT8.size();
                    }
                    boolcount++;
                    if (boolcount == Byte.SIZE) {
                        boolcount = 0;
                    }

                } else {
                    size += columnType.size().size();
                }

                typeMap.get(columnType.size()).add(
                        new PyDBColumn(columnType, info.get(0)));
            }
        }

        return this.createColumns();
    }

    /**
     * Creates List of <code></code> object representing the
     * columns of a <code>PyDBRow</code> object described by this object.
     * The columns are sorted the way they are read from the buffer.
     * @return List of <code>PyDBColumn</code> objects describing a
     *          <code>PyDBRow</code> object.
     * @since   0.0.1
     */
    private List<PyDBColumn> createColumns() {

        List<PyDBColumn> elements = new ArrayList<PyDBColumn>();

        List<PyDBColumn> list = typeMap
                .get(DBColumnSize.BIT64);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(DBColumnSize.BIT32);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(DBColumnSize.BIT16);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(DBColumnSize.BIT8);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(DBColumnSize.BIT1);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(DBColumnSize.BIT0);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        return elements;
    }

    /**
     * Returns size of the uncompressed buffer holding the numeric and boolean
     * values described by this object.
     *
     * @return size size of the uncompressed buffer holding the numeric and
     *          boolean values
     * @since   0.0.1
     */
    public final int size() {
        return this.size;
    }

    /**
     * Visits this <tt>PyDBRowDescriptor</tt> instance with PyVisitor.
     *
     * @param   visitor   visitor used
     * @return  <code>true</code> if the visit was successful;
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
     * @return  zero if both objects share the same <tt>PyDBColumn</tt>s;
     *          a positive value if this object's value is greater
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

            boolean same = false;

            List<PyDBColumn> cols1 = this.columns;
            List<PyDBColumn> cols2 = other.asDBRowDescriptor().columns;

            for (PyDBColumn pyDBColumn1 : cols1) {
                for (PyDBColumn pyDBColumn2 : cols2) {
                    if (pyDBColumn1.equals(pyDBColumn2)) {
                        same = true;
                        break;
                    }
                }
                if (!same) {
                    break;
                }
                same = false;
            }

            if (same) {
                return 0;
            } else if (this.columns.size()
                    > other.asDBRowDescriptor().columns.size()) {
                return 1;
            }
            return -1;
        } else {
            return other.getType().compareTo(this.getType());
        }
    }
}
