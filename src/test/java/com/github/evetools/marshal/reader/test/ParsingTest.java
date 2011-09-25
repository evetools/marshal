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

import org.junit.Assert;
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
     * @throws Exception
     *             on error
     */
    @Test
    public final void testReadfd4f() throws Exception {
        
        InputStream in = ParsingTest.class.getResourceAsStream("/fd4f.cache");
        assertNotNull(in);
        Reader reader = new Reader(in);
        PyBase PyBase = reader.read();
        assertNotNull(PyBase);

        File file = File.createTempFile("eve", "decoded");
        FileOutputStream ostream = new FileOutputStream(file);
        PyDumpVisitor visitor = new PyDumpVisitor(ostream);
        PyBase.visit(visitor);
        ostream.close();

        InputStream istream = new FileInputStream(file);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        istream = new DigestInputStream(istream, digest);
        int res = istream.read();
        while (res != -1) {
            res = istream.read();
        }
        istream.close();

        byte[] bytes = digest.digest();

        String expected = "14731ac211033cd4c78e752644c92af3";
        String calculated = "";

        for (byte b : bytes) {
            String formatted = String.format("%02x", (b & 0xFF));
            calculated += formatted;
        }

        //Assert.assertEquals("MD5 missmatch", expected, calculated);
        
    }

    /**
     * Runs test.
     * @throws Exception
     *             on error
     */
    @Test
    public final void testRead67b3() throws Exception {
        
        InputStream in = ParsingTest.class.getResourceAsStream("/67b3.cache");
        assertNotNull(in);
        Reader reader = new Reader(in);
        PyBase PyBase = reader.read();
        assertNotNull(PyBase);

        File file = File.createTempFile("eve", "decoded");
        FileOutputStream ostream = new FileOutputStream(file);
        PyDumpVisitor visitor = new PyDumpVisitor(ostream);
        PyBase.visit(visitor);
        ostream.close();

        InputStream istream = new FileInputStream(file);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        istream = new DigestInputStream(istream, digest);
        int res = istream.read();
        while (res != -1) {
            res = istream.read();
        }
        istream.close();

        byte[] bytes = digest.digest();

        String expected = "e02e4e5022beff04ddbe892f54690a11";
        String calculated = "";

        for (byte b : bytes) {
            String formatted = String.format("%02x", (b & 0xFF));
            calculated += formatted;
        }

        //Assert.assertEquals("MD5 missmatch", expected, calculated);
    }

    /**
     * Runs test.
     * @throws Exception
     *             on error
     */
    @Test
    public final void testRead8d17() throws Exception {

        InputStream in = ParsingTest.class.getResourceAsStream("/8d17.cache");
        assertNotNull(in);
        Reader reader = new Reader(in);
        PyBase PyBase = reader.read();
        assertNotNull(PyBase);

        File file = File.createTempFile("eve", "decoded");
        FileOutputStream ostream = new FileOutputStream(file);
        PyDumpVisitor visitor = new PyDumpVisitor(ostream);
        PyBase.visit(visitor);
        ostream.close();

        InputStream istream = new FileInputStream(file);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        istream = new DigestInputStream(istream, digest);
        int res = istream.read();
        while (res != -1) {
            res = istream.read();
        }
        istream.close();

        byte[] bytes = digest.digest();

        String expected = "4d6d495e27499a869869a86eb8681d7b";
        String calculated = "";

        for (byte b : bytes) {
            String formatted = String.format("%02x", (b & 0xFF));
            calculated += formatted;
        }

        //Assert.assertEquals("MD5 missmatch", expected, calculated);
        
    }
}
