package com.github.evetools.marshal.python;

/**
* The PyGlobal class extends <code>PyBuffer</code>.
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
public class PyGlobal extends PyBuffer {

    /**
     * Allocates a <code>PyGlobal</code> object representing the
     * <code>bytes</code> argument.
     *
     * @param bytes the value of the <code>PyGLobal</code>.
     * @since   0.0.1
     */
    public PyGlobal(final byte[] bytes) {
        super(PyType.GLOBAL, bytes);
    }

}
