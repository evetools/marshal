package com.github.evetools.marshal.python;

import java.io.IOException;

import com.github.evetools.marshal.Reader.Buffer;
import com.github.evetools.marshal.python.PyBase.PyType;

/**
 * The PyDBColumn class represents a single database column.
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
     * The DBColumnSize enum is used for calculating the uncompressed buffer
     * size containing values that were compressed.
     * <p>
     * Copyright (C)2011 by Gregor Anders All rights reserved.
     * <p>
     * This code is free software; you can redistribute it and/or modify it
     * under the terms of the BSD license (see the file LICENSE.txt included
     * with the distribution).
     *
     * @since   0.0.1
     */
    public static enum DBColumnSize {
        /**
         * BIT64.
         */
        BIT64 {
            @Override
            public int size() {
                return Long.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT32.
         */
        BIT32 {
            @Override
            public int size() {
                return Integer.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT16.
         */
        BIT16 {
            @Override
            public int size() {
                return Short.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT8.
         */
        BIT8 {
            @Override
            public int size() {
                return Byte.SIZE;
            }
        },
        /**
         * BIT1. - bool type values are stored as a single bit.
         */
        BIT1 {
            @Override
            public int size() {
                return Byte.SIZE / Byte.SIZE;
            }
        },
        /**
         * BIT0.- String values are not part of the compressed part.
         */
        BIT0 {
            @Override
            public int size() {
                return 0;
            }
        };

        /**
         * Returns the byte size of this <tt>DBColumnSize</tt> enum as an
         * <code>int</code> primitive.
         *
         * @return  the primitive <code>int</code> representing the size in
         *          bytes.
         * @since   0.0.1
         */
        public abstract int size();
    }

    /**
     * The DBColumnType enum represents a database type.
     * An enum of type <code>DBColumnType</code> contains a
     * field whose type is <code>DBColumnSize</code> representing its size
     * in bytes in relation to their size. Please not that String values are
     * not part of the compressed buffer and are handled in a different place.
     * Also keep in mind that bool type values are stored in a single bit.
     * <p>
     * In addition, this enum provides methods for reading a value from a
     * Buffer.
     * <p>
     * Copyright (C)2011 by Gregor Anders All rights reserved.
     * <p>
     * This code is free software; you can redistribute it and/or modify it
     * under the terms of the BSD license (see the file LICENSE.txt included
     * with the distribution).
     *
     * @since   0.0.1
     */
    public static enum DBColumnType {
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
         * The internal identifier of the DBColumnType.
         */
        private final int type;

        /**
         * Allocates a <code>DBColumnType</code> enum representing the
         * <code>dbtype</code> argument.
         *
         * @param dbtype the value of the <code>DBColumnType</code>.
         * @since   0.0.1
         */
        private DBColumnType(final int dbtype) {
            this.type = dbtype;
        }

        /**
         * Returns the byte size of this <tt>DBColumnType</tt> enum as an
         * <code>int</code> primitive.
         *
         * @return  the primitive <code>int</code> representing the size in
         *          bytes.
         * @see     DBColumnSize#size()
         * @since   0.0.1
         */
        public abstract DBColumnSize size();

        /**
         * Returns a <code>PyBase</code> derived object representing this
         * <code>PyDBColumn</code>.
         *
         * @param   buffer buffer used to read from
         * @return  the primitive <code>int</code> representing the size in
         *          bytes.
         * @see     DBColumnSize#size()
         * @since   0.0.1
         */
        public abstract PyBase read(Buffer buffer);

        /**
         * Returns a <code>DBColumnType</code> enum represented by the
         * argument <code>type</code>.
         *
         * @param   type representing a database type
         * @return  <code>DBColumnType</code> type representing a column type.
         * @see     DBColumnSize#size()
         * @since   0.0.1
         */
        public static DBColumnType get(final int type) {
            for (DBColumnType t : values()) {
                if (t.type == type) {
                    return t;
                }
            }
            throw new IllegalArgumentException("DBColumnType not implemented");
        }
    }

    /**
     * The value representing the columns name.
     */
    private PyBase name;

    /**
     * The value representing the columns database type.
     */
    private final DBColumnType dbtype;

    /**
     * Allocates a <code>PyDBColumn</code> object representing a single
     * database columns of the type provided by the <code>type</code> argument
     * and with a name provided by the <code>columnName</code> argument.
     *
     * @param   type the database type of the <code>PyDBColumn</code>.
     * @param   columnName the name of the <code>PyDBColumn</code>.
     * @since   0.0.1
     */
    public PyDBColumn(final DBColumnType type, final PyBase columnName) {
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
     * @see     DBColumnType#hashCode()
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
    public final DBColumnType getDBType() {
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
