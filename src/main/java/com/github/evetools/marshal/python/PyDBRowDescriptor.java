package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.github.evetools.marshal.Reader.Buffer;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDBRowDescriptor extends PyBase {

    /**
     * DBColumnSize.
     */
    public static enum DBColumnSize {
        /**
         * BIT64.
         */
        BIT64(0) {
            @Override
            public int size() {
                return Long.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT32.
         */
        BIT32(1) {
            @Override
            public int size() {
                return Integer.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT16.
         */
        BIT16(2) {
            @Override
            public int size() {
                return Short.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT8.
         */
        BIT8(3) {
            @Override
            public int size() {
                return Byte.SIZE;
            }
        },
        /**
         * BIT1.
         */
        BIT1(4) {
            @Override
            public int size() {
                return Byte.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT0.
         */
        BIT0(5) {
            @Override
            public int size() {
                return 0;
            }
        };

        /**
         * Type.
         */
        private final int type;

        /**
         * Returns size in bytes.
         * @return bytes
         */
        public abstract int size();

        /**
         * DBColumnSize.
         * @param dbsizetype db size type
         */
        private DBColumnSize(final int dbsizetype) {
            this.type = dbsizetype;
        }

        /**
         * Returns DBColumnSize for db sizs type.
         * @param idx db size type
         * @return DBColumnSize
         */
        public static DBColumnSize get(final int idx) {
            for (DBColumnSize t : values()) {
                if (t.type == idx) {
                    return t;
                }
            }
            throw new IllegalArgumentException("message here");
        }
    }

    /**
     * DBColumnTypes.
     */
    public static enum DBColumnTypes {
        /**
         * BOOL.
         */
        BOOL(11) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT1;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return null;
            }
        },
        /**
         * CURRENCY.
         */
        CURRENCY(6) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyLong(buffer.readLong());
            }
        },
        /**
         * DOUBLE.
         */
        DOUBLE(5) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyDouble(buffer.readDouble());
            }
        },
        /**
         * INT16.
         */
        INT16(2) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT16;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyShort(buffer.readShort());
            }
        },
        /**
         * INT32.
         */
        INT32(3) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT32;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyInt(buffer.readInt());
            }
        },
        /**
         * INT64.
         */
        INT64(20) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyLong(buffer.readLong());
            }
        },
        /**
         * STRING.
         */
        STRING(129) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT0;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return null;
            }
        },
        /**
         * UINT8.
         */
        UINT8(17) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT8;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyByte(buffer.readByte());
            }
        },
        /**
         * WINFILETIME.
         */
        WINFILETIME(64) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return new PyLong(buffer.readLong());
            }
        },
        /**
         * USTRING.
         */
        USTRING(130) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT0;
            }

            @Override
            public PyBase read(final Buffer buffer) {
                return null;
            }
        };

        /**
         * db type.
         */
        private final int type;

        /**
         * DBColumnTypes.
         * @param dbtype db type
         */
        private DBColumnTypes(final int dbtype) {
            this.type = dbtype;
        }

        /**
         * Returns db type.
         * @return db type
         */
        public int type() {
            return type;
        }

        /**
         * Returns db size.
         * @return dbsize
         */
        public abstract DBColumnSize size();

        /**
         * Read db value from buffer.
         * @param buffer buffer
         * @return pybase
         */
        public abstract PyBase read(Buffer buffer);

        /**
         * Returns db type.
         * @param idx db type
         * @return db type
         */
        public static DBColumnTypes get(final int idx) {
            for (DBColumnTypes t : values()) {
                if (t.type == idx) {
                    return t;
                }
            }
            throw new IllegalArgumentException("message here");
        }
    }

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
            put(DBColumnSize.BIT64, new ArrayList<PyDBColumn>()); // 64
            put(DBColumnSize.BIT32, new ArrayList<PyDBColumn>()); // 32
            put(DBColumnSize.BIT16, new ArrayList<PyDBColumn>()); // 16
            put(DBColumnSize.BIT8, new ArrayList<PyDBColumn>()); // 8
            put(DBColumnSize.BIT1, new ArrayList<PyDBColumn>()); // bool
            put(DBColumnSize.BIT0, new ArrayList<PyDBColumn>()); // String
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
                    dbtype = (((PyByte) info.get(1)).getValue());
                } else if (info.get(1) instanceof PyShort) {
                    dbtype = (((PyShort) info.get(1)).getValue());
                }

                DBColumnTypes columnType = DBColumnTypes.get(Integer
                        .valueOf(dbtype));

                if (columnType == DBColumnTypes.BOOL) {

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
                .get(PyDBRowDescriptor.DBColumnSize.BIT64);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(PyDBRowDescriptor.DBColumnSize.BIT32);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(PyDBRowDescriptor.DBColumnSize.BIT16);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(PyDBRowDescriptor.DBColumnSize.BIT8);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(PyDBRowDescriptor.DBColumnSize.BIT1);

        for (final PyDBColumn column : list) {
            elements.add(column);
        }

        list = typeMap.get(PyDBRowDescriptor.DBColumnSize.BIT0);

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
}
