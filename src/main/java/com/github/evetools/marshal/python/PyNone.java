package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyNone extends PyBase {

    /**
     * PyNone.
     */
    public PyNone() {
        super(PyType.NONE);
    }

    @Override
    public final String toString() {
        return "None";
    }

    @Override
    public final boolean visit(final PyVisitor visitor) throws IOException {
        return (visitor.visit(this));
    }

    @Override
    public final int compareTo(final PyBase o) {
        if (o.getType() == this.getType()) {
            return Integer.valueOf(o.asNone().hashCode()).compareTo(
                    this.hashCode());
        } else {
            return 1;
        }
    }

}
