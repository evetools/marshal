package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.reader.MarketOrders;
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
public class MarketOrdersTest {

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testRead() throws Exception {
        InputStream in = MarketOrdersBestsTest.class
                .getResourceAsStream("/9128.cache");
        assertNotNull(in);
        MarketOrders marketOrders = new MarketOrders(in);
        marketOrders.read();
    }

}
