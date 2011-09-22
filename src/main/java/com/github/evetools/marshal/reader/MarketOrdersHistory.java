package com.github.evetools.marshal.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.github.evetools.marshal.Reader;
import com.github.evetools.marshal.python.PyBase;
import com.github.evetools.marshal.python.PyDict;
import com.github.evetools.marshal.python.PyList;
import com.github.evetools.marshal.python.PyObjectEx;
import com.github.evetools.marshal.python.PyPackedRow;
import com.github.evetools.marshal.python.PyString;
import com.github.evetools.marshal.python.PyTuple;
import java.io.InputStream;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class MarketOrdersHistory {

	private Reader reader;

	private long regionID;

	private long typeID;

	private long timestamp;

	public static class MarketOrderHistory {

		private long avgPrice;
		private long highPrice;
		private long lowPrice;

		private long orders;
		private long volume;

		private long historyDate;

		public long getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(long avgPrice) {
			this.avgPrice = avgPrice;
		}

		public long getHighPrice() {
			return highPrice;
		}

		public void setHighPrice(long highPrice) {
			this.highPrice = highPrice;
		}

		public long getLowPrice() {
			return lowPrice;
		}

		public void setLowPrice(long lowPrice) {
			this.lowPrice = lowPrice;
		}

		public long getOrders() {
			return orders;
		}

		public void setOrders(long orders) {
			this.orders = orders;
		}

		public long getVolume() {
			return volume;
		}

		public void setVolume(long volume) {
			this.volume = volume;
		}

		public long getHistoryDate() {
			return historyDate;
		}

		public void setHistoryDate(long historyDate) {
			this.historyDate = historyDate;
		}




	};

	private Collection<MarketOrderHistory> entries;


	public Reader getReader() {
		return reader;
	}

	public long getRegionID() {
		return regionID;
	}

	public long getTypeID() {
		return typeID;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Collection<MarketOrderHistory> getOrders() {
		return entries;
	}

	public MarketOrdersHistory(InputStream inputStream) throws Exception {
		this.reader = new Reader(inputStream);
	}
	public MarketOrdersHistory(File file) throws Exception {
		this.reader = new Reader(file);
	}

	public void read() throws Exception {

		PyBase pyBase = this.reader.read();

		PyTuple tuple1 = pyBase.asTuple();
		PyTuple tuple2 = null;
		PyDict dict = null;
		PyBase base = null;
		PyList list = null;
		PyObjectEx objectEx = null;
		PyString string = null;

		if (tuple1 == null) {
			throw new RuntimeException("Invalid element: " + pyBase.getType());
		}

		tuple2 = tuple1.get(0).asTuple();

		if (tuple2 == null) {
			throw new RuntimeException("Invalid element: " + tuple1.get(0).getType());
		}

		if (tuple2.get(0).isString()) {
			string = tuple2.get(0).asString();
		} else if (tuple2.get(0).isBuffer()) {
			string = new PyString(new String(tuple2.get(0).asBuffer().getValue()));
		} else {
			string = null;
		}

		if (string == null) {
			throw new RuntimeException("Invalid element: " + tuple2.get(0).getType());
		}

		if (!string.toString().equals("marketProxy")) {
			throw new RuntimeException("Invalid element content: " + string + " expeced: marketProxy");
		}

		if (tuple2.get(1).isString()) {
			string = tuple2.get(1).asString();
		} else if (tuple2.get(1).isBuffer()) {
			string = new PyString(new String(tuple2.get(1).asBuffer().getValue()));
		} else {
			string = null;
		}

		if (string == null) {
			throw new RuntimeException("Invalid element: " + tuple2.get(1).getType());
		}

		if (!string.toString().equals("GetOldPriceHistory")) {
			throw new RuntimeException("Invalid element content: " + string + " expeced: GetOldPriceHistory");
		}

		switch (tuple2.get(2).getType()) {

		case INT8:
			this.regionID = tuple2.get(2).asByte().getValue() & 0xFF;
			break;
		case INT16:
			this.regionID = tuple2.get(2).asShort().getValue();
			break;
		case INT32:
			this.regionID = tuple2.get(2).asInt().getValue();
			break;
		case INT64:
			this.regionID = tuple2.get(2).asLong().getValue();
			break;
		default:
			throw new RuntimeException("Invalid element: " + tuple2.getType());
		}

		switch (tuple2.get(3).getType()) {

		case INT8:
			this.typeID = tuple2.get(3).asByte().getValue() & 0xFF;
			break;
		case INT16:
			this.typeID = tuple2.get(3).asShort().getValue();
			break;
		case INT32:
			this.typeID = tuple2.get(3).asInt().getValue();
			break;
		case INT64:
			this.typeID = tuple2.get(3).asLong().getValue();
			break;
		default:
			throw new RuntimeException("Invalid element: " + tuple2.getType());
		}

		dict = tuple1.get(1).asDict();

		if (dict == null) {
			throw new RuntimeException("Invalid element: " + tuple1.get(1).getType());
		}

		base = dict.get(new PyString("version"));

		if (base == null) {
			throw new RuntimeException("version key not found in dict");
		}

		list = base.asList();

		if (list == null) {
			throw new RuntimeException("Invalid element: " + base.getType());
		}

		if (list.get(0).asLong() == null) {
			throw new RuntimeException("Invalid element: " + list.get(0).getType());
		}

		this.timestamp = PyBase.convertWindowsTime(list.get(0).asLong().getValue());

		base = dict.get(new PyString("lret"));

		if (base == null) {
			throw new RuntimeException("version key not found in dict");
		}

		objectEx = base.asObjectEx();

		if (objectEx == null) {
			throw new RuntimeException("Invalid element: " + base.getType());
		}

		this.entries = new ArrayList<MarketOrdersHistory.MarketOrderHistory>();

		this.read(objectEx);
	}

	protected void read(PyObjectEx object) throws Exception {

		if (object == null) {
			throw new IllegalArgumentException("invalid PyObjectEx");
		}

		PyList list = object.getList();

		for (Iterator<PyBase> iterator = list.iterator(); iterator.hasNext();) {
			PyBase type = (PyBase) iterator.next();

			if (!type.isPackedRow()) {
				throw new RuntimeException("Invalid element: " + type.getType());
			}

			this.read(type.asPackedRow());
		}
	}

	protected void read(PyPackedRow object) throws Exception {

		if (object == null) {
			throw new IllegalArgumentException("invalid PyPackedRow");
		}

		PyDict dict = object.getColumns();

		if (dict == null) {
			throw new IllegalArgumentException("Empty PyPackedRow");
		}

		MarketOrderHistory entry = new MarketOrderHistory();
		entry.setAvgPrice(dict.get("avgPrice").asLong().getValue());
		entry.setHighPrice(dict.get("highPrice").asLong().getValue());
		entry.setLowPrice(dict.get("lowPrice").asLong().getValue());
		entry.setOrders(dict.get("orders").asInt().getValue());
		entry.setVolume(dict.get("volume").asLong().getValue());
		entry.setHistoryDate(dict.get("historyDate").asLong().getValue());

		this.entries.add(entry);
	}
}
