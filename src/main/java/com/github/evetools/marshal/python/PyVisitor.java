package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public interface PyVisitor {

	boolean visit(PyBase base);

	boolean visit(PyBool base1);

	boolean visit(PyBuffer buffer);

	boolean visit(PyByte base);

	boolean visit(PyContainer container);

	boolean visit(PyDict container);

	boolean visit(PyDouble base);

	boolean visit(PyGlobal base);

	boolean visit(PyInt base);

	boolean visit(PyLong base);

	boolean visit(PyMarker base);

	boolean visit(PyNone base);

	boolean visit(PyObject base);

	boolean visit(PyObjectEx base);

	boolean visit(PyPackedRow base);

	boolean visit(PyShort base);

	boolean visit(PyString string);
}
