package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public abstract class PyBase  implements Comparable<PyBase>, PyVisitable {

    /**
     * PyType.
     */
    public static enum PyType {
        /**
         * BOOL.
         */
        BOOL,
        /**
         * BUFFER.
         */
        BUFFER,
        /**
         * CONTAINER.
         */
        CONTAINER,
        /**
         * DBROWDESCRIPTOR.
         */
        DBROWDESCRIPTOR,
        /**
         * DICT.
         */
        DICT,
        /**
         * DOUBLE.
         */
        DOUBLE,
        /**
         * GLOBAL.
         */
        GLOBAL,
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
         * INT8.
         */
        INT8,
        /**
         * LIST.
         */
        LIST,
        /**
         * MARKER.
         */
        MARKER,
        /**
         * NONE.
         */
        NONE,
        /**
         * OBJECT.
         */
        OBJECT,
        /**
         * OBJECTEX.
         */
        OBJECTEX,
        /**
         * DBROW.
         */
        DBROW,
        /**
         * STRING.
         */
        STRING,
        /**
         * TUPLE.
         */
        TUPLE,
        /**
         * UNKNOWN.
         */
        UNKNOWN,
        /**
         * DBCOLUMN.
         */
        DBCOLUMN
    };

    /**
     * Windows offset.
     */
    private static final long WINDOWSOFFSET = 11644473600000L;

    /**
     * Millsec.
     */
    private static final long MILLSEC = 10000L;

    /**
     * Convert windows timestamp to java millsec.
     * @param timeStamp timestamp
     * @return millsec
     */
    public static long convertWindowsTime(final long timeStamp) {
        return (timeStamp / MILLSEC) - WINDOWSOFFSET;
    }

    /**
     * PyType.
     */
    private PyType type = PyType.UNKNOWN;

    /**
     * PyBase.
     * @param pyType type
     */
    protected PyBase(final PyType pyType) {
        this.type = pyType;
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isObject() {
        return (this.type == PyType.OBJECT);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyObject asObject() {
        if (this.isObject()) {
            return (PyObject) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isObjectEx() {
        return (this.type == PyType.OBJECTEX);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyObjectEx asObjectEx() {
        if (this.isObjectEx()) {
            return (PyObjectEx) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isDBRowDescriptor() {
        return (this.type == PyType.DBROWDESCRIPTOR);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyDBRowDescriptor asDBRowDescriptor() {
        if (this.isDBRowDescriptor()) {
            return (PyDBRowDescriptor) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isDBColumn() {
        return (this.type == PyType.DBCOLUMN);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyDBColumn asDBColumn() {
        if (this.isDBColumn()) {
            return (PyDBColumn) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isDBRow() {
        return (this.type == PyType.DBROW);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyDBRow asDBRow() {
        if (this.isDBRow()) {
            return (PyDBRow) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isNone() {
        return (this.type == PyType.NONE);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyNone asNone() {
        if (this.isNone()) {
            return (PyNone) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isLong() {
        return (this.type == PyType.INT64);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyLong asLong() {
        if (this.isLong()) {
            return (PyLong) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isInt() {
        return (this.type == PyType.INT32);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyInt asInt() {
        if (this.isInt()) {
            return (PyInt) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isShort() {
        return (this.type == PyType.INT16);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyShort asShort() {
        if (this.isShort()) {
            return (PyShort) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isByte() {
        return (this.type == PyType.INT8);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyByte asByte() {
        if (this.isByte()) {
            return (PyByte) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isList() {
        return (this.type == PyType.LIST);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyList asList() {
        if (this.isList()) {
            return (PyList) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isDouble() {
        return (this.type == PyType.DOUBLE);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyDouble asDouble() {
        if (this.isDouble()) {
            return (PyDouble) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isDict() {
        return (this.type == PyType.DICT);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyDict asDict() {
        if (this.isDict()) {
            return (PyDict) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isBool() {
        return (this.type == PyType.BOOL);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyBool asBool() {
        if (this.isBool()) {
            return (PyBool) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isGlobal() {
        return (this.type == PyType.GLOBAL);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyGlobal asGlobal() {
        if (this.isGlobal()) {
            return (PyGlobal) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isTuple() {
        return (this.type == PyType.TUPLE);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyTuple asTuple() {
        if (this.isTuple()) {
            return (PyTuple) this;
        } else {
            return null;
        }
    }

    /**
     * Check for certain object..
     * @return result
     */
    public final boolean isBuffer() {
        return (this.type == PyType.BUFFER);
    }

    /**
     * Pybase as certain PyBase derived class.
     * @return object
     */
    public final PyBuffer asBuffer() {
        if (this.isBuffer()) {
            return (PyBuffer) this;
        } else {
            return null;
        }
    }

    /**
     * Get Python type.
     * @return type
     */
    public final PyType getType() {
        return this.type;
    }
}
