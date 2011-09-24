package com.github.evetools.marshal.python;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyDumpVisitor implements PyVisitor {

    /**
     * PyDumpVisitor.
     * @param ostream output stream
     */
    public PyDumpVisitor(final OutputStream ostream) {
        this.stream = ostream;
    }
    
    /**
     * OutputStream.
     */
    private OutputStream stream;
    
    /**
     * indent.
     */
    private int indent = 0;

    /**
     * Push indent.
     */
    protected final void popIndent() {
        this.indent--;
    }

    /**
     * Prints PyBase.
     * @param base object
     * @throws IOException on error
     */
    protected final void print(final PyBase base) throws IOException {
        this.print(base.toString());
    }

    /**
     * Space.
     */
    private static final int SPACE = 32;
    
    /**
     * Prints PyBase.
     * @param string string
     * @throws IOException on error
     */
    protected final void print(final String string) throws IOException {
        for (int loop = 0; loop < this.indent; loop++) {
            this.stream.write(SPACE);
        }
        this.stream.write(string.getBytes());
        this.stream.write(System.getProperty("line.separator").getBytes());
    }

    /**
     * Push indent.
     */
    protected final void pushIndent() {
        this.indent++;
    }

    @Override
    public final boolean visit(final PyBase base) throws IOException {

        if (base != null) {
            this.print(base.getType().toString());
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyBool base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyBuffer buffer) throws IOException {

        if (buffer != null) {
            this.print(buffer);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyByte base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyContainer container) throws IOException {

        if (container != null) {

            this.print(container.getType().toString() + " [" + container.size()
                    + "]");
            this.pushIndent();
            for (final Iterator<PyBase> iterator = container.iterator();
                    iterator.hasNext();) {
                final PyBase type = iterator.next();
                if (type != null) {
                    type.visit(this);
                }
            }
            this.popIndent();

            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyDict container) throws IOException {

        if (container != null) {

            this.print(container.getType().toString() + " [" + container.size()
                    + "]");
            this.pushIndent();
            for (final Map.Entry<PyBase, PyBase> entry : container.entrySet()) {
                entry.getKey().visit(this);
                this.pushIndent();
                entry.getValue().visit(this);
                this.popIndent();
            }
            this.popIndent();

            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyDouble base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyGlobal base1) throws IOException {
        if (base1 != null) {
            this.print(base1.getType().toString());
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyInt base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyLong base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyMarker base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyNone base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyDBColumn base1) throws IOException {
        if (base1 != null) {
            this.print(base1.getName());
            this.pushIndent();
            this.print(base1.getDBType().name());
            this.popIndent();
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyObject base1) throws IOException {

        if (base1 != null) {
            this.print(base1.getType().toString());
            this.pushIndent();
            this.print(base1.getType().toString() + "-HEADER");
            this.pushIndent();
            base1.getHead().visit(this);
            this.popIndent();
            this.print(base1.getType().toString() + "-CONTENT");
            this.pushIndent();
            base1.getContent().visit(this);
            this.popIndent();
            this.popIndent();
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyObjectEx base1) throws IOException {

        if (base1 != null) {

            this.print(base1.getType().toString());
            this.pushIndent();

            this.print(base1.getType().toString() + "-HEADER");
            this.pushIndent();
            base1.getHead().visit(this);
            this.popIndent();

            this.print(base1.getType().toString() + "-LIST");
            this.pushIndent();
            base1.getList().visit(this);
            this.popIndent();

            this.print(base1.getType().toString() + "-DICT");
            this.pushIndent();
            base1.getDict().visit(this);
            this.popIndent();

            this.popIndent();

            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyDBRow base1) throws IOException {
        if (base1 != null) {
            this.print("DBRow");
            this.pushIndent();
            base1.getColumns().visit(this);
            this.popIndent();
            return true;
        }

        return false;
    }

    @Override
    public final boolean visit(final PyShort base1) throws IOException {
        if (base1 != null) {
            this.print(base1);
            return true;
        }

        return false;
    }
}
