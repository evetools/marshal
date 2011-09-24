package com.github.evetools.marshal.python;

import java.io.IOException;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public interface PyVisitable {

    /**
     * Visit element.
     * @param visitor visitor
     * @return result
     * @throws IOException on error
     */
    boolean visit(PyVisitor visitor) throws IOException;

}
