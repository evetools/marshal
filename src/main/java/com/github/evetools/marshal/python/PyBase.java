package com.github.evetools.marshal.python;

/**
 * The PyBase as an abstract class wraps a <code>Python</code> or specific
 * type into a object.
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
public abstract class PyBase  implements Comparable<PyBase>, PyVisitable {

    /**
     * Represents the different <tt>Python</tt> or specific types implemented.
     *
     * Do not change the order.
     * Always append.
     * Order is used in implementation of compare methods.
     */
    public static enum PyType {
        /**
         * NONE.
         */
        NONE,
        /**
         * MARKER.
         */
        MARKER,
        /**
         * BOOL.
         */
        BOOL,
        /**
         * INT8.
         */
        INT8,
        /**
         * INT16.
         */
        INT16,
        /**
         * INT32.
         */
        INT32,
        /**
         * INT64.
         */
        INT64,
        /**
         * DOUBLE.
         */
        DOUBLE,
        /**
         * BUFFER.
         */
        BUFFER,
        /**
         * ABSTRACT CONTAINER.
         */
        CONTAINER,
        /**
         * LIST.
         */
        LIST,
        /**
         * TUPLE.
         */
        TUPLE,
        /**
         * DICT.
         */
        DICT,
        /**
         * OBJECT.
         */
        OBJECT,
        /**
         * OBJECTEX.
         */
        OBJECTEX,
        /**
         * GLOBAL.
         */
        GLOBAL,
        /**
         * DBROWDESCRIPTOR.
         */
        DBROWDESCRIPTOR,
        /**
         * DBROW.
         */
        DBROW,
        /**
         * DBCOLUMN.
         */
        DBCOLUMN,
        /**
         * UNKNOWN.
         */
        UNKNOWN
    };

    /**
     * Windows offset used to convert WINFILE time.
     */
    private static final long WINDOWSOFFSET = 11644473600000L;

    /**
     * Millsec used to convert WINFILE time.
     */
    private static final long MILLSEC = 10000L;

    /**
     * Converts WINFILE time to millsec used in JAVA.
     * @param   timeStamp WINFILE time to convert
     * @return  millsec
     */
    public static long convertWindowsTime(final long timeStamp) {
        return (timeStamp / MILLSEC) - WINDOWSOFFSET;
    }

    /**
     * <code>PyType</code> this object is representing.
     */
    private PyType type = PyType.UNKNOWN;

    /**
     * Allocates a <code>PyBase</code> object representing the
     * <code>pyType</code> argument.
     *
     * @param   pyType the value of the <code>PyType</code>.
     * @since   0.0.1
     */
    protected PyBase(final PyType pyType) {
        this.type = pyType;
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#OBJECT}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#OBJECT}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isObject() {
        return (this.type == PyType.OBJECT);
    }

    /**
     * Returns <code>{@link PyObject}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#OBJECT}</tt>.
     *
     * @return <code>{@link PyObject}</code> if {@link #getType()} is
     * <tt>{@link PyType#OBJECT}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyObject asObject() {
        if (this.isObject()) {
            return (PyObject) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#OBJECTEX}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#OBJECTEX}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isObjectEx() {
        return (this.type == PyType.OBJECTEX);
    }

    /**
     * Returns <code>{@link PyObjectEx}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#OBJECTEX}</tt>.
     *
     * @return <code>{@link PyObject}</code> if {@link #getType()} is
     * <tt>{@link PyType#OBJECTEX}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyObjectEx asObjectEx() {
        if (this.isObjectEx()) {
            return (PyObjectEx) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#DBROWDESCRIPTOR}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#DBROWDESCRIPTOR}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isDBRowDescriptor() {
        return (this.type == PyType.DBROWDESCRIPTOR);
    }

    /**
     * Returns <code>{@link PyDBRowDescriptor}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#DBROWDESCRIPTOR}</tt>.
     *
     * @return <code>{@link PyDBRowDescriptor}</code> if {@link #getType()} is
     * <tt>{@link PyType#DBROWDESCRIPTOR}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyDBRowDescriptor asDBRowDescriptor() {
        if (this.isDBRowDescriptor()) {
            return (PyDBRowDescriptor) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#DBCOLUMN}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#DBCOLUMN}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isDBColumn() {
        return (this.type == PyType.DBCOLUMN);
    }

    /**
     * Returns <code>{@link PyDBColumn}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#DBCOLUMN}</tt>.
     *
     * @return <code>{@link PyDBColumn}</code> if {@link #getType()} is
     * <tt>{@link PyType#DBCOLUMN}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyDBColumn asDBColumn() {
        if (this.isDBColumn()) {
            return (PyDBColumn) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#DBROW}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#DBROW}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isDBRow() {
        return (this.type == PyType.DBROW);
    }

    /**
     * Returns <code>{@link PyDBRow}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#DBROW}</tt>.
     *
     * @return <code>{@link PyDBRow}</code> if {@link #getType()} is
     * <tt>{@link PyType#DBROW}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyDBRow asDBRow() {
        if (this.isDBRow()) {
            return (PyDBRow) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#NONE}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#NONE}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isNone() {
        return (this.type == PyType.NONE);
    }

    /**
     * Returns <code>{@link PyNone}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#NONE}</tt>.
     *
     * @return <code>{@link PyNone}</code> if {@link #getType()} is
     * <tt>{@link PyType#NONE}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyNone asNone() {
        if (this.isNone()) {
            return (PyNone) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#INT64}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#INT64}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isLong() {
        return (this.type == PyType.INT64);
    }

    /**
     * Returns <code>{@link PyLong}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#INT64}</tt>.
     *
     * @return <code>{@link PyLong}</code> if {@link #getType()} is
     * <tt>{@link PyType#INT64}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyLong asLong() {
        if (this.isLong()) {
            return (PyLong) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#INT32}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#INT32}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isInt() {
        return (this.type == PyType.INT32);
    }

    /**
     * Returns <code>{@link PyInt}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#INT32}</tt>.
     *
     * @return <code>{@link PyInt}</code> if {@link #getType()} is
     * <tt>{@link PyType#INT32}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyInt asInt() {
        if (this.isInt()) {
            return (PyInt) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#INT16}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#INT16}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isShort() {
        return (this.type == PyType.INT16);
    }

    /**
     * Returns <code>{@link PyShort}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#INT16}</tt>.
     *
     * @return <code>{@link PyShort}</code> if {@link #getType()} is
     * <tt>{@link PyType#INT16}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyShort asShort() {
        if (this.isShort()) {
            return (PyShort) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#INT8}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#INT8}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isByte() {
        return (this.type == PyType.INT8);
    }

    /**
     * Returns <code>{@link PyByte}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#INT8}</tt>.
     *
     * @return <code>{@link PyByte}</code> if {@link #getType()} is
     * <tt>{@link PyType#INT8}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyByte asByte() {
        if (this.isByte()) {
            return (PyByte) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#LIST}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#LIST}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isList() {
        return (this.type == PyType.LIST);
    }

    /**
     * Returns <code>{@link PyList}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#LIST}</tt>.
     *
     * @return <code>{@link PyList}</code> if {@link #getType()} is
     * <tt>{@link PyType#LIST}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyList asList() {
        if (this.isList()) {
            return (PyList) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#DOUBLE}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#DOUBLE}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isDouble() {
        return (this.type == PyType.DOUBLE);
    }

    /**
     * Returns <code>{@link PyDouble}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#DOUBLE}</tt>.
     *
     * @return <code>{@link PyDouble}</code> if {@link #getType()} is
     * <tt>{@link PyType#DOUBLE}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyDouble asDouble() {
        if (this.isDouble()) {
            return (PyDouble) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#DICT}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#DICT}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isDict() {
        return (this.type == PyType.DICT);
    }

    /**
     * Returns <code>{@link PyDict}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#DICT}</tt>.
     *
     * @return <code>{@link PyDict}</code> if {@link #getType()} is
     * <tt>{@link PyType#DICT}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyDict asDict() {
        if (this.isDict()) {
            return (PyDict) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#BOOL}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#BOOL}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isBool() {
        return (this.type == PyType.BOOL);
    }

    /**
     * Returns <code>{@link PyBool}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#BOOL}</tt>.
     *
     * @return <code>{@link PyBool}</code> if {@link #getType()} is
     * <tt>{@link PyType#BOOL}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyBool asBool() {
        if (this.isBool()) {
            return (PyBool) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#GLOBAL}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#GLOBAL}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isGlobal() {
        return (this.type == PyType.GLOBAL);
    }

    /**
     * Returns <code>{@link PyGlobal}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#GLOBAL}</tt>.
     *
     * @return <code>{@link PyGlobal}</code> if {@link #getType()} is
     * <tt>{@link PyType#GLOBAL}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyGlobal asGlobal() {
        if (this.isGlobal()) {
            return (PyGlobal) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#TUPLE}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#TUPLE}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isTuple() {
        return (this.type == PyType.TUPLE);
    }

    /**
     * Returns <code>{@link PyTuple}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#TUPLE}</tt>.
     *
     * @return <code>{@link PyTuple}</code> if {@link #getType()} is
     * <tt>{@link PyType#TUPLE}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyTuple asTuple() {
        if (this.isTuple()) {
            return (PyTuple) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <tt>true</tt> if, and only if, {@link #getType()} is
     * <tt>{@link PyType#BUFFER}</tt>.
     *
     * @return <tt>true</tt> if {@link #getType()} is
     * <tt>{@link PyType#BUFFER}</tt>, otherwise <tt>false</tt>
     *
     * @since 0.0.1
     */
    public final boolean isBuffer() {
        return (this.type == PyType.BUFFER);
    }

    /**
     * Returns <code>{@link PyBuffer}</code> if, and only if,
     * {@link #getType()} is <tt>{@link PyType#BUFFER}</tt>.
     *
     * @return <code>{@link PyBuffer}</code> if {@link #getType()} is
     * <tt>{@link PyType#BUFFER}</tt>, otherwise <tt>null</tt>
     *
     * @since 0.0.1
     */
    public final PyBuffer asBuffer() {
        if (this.isBuffer()) {
            return (PyBuffer) this;
        } else {
            return null;
        }
    }

    /**
     * Returns <code>{@link PyType}</code> this object represents.
     *
     * @return <code>{@link PyType}</code> this object represents
     *
     * @since 0.0.1
     */
    public final PyType getType() {
        return this.type;
    }
}
