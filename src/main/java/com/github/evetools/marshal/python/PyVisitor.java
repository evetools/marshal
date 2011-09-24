package com.github.evetools.marshal.python;

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
     */
    boolean visit(PyBase base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyBool base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyBuffer base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyByte base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyContainer base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyDict base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyDouble base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyGlobal base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyInt base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyLong base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyMarker base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyNone base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyObject base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyObjectEx base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyDBRow base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyShort base);

    /**
     * Visits Python element. 
     * @param base PyBase element
     * @return status
     */
    boolean visit(PyDBColumn base);
}
