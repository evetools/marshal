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
import com.github.evetools.marshal.python.PyBuffer;
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
public class MarketOrders {

	private Reader reader;

	private long regionID;

	private long typeID;

	private long timestamp;

	public static class MarketOrder {

		private boolean bid;

		private int typeID;

		private short range;

		private long price;

		private long  orderID;

		private long  issueDate;

		private int stationID;

		private int regionID;

		private short duration;

		private int jumps;

		private long minVolume;

		private long volEntered;

		private double volRemaining;

		public boolean isBid() {
			return bid;
		}

		public void setBid(boolean bid) {
			this.bid = bid;
		}

		public int getTypeID() {
			return typeID;
		}

		public void setTypeID(int typeID) {
			this.typeID = typeID;
		}

		public short getRange() {
			return range;
		}

		public void setRange(short range) {
			this.range = range;
		}

		public long getPrice() {
			return price;
		}

		public void setPrice(long price) {
			this.price = price;
		}

		public long getOrderID() {
			return orderID;
		}

		public void setOrderID(long orderID) {
			this.orderID = orderID;
		}

		public long getIssueDate() {
			return issueDate;
		}

		public void setIssueDate(long issueDate) {
			this.issueDate = issueDate;
		}

		public int getStationID() {
			return stationID;
		}

		public void setStationID(int stationID) {
			this.stationID = stationID;
		}

		public int getRegionID() {
			return regionID;
		}

		public void setRegionID(int regionID) {
			this.regionID = regionID;
		}

		public short getDuration() {
			return duration;
		}

		public void setDuration(short duration) {
			this.duration = duration;
		}

		public int getJumps() {
			return jumps;
		}

		public void setJumps(int jumps) {
			this.jumps = jumps;
		}

		public long getMinVolume() {
			return minVolume;
		}

		public void setMinVolume(long minVolume) {
			this.minVolume = minVolume;
		}

		public long getVolEntered() {
			return volEntered;
		}

		public void setVolEntered(long volEntered) {
			this.volEntered = volEntered;
		}

		public double getVolRemaining() {
			return volRemaining;
		}

		public void setVolRemaining(double volRemaining) {
			this.volRemaining = volRemaining;
		}

		@Override
		public String toString() {
			return "MarketOrder [bid=" + bid + ", typeID=" + typeID
					+ ", range=" + range + ", price=" + price + ", orderID="
					+ orderID + ", issueDate=" + issueDate + ", stationID="
					+ stationID + ", regionID=" + regionID + ", duration="
					+ duration + ", jumps=" + jumps + ", minVolume="
					+ minVolume + ", volEntered=" + volEntered
					+ ", volRemaining=" + volRemaining + "]";
		}


	};

	private Collection<MarketOrder> orders;


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

	public Collection<MarketOrder> getOrders() {
		return orders;
	}

	public MarketOrders(File file) throws Exception {
		this.reader = new Reader(file);
	}

	public MarketOrders(InputStream inputStream) throws Exception {
		this.reader = new Reader(inputStream);
	}

	public void read() throws Exception {

		PyBase pyBase = this.reader.read();

		PyTuple tuple1 = pyBase.asTuple();
		PyTuple tuple2 = null;
		PyDict dict = null;
		PyBase base = null;
		PyList list = null;
		PyBuffer string = null;

		if (tuple1 == null) {
			throw new RuntimeException("Invalid element: " + pyBase.getType());
		}

		tuple2 = tuple1.get(0).asTuple();

		if (tuple2 == null) {
			throw new RuntimeException("Invalid element: " + tuple1.get(0).getType());
		}

		if (tuple2.get(0).isBuffer()) {
			string = tuple2.get(0).asBuffer();
		} else {
			string = null;
		}

		if (string == null) {
			throw new RuntimeException("Invalid element: " + tuple2.get(0).getType());
		}

		if (!string.toString().equals("marketProxy")) {
			throw new RuntimeException("Invalid element content: " + string + " expeced: marketProxy");
		}

		if (tuple2.get(1).isBuffer()) {
			string = tuple2.get(1).asBuffer();
		} else {
			string = null;
		}

		if (string == null) {
			throw new RuntimeException("Invalid element: " + tuple2.get(1).getType());
		}

		if (!string.toString().equals("GetOrders")) {
			throw new RuntimeException("Invalid element content: " + string + " expeced: GetOrders");
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

		base = dict.get("version");

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

		base = dict.get("lret");

		if (base == null) {
			throw new RuntimeException("version key not found in dict");
		}

		list = base.asList();

		if (list == null) {
			throw new RuntimeException("Invalid element: " + base.getType());
		}

		if (list.size() != 2) {
			throw new RuntimeException("Invalid list size: " + list.size());
		}

		this.orders = new ArrayList<MarketOrders.MarketOrder>();

		for (Iterator<PyBase> iterator = list.iterator(); iterator.hasNext();) {
			PyBase type = (PyBase) iterator.next();

			if (!type.isObjectEx()) {
				throw new RuntimeException("Invalid element: " + type.getType());
			}

			this.read(type.asObjectEx());
		}
	}

	private void read(PyObjectEx object) throws Exception {

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

	private void read(PyPackedRow object) throws Exception {

		if (object == null) {
			throw new IllegalArgumentException("invalid PyPackedRow");
		}

		PyDict dict = object.getColumns();

		if (dict == null) {
			throw new IllegalArgumentException("Empty PyPackedRow");
		}

		MarketOrder order = new MarketOrder();
		order.setBid(dict.get("bid").asBool().getValue());
		order.setTypeID(dict.get("typeID").asInt().getValue());
		order.setOrderID(dict.get("orderID").asLong().getValue());
		order.setStationID(dict.get("stationID").asInt().getValue());
		order.setRegionID(dict.get("regionID").asInt().getValue());
		order.setPrice(dict.get("price").asLong().getValue());
		order.setJumps(dict.get("jumps").asInt().getValue());
		order.setRange(dict.get("range").asShort().getValue());
		order.setDuration(dict.get("duration").asShort().getValue());
		order.setMinVolume(dict.get("minVolume").asInt().getValue());
		order.setVolEntered(dict.get("volEntered").asInt().getValue());
		order.setVolRemaining(dict.get("volRemaining").asDouble().getValue());

		this.orders.add(order);
	}
}
