package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.Reader;
import java.io.InputStream;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class ParsingTest {

	@Test
	public void testRead_fd4f() throws Exception {
		InputStream in = ParsingTest.class.getResourceAsStream("/fd4f.cache");
		assertNotNull(in);
		Reader reader = new Reader(in);
		assertNotNull(reader.read());
	}

	@Test
	public void testRead_67b3() throws Exception {
		InputStream in = ParsingTest.class.getResourceAsStream("/67b3.cache");
		assertNotNull(in);
		Reader reader = new Reader(in);
		assertNotNull(reader.read());
	}

	@Test
	public void testRead_ce99() throws Exception {
		InputStream in = ParsingTest.class.getResourceAsStream("/ce99.cache");
		assertNotNull(in);
		Reader reader = new Reader(in);
		assertNotNull(reader.read());
	}
}
