package com.github.evetools.marshal.reader.test;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.github.evetools.marshal.reader.MarketOrders;

public class MarketOrdersTest {

	@Test
	public void testRead() throws Exception {
		
		URL url = this.getClass().getResource("/9128.cache");
		File file = new File(url.getFile());
		
		Assert.assertTrue(file.isFile());
		
		MarketOrders marketOrders = new MarketOrders(file);		
		marketOrders.read();
	}

}
