package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyObject extends PyBase {

    /**
     * Content.
     */
    private PyBase content;

    /**
     * Head.
     */
    private PyBase head;

    /**
     * PyObject.
     */
    public PyObject() {
        super(PyType.OBJECT);
    }

    /**
     * Set head.
     * @param header head
     */
    public final void setHead(final PyBase header) {
        this.head = header;
    }

    /**
     * Set content.
     * @param cont content
     */
    public final void setContent(final PyBase cont) {
        this.content = cont;
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
        final PyObject other = (PyObject) obj;
        if (this.content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!this.content.equals(other.content)) {
            return false;
        }
        if (this.head == null) {
            if (other.head != null) {
                return false;
            }
        } else if (!this.head.equals(other.head)) {
            return false;
        }
        return true;
    }

    /**
     * Returns content.
     * @return content
     */
    public final PyBase getContent() {
        return this.content;
    }

    /**
     * Returns head.
     * @return head
     */
    public final PyBase getHead() {
        return this.head;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = this.getType().hashCode();
        result = (prime * result);
        if (this.content != null) {
            result += this.content.hashCode();
        }
        if (this.head != null) {
            result += this.head.hashCode();
        }
        return result;
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            return Integer.valueOf(o.asObject().hashCode()).compareTo(
                    this.hashCode());
        } else {
            return 1;
        }
    }

}
