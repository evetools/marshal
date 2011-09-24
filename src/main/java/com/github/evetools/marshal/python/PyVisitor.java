package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public interface PyVisitor {

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyBase base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyBool base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyBuffer base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyByte base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyContainer base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyDict base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyDouble base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyGlobal base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyInt base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyLong base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyNone base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyObject base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyObjectEx base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyDBRow base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyShort base) throws IOException;

    /**
     * Visits Python element.
     * @param base PyBase element
     * @return status
     * @throws IOException on error
     */
    boolean visit(PyDBColumn base) throws IOException;
}
