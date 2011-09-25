package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.python.PyBool;
import com.github.evetools.marshal.python.PyBuffer;
import com.github.evetools.marshal.python.PyByte;
import com.github.evetools.marshal.python.PyContainer;
import com.github.evetools.marshal.python.PyDict;
import com.github.evetools.marshal.python.PyDouble;
import com.github.evetools.marshal.python.PyInt;
import com.github.evetools.marshal.python.PyLong;
import com.github.evetools.marshal.python.PyNone;
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
    public final void testPyByte() throws Exception {

        PyByte pyByte1 = new PyByte((byte) 2);
        PyByte pyByte2 = new PyByte((byte) 2);
        PyByte pyByte3 = new PyByte((byte) 0);

        PyBuffer pyBuffer1 = new PyBuffer("test1");

        Assert.assertTrue(pyByte1.compareTo(pyByte1) == 0);
        Assert.assertTrue(pyByte1.compareTo(pyByte2) == 0);
        Assert.assertTrue(pyByte1.compareTo(pyByte3) == 1);
        Assert.assertTrue(pyByte3.compareTo(pyByte1) == -1);

        Assert.assertFalse(pyByte1.equals(pyBuffer1));
        Assert.assertTrue(pyByte1.equals(pyByte1));
        Assert.assertTrue(pyByte1.equals(pyByte2));
        Assert.assertFalse(pyByte1.equals(pyByte3));
    }

    /**
     * Runs test.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyShort() throws Exception {

        PyShort pyShort1 = new PyShort((short) 2);
        PyShort pyShort2 = new PyShort((short) 2);
        PyShort pyShort3 = new PyShort((short) 0);

        PyBuffer pyBuffer1 = new PyBuffer("test1");

        Assert.assertTrue(pyShort1.compareTo(pyShort1) == 0);
        Assert.assertTrue(pyShort1.compareTo(pyShort2) == 0);
        Assert.assertTrue(pyShort1.compareTo(pyShort3) > 1);
        Assert.assertTrue(pyShort3.compareTo(pyShort1) < 1);

        Assert.assertFalse(pyShort1.equals(pyBuffer1));
        Assert.assertTrue(pyShort1.equals(pyShort1));
        Assert.assertTrue(pyShort1.equals(pyShort2));
        Assert.assertFalse(pyShort1.equals(pyShort3));
    }

    /**
     * Runs test.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyBuffer() throws Exception {

        PyBuffer pyBase1 = new PyBuffer("test1");
        PyBuffer pyBase2 = new PyBuffer("test1");
        PyBuffer pyBase3 = new PyBuffer("test0");

        PyShort pyShort1 = new PyShort((short) 2);

        Assert.assertEquals(pyBase1, pyBase2);
        Assert.assertFalse(pyBase1.equals(pyBase3));
        Assert.assertFalse(pyBase1.equals(pyShort1));

        Assert.assertFalse(pyBase1.compareTo(pyBase3) == 0);
        Assert.assertFalse(pyBase1.compareTo(pyShort1) == 0);
    }

    /**
     * Runs test.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyNone() throws Exception {

        PyNone pyBase1 = new PyNone();
        PyNone pyBase2 = new PyNone();

        PyShort pyShort1 = new PyShort((short) 2);
        PyShort pyShort2 = new PyShort((short) 1);

        Assert.assertEquals(pyBase1, pyBase2);
        Assert.assertTrue(pyBase1.equals(null));
        Assert.assertFalse(pyBase1.equals(pyShort1));

        Assert.assertTrue(pyBase1.compareTo(null) == 0);
        Assert.assertFalse(pyBase1.compareTo(pyShort1) == 0);

        PyDict dict = new PyDict();
        dict.put(pyBase1, pyShort1);
        dict.put(pyBase1, pyShort2);

        Assert.assertTrue(pyBase1.hashCode() == pyBase2.hashCode());
    }

    /**
     * Runs test.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyInt() throws Exception {

        PyInt pyBase1 = new PyInt(2);
        PyInt pyBase2 = new PyInt(2);
        PyInt pyBase3 = new PyInt(0);

        PyShort pyShort1 = new PyShort((short) 2);

        Assert.assertEquals(pyBase1, pyBase2);
        Assert.assertFalse(pyBase1.equals(pyBase3));
        Assert.assertFalse(pyBase1.equals(pyShort1));

        Assert.assertFalse(pyBase1.compareTo(pyBase3) == 0);
        Assert.assertFalse(pyBase1.compareTo(pyShort1) == 0);

        Assert.assertTrue(pyBase1.hashCode() == pyBase2.hashCode());
        Assert.assertTrue(pyBase1.hashCode() != pyBase3.hashCode());
    }

    /**
     * Runs test.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyLong() throws Exception {

        PyLong pyBase1 = new PyLong(2);
        PyLong pyBase2 = new PyLong(2);
        PyLong pyBase3 = new PyLong(0);

        PyShort pyShort1 = new PyShort((short) 2);

        Assert.assertEquals(pyBase1, pyBase2);
        Assert.assertFalse(pyBase1.equals(pyBase3));
        Assert.assertFalse(pyBase1.equals(pyShort1));

        Assert.assertFalse(pyBase1.compareTo(pyBase3) == 0);
        Assert.assertFalse(pyBase1.compareTo(pyShort1) == 0);

        Assert.assertTrue(pyBase1.hashCode() == pyBase2.hashCode());
        Assert.assertTrue(pyBase1.hashCode() != pyBase3.hashCode());
    }

    /**
     * Runs test.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public final void testPyBool() throws Exception {

        PyBool pyBase1 = new PyBool(true);
        PyBool pyBase2 = new PyBool(true);
        PyBool pyBase3 = new PyBool(false);

        PyShort pyShort1 = new PyShort((short) 2);
        PyLong  pyLong1 = new PyLong(2);

        Assert.assertEquals(pyBase1, pyBase2);
        Assert.assertFalse(pyBase1.equals(pyBase3));
        Assert.assertFalse(pyBase1.equals(pyShort1));
        Assert.assertFalse(pyBase1.equals(pyLong1));

        Assert.assertFalse(pyBase1.compareTo(pyBase3) == 0);
        Assert.assertFalse(pyBase1.compareTo(pyShort1) == 0);
        Assert.assertFalse(pyBase1.compareTo(pyLong1) == 0);

        Assert.assertTrue(pyBase1.hashCode() == pyBase2.hashCode());
        Assert.assertTrue(pyBase1.hashCode() != pyBase3.hashCode());
    }
}
