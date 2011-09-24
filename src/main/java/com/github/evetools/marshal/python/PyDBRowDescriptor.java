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
 * 
 */
public class PyDBRowDescriptor extends PyBase {

    public static enum DBColumnSize {
        BIT64(0) {
            @Override
            public int size() {
                return Long.SIZE / Byte.SIZE;
            }
        },
        BIT32(1) {
            @Override
            public int size() {
                return Integer.SIZE / Byte.SIZE;
            }
        },
        BIT16(2) {
            @Override
            public int size() {
                return Short.SIZE / Byte.SIZE;
            }
        },
        BIT8(3) {
            @Override
            public int size() {
                return Byte.SIZE;
            }
        },
        BIT1(4) {
            @Override
            public int size() {
                return Byte.SIZE / Byte.SIZE;
            }
        },
        BIT0(5) {
            @Override
            public int size() {
                return 0;
            }
        },
        ;

        protected final int type;

        public abstract int size();

        private DBColumnSize(int type) {
            this.type = type;
        }

        public static DBColumnSize get(int idx) {
            for (DBColumnSize t : values()) {
                if (t.type == idx) {
                    return t;
                }
            }
            throw new IllegalArgumentException("message here");
        }
    }

    public static enum DBColumnTypes {
        BOOL(11) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT1;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return null;
            }
        },
        CURRENCY(6) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyLong(buffer.readLong());
            }
        },
        DOUBLE(5) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyDouble(buffer.readDouble());
            }
        },
        INT16(2) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT16;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyShort(buffer.readShort());
            }
        },
        INT32(3) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT32;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyInt(buffer.readInt());
            }
        },
        INT64(20) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyLong(buffer.readLong());
            }
        },
        STRING(129) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT0;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return null;
            }
        },
        UINT8(17) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT8;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyByte(buffer.readByte());
            }
        },
        WINFILETIME(64) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT64;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return new PyLong(buffer.readLong());
            }
        },
        USTRING(130) {
            @Override
            public DBColumnSize size() {
                return DBColumnSize.BIT0;
            }

            @Override
            public PyBase read(Buffer buffer) {
                return null;
            }
        };

        protected final int type;

        private DBColumnTypes(int type) {
            this.type = type;
        }

        public int type() {
            return type;
        }

        public abstract DBColumnSize size();

        public abstract PyBase read(Buffer buffer);

        public static DBColumnTypes get(int idx) {
            for (DBColumnTypes t : values()) {
                if (t.type == idx) {
                    return t;
                }
            }
            throw new IllegalArgumentException("message here");
        }
    }

    private int size;

    private List<PyDBColumn> columns;

    private SortedMap<DBColumnSize, List<PyDBColumn>> typeMap = new TreeMap<DBColumnSize, List<PyDBColumn>>() {

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

    public PyDBRowDescriptor(PyObjectEx object) throws IOException {

        super(PyType.DBROWDESCRIPTOR);

        this.size = 0;

        this.columns = this.init(object);
    }

    public List<PyDBColumn> getColumns() {
        return columns;
    }

    private List<PyDBColumn> init(PyObjectEx object) throws IOException {

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
                    if (boolcount == 8) {
                        boolcount = 0;
                    }

                } else {
                    size += columnType.size().size();
                }

                typeMap.get(columnType.size()).add(
                        new PyDBColumn(columnType, info.get(0)));
            }
        }

        return this.createColumns(typeMap);
    }

    private List<PyDBColumn> createColumns(
            SortedMap<DBColumnSize, List<PyDBColumn>> typeMap) {

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

    public int size() {
        return this.size;
    }
    
    @Override
    public final boolean visit(final PyVisitor visitor) {
        return (visitor.visit(this));
    }
}
