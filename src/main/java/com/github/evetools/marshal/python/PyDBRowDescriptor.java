package com.github.evetools.marshal.python;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyDBRowDescriptor extends PyBase {

	private static enum DBRowType {
		BOOL, CURRENCY, DOUBLE, INT16, INT32, INT64, STRING, UINT8, WINFILETIME
	};

	private final Map<Integer, DBRowType> dbtypes;
	private int size;

	private final SortedMap<Integer, ArrayList<PyBase>> typeMap;

	public PyDBRowDescriptor(PyObjectEx object) throws IOException {

		super(types.DBROWDESCRIPTOR);

		this.size = 0;

		this.dbtypes = new HashMap<Integer, PyDBRowDescriptor.DBRowType>();
		this.dbtypes.put(new Integer(2), DBRowType.INT16);
		this.dbtypes.put(new Integer(3), DBRowType.INT32);
		this.dbtypes.put(new Integer(5), DBRowType.DOUBLE);
		this.dbtypes.put(new Integer(6), DBRowType.CURRENCY);
		this.dbtypes.put(new Integer(11), DBRowType.BOOL);
		this.dbtypes.put(new Integer(17), DBRowType.UINT8);
		this.dbtypes.put(new Integer(20), DBRowType.INT64);
		this.dbtypes.put(new Integer(64), DBRowType.WINFILETIME);
		this.dbtypes.put(new Integer(129), DBRowType.STRING);
		this.dbtypes.put(new Integer(130), DBRowType.STRING);

		this.typeMap = new TreeMap<Integer, ArrayList<PyBase>>();
		this.typeMap.put(new Integer(0), new ArrayList<PyBase>()); // 64
		this.typeMap.put(new Integer(1), new ArrayList<PyBase>()); // 32
		this.typeMap.put(new Integer(2), new ArrayList<PyBase>()); // 16
		this.typeMap.put(new Integer(3), new ArrayList<PyBase>()); // 8
		this.typeMap.put(new Integer(4), new ArrayList<PyBase>()); // bool
		this.typeMap.put(new Integer(5), new ArrayList<PyBase>()); // String

		this.init(object);
	}

	public SortedMap<Integer, ArrayList<PyBase>> getTypeMap() {
		return this.typeMap;
	}

	private void init(PyObjectEx object) throws IOException {

		if (!(object.getHead().isTuple())) {
			throw new IOException("Invalid Packed Row header");
		}

		final PyTuple tuple = object.getHead().asTuple();

		if (!(tuple.get(1).isTuple())) {
			throw new IOException("Invalid Packed Row header");
		}

		PyTuple header = tuple.get(1).asTuple();

		if (!(header.get(0).isTuple())) {
			throw new IOException("Invalid Packed Row header");
		}

		header = header.get(0).asTuple();
		int boolcount = 0;

		for (final Iterator<PyBase> iterator = header.iterator(); iterator
				.hasNext();) {

			final PyBase type = iterator.next();

			if (type != null) {
				if (!(type.isTuple())) {
					throw new IOException("Invalid DBRowType header type");
				}
				final PyTuple info = type.asTuple();

				int dbtype = 0;

				if (info.get(1) instanceof PyByte) {
					dbtype = (((PyByte) info.get(1)).getValue());
				} else if (info.get(1) instanceof PyShort) {
					dbtype = (((PyShort) info.get(1)).getValue());
				}

				if (!this.dbtypes.containsKey(new Integer(dbtype))) {
					throw new IOException("Invalid DBRowType: " + dbtype);
				}

				switch (this.dbtypes.get(new Integer(dbtype))) {
				case UINT8:
					this.size += 1;
					this.typeMap.get(new Integer(3)).add(type);
					break;
				case INT16:
					this.size += 2;
					this.typeMap.get(new Integer(2)).add(type);
					break;
				case INT32:
					this.size += 4;
					this.typeMap.get(new Integer(1)).add(type);
					break;
				case DOUBLE:
				case INT64:
				case WINFILETIME:
				case CURRENCY:
					this.size += 8;
					this.typeMap.get(new Integer(0)).add(type);
					break;
				case BOOL:
					if (boolcount == 0) {
						this.size += 1;
						this.typeMap.get(new Integer(4)).add(type);
					}
					boolcount++;
					if (boolcount == 8) {
						boolcount = 0;
					}
					break;
				case STRING:
					this.typeMap.get(new Integer(5)).add(type);
					break;
				default:
					throw new IOException("Not implemented DBRowType: "
							+ dbtype);
				}
			}
		}

	}

	public int size() {
		return this.size;
	}
}
