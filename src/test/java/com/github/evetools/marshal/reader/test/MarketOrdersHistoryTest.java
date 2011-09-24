package com.github.evetools.marshal.reader.test;

import com.github.evetools.marshal.reader.MarketOrdersHistory;
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
public class MarketOrdersHistoryTest {

    /**
     * Runs test.
     * 
     * @throws Exception
     *             on error
     */
    @Test
    public final void testRead() throws Exception {
        InputStream in = MarketOrdersBestsTest.class
                .getResourceAsStream("/5533.cache");
        assertNotNull(in);
        MarketOrdersHistory marketOrdersHistory = new MarketOrdersHistory(in);
        marketOrdersHistory.read();
    }

}
