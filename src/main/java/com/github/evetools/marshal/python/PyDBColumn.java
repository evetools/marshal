package com.github.evetools.marshal.python;

import com.github.evetools.marshal.python.PyDBRowDescriptor.DBColumnTypes;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDBColumn extends PyBase {

    /**
     * Name.
     */
    private PyBase name;

    /**
     * DBColumnType.
     */
    private final DBColumnTypes dbtype;

    /**
     * PyDBColumn.
     * @param type db type
     */
    public PyDBColumn(final DBColumnTypes type) {
        super(PyType.DBCOLUMN);
        this.dbtype = type;
    }

    /**
     * PyDBColumn.
     * @param type db type
     * @param columnName column name
     */
    public PyDBColumn(final DBColumnTypes type, final PyBase columnName) {
        super(PyType.DBCOLUMN);
        this.dbtype = type;
        this.name = columnName;
    }

    /**
     * Returns name.
     * @return name
     */
    public final PyBase getName() {
        return name;
    }

    /**
     * Sets name.
     * @param columnName name
     */
    public final void setName(final PyBase columnName) {
        this.name = columnName;
    }

    /**
     * Returns db type.
     * @return db type
     */
    public final DBColumnTypes getDBType() {
        return dbtype;
    }

    @Override
    public final boolean visit(final PyVisitor visitor) {
        return (visitor.visit(this));
    }
};
