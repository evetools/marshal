package com.github.evetools.marshal.reader.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.github.evetools.marshal.reader.MarketOrderBests;
import java.io.InputStream;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class MarketOrdersBestsTest {

	@Test
	public void testRead() throws Exception {
		InputStream in = MarketOrdersBestsTest.class.getResourceAsStream("/15f9.cache");
		assertNotNull(in);
		MarketOrderBests marketOrderBests = new MarketOrderBests(in);
		marketOrderBests.read();
	}

}
