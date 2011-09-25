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
     * Represents the different <tt>Python</tt> or specific types implemented.
     *
     * Do not change the order. Always append. Order is used in implementation
     * of compare methods.
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
         * CONTAINER.
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
         * STRING.
         */
        UNKNOWN,
        /**
         * DBCOLUMN.
         */
        DBCOLUMN
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
     * PyBase.
     * @param pyType type
     */
    protected PyBase(final PyType pyType) {
        this.type = pyType;
    }

    /**
     * Check for certain object.
     * @return result
     */
    public final boolean isObject() {
        return (this.type == PyType.OBJECT);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isObjectEx() {
        return (this.type == PyType.OBJECTEX);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isDBRowDescriptor() {
        return (this.type == PyType.DBROWDESCRIPTOR);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isDBColumn() {
        return (this.type == PyType.DBCOLUMN);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isDBRow() {
        return (this.type == PyType.DBROW);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isNone() {
        return (this.type == PyType.NONE);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isLong() {
        return (this.type == PyType.INT64);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isInt() {
        return (this.type == PyType.INT32);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isShort() {
        return (this.type == PyType.INT16);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isByte() {
        return (this.type == PyType.INT8);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isList() {
        return (this.type == PyType.LIST);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isDouble() {
        return (this.type == PyType.DOUBLE);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isDict() {
        return (this.type == PyType.DICT);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isBool() {
        return (this.type == PyType.BOOL);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isGlobal() {
        return (this.type == PyType.GLOBAL);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isTuple() {
        return (this.type == PyType.TUPLE);
    }

    /**
     * PyBase as certain PyBase derived class.
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
     * Check for certain object.
     * @return result
     */
    public final boolean isBuffer() {
        return (this.type == PyType.BUFFER);
    }

    /**
     * PyBase as certain PyBase derived class.
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
