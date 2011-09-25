package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.github.evetools.marshal.python.PyDBColumn.DBColumnSize;
import com.github.evetools.marshal.python.PyDBColumn.DBColumnType;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDBRowDescriptor extends PyBase {



    /**
     * DBRow size.
     */
    private int size;

    /**
     * DBRow columns.
     */
    private List<PyDBColumn> columns;

    /**
     * DBType map.
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
     * PyDBRowDescriptor.
     * @param object PyObjectEx
     * @throws IOException on error
     */
    public PyDBRowDescriptor(final PyObjectEx object) throws IOException {

        super(PyType.DBROWDESCRIPTOR);

        this.size = 0;

        this.columns = this.init(object);
    }

    /**
     * Returns columns.
     * @return columns
     */
    public final List<PyDBColumn> getColumns() {
        return columns;
    }

    /**
     * Initialize.
     * @param object PyObjectEx
     * @return columns
     * @throws IOException on error
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
                    throw new IOException("Invalid DBRowType header type");
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
     * Create columns according to typeMap.
     * @return columns
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
     * Returns size in bytes.
     * @return size
     */
    public final int size() {
        return this.size;
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            return Integer.valueOf(o.asDBRowDescriptor().hashCode()).compareTo(
                    this.hashCode());
        } else {
            return 1;
        }
    }

}
