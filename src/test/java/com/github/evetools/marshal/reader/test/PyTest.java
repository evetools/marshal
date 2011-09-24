package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.python.PyBuffer;
import com.github.evetools.marshal.python.PyByte;
import com.github.evetools.marshal.python.PyContainer;
import com.github.evetools.marshal.python.PyDouble;
import com.github.evetools.marshal.python.PyInt;
import com.github.evetools.marshal.python.PyLong;
import com.github.evetools.marshal.python.PyShort;
import com.github.evetools.marshal.python.PyTuple;

import org.junit.Assert;
import org.junit.Test;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * 
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class PyTest {

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyTuple() throws Exception {

        PyTuple tuple1 = new PyTuple();
        PyTuple tuple2 = new PyTuple();
        PyTuple tuple3 = new PyTuple();

        PyBuffer buffer1 = new PyBuffer("test");
        PyBuffer buffer2 = new PyBuffer("test1");

        tuple1.add(buffer1);
        tuple2.add(buffer2);

        tuple1.compareTo(tuple2);

        tuple1.compareTo(buffer1);

        if (tuple1 instanceof PyContainer) {
            tuple1.compareTo(tuple3);
        }
    }

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyNumeric() throws Exception {

        PyByte pyByte1 = new PyByte((byte) 1);
        PyByte pyByte2 = new PyByte((byte) 2);
        PyByte pyByte3 = new PyByte((byte) 1);

        PyShort pyShort1 = new PyShort((short) 1);
        PyShort pyShort2 = new PyShort((short) 2);
        PyShort pyShort3 = new PyShort((short) 1);

        PyInt pyInt1 = new PyInt(1);
        PyInt pyInt2 = new PyInt(2);
        PyInt pyInt3 = new PyInt(1);

        PyLong pyLong1 = new PyLong(1);
        PyLong pyLong2 = new PyLong(2);
        PyLong pyLong3 = new PyLong(1);

        PyDouble pyDouble1 = new PyDouble(1);
        PyDouble pyDouble2 = new PyDouble(2);
        PyDouble pyDouble3 = new PyDouble(1);

        PyBuffer pyBuffer1 = new PyBuffer("test1");
        PyBuffer pyBuffer2 = new PyBuffer("test2");
        PyBuffer pyBuffer3 = new PyBuffer("test1");
    }
}
