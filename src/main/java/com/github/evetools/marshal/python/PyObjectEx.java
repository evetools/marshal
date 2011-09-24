package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyObjectEx extends PyBase {

    /**
     * dict.
     */
    private PyDict dict;

    /**
     * head.
     */
    private PyBase head;

    /**
     * list.
     */
    private PyList list;

    /**
     * reduce style object.
     */
    private boolean reduce;

    /**
     * PyObjectEx.
     * @param red reduce style object
     */
    public PyObjectEx(final boolean red) {
        super(PyType.OBJECTEX);
        this.head = null;
        this.reduce = red;
        this.list = new PyList();
        this.dict = new PyDict();
    }

    /**
     * Is reduce object.
     * @return reduce object flag
     */
    public final boolean isReduce() {
        return reduce;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final PyObjectEx other = (PyObjectEx) obj;
        if (this.dict == null) {
            if (other.dict != null) {
                return false;
            }
        } else if (!this.dict.equals(other.dict)) {
            return false;
        }
        if (this.head == null) {
            if (other.head != null) {
                return false;
            }
        } else if (!this.head.equals(other.head)) {
            return false;
        }
        if (this.list == null) {
            if (other.list != null) {
                return false;
            }
        } else if (!this.list.equals(other.list)) {
            return false;
        }
        return true;
    }

    /**
     * Returns dict.
     * @return dict
     */
    public final PyDict getDict() {
        return this.dict;
    }

    /**
     * Returns head.
     * @return head
     */
    public final PyBase getHead() {
        return this.head;
    }

    /**
     * Returns list.
     * @return list
     */
    public final PyList getList() {
        return this.list;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = this.getType().hashCode();
        result = (prime * result);
        if (this.dict != null) {
            result += this.dict.hashCode();
        }
        if (this.head != null) {
            result += this.head.hashCode();
        }
        if (this.list != null) {
            result += this.list.hashCode();
        }
        return result;
    }

    /**
     * Set head.
     * @param header head
     */
    public final void setHead(final PyBase header) {
        this.head = header;
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            return Integer.valueOf(o.asObjectEx().hashCode()).compareTo(
                    this.hashCode());
        } else {
            return 1;
        }
    }

}
