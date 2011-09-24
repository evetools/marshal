package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.Reader;
import com.github.evetools.marshal.python.PyBase;
import com.github.evetools.marshal.python.PyDumpVisitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import static org.junit.Assert.assertNotNull;

import org.hamcrest.core.IsSame;
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
        PyBase pyBase = reader.read(); 
        assertNotNull(pyBase);
        
        //File file = File.createTempFile("eve", "decoded");
        File file = new File("/tmp/out.raw");
        FileOutputStream ostream = new FileOutputStream(file);
        PyDumpVisitor visitor = new PyDumpVisitor(ostream);
        pyBase.visit(visitor);
        ostream.close();
        
        InputStream istream = new FileInputStream(file);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        istream = new DigestInputStream(istream, digest);
        istream.close();
        
        byte[] bytes = digest.digest();
        
        System.out.println("bytes: " + bytes.length);
        
        for (byte b : bytes) {
            String formatted = String.format("%02x", b);
            System.out.print(formatted);
        }
        System.out.println("");
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
