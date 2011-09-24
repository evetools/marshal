package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.Reader;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * 
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class ParsingTest {

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testReadfd4f() throws Exception {
        InputStream in = ParsingTest.class.getResourceAsStream("/fd4f.cache");
        assertNotNull(in);
        Reader reader = new Reader(in);
        assertNotNull(reader.read());
    }

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testRead67b3() throws Exception {
        InputStream in = ParsingTest.class.getResourceAsStream("/67b3.cache");
        assertNotNull(in);
        Reader reader = new Reader(in);
        assertNotNull(reader.read());
    }

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testRead8d17() throws Exception {
        InputStream in = ParsingTest.class.getResourceAsStream("/8d17.cache");
        assertNotNull(in);
        Reader reader = new Reader(in);
        assertNotNull(reader.read());
    }
}
