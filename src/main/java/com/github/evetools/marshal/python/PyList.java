package com.github.evetools.marshal.python;

/**
* The PyList class extends <code>PyContainer</code> representing a
* <code>List</code> of <code>PyBase</code> derived objects.
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
public class PyList extends PyContainer {

    /**
     * Allocates a <code>PyList</code> object.
     *
     * @since   0.0.1
     */
    public PyList() {
        super(PyType.LIST);
    }
}
