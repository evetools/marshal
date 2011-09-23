package com.github.evetools.marshal.python;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyPackedRow extends PyBase {

	private PyBuffer buffer;

	private PyDict columns;

	private PyBase head;

	public PyPackedRow(PyBase head, PyBuffer buffer) {
		super(types.PACKEDROW);
		this.head = head;
		this.buffer = buffer;
		this.columns = new PyDict();
	}

	public void clear() {
		this.columns.clear();
	}

	public boolean containsKey(PyBase key) {
		return this.columns.containsKey(key);
	}

	public boolean containsValue(PyBase value) {
		return this.columns.containsValue(value);
	}

	public Set<Entry<PyBase, PyBase>> entrySet() {
		return this.columns.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return this.columns.equals(o);
	}

	public PyBase get(PyBase key) {
		return this.columns.get(key);
	}

	public PyBuffer getBuffer() {
		return this.buffer;
	}

	public PyDict getColumns() {
		return this.columns;
	}

	public PyBase getHead() {
		return this.head;
	}

	@Override
	public int hashCode() {
		return this.columns.hashCode();
	}

	public boolean isEmpty() {
		return this.columns.isEmpty();
	}

	public Set<PyBase> keySet() {
		return this.columns.keySet();
	}

	public PyBase put(PyBase key, PyBase value) {
		return this.columns.put(key, value);
	}

	public void putAll(Map<? extends PyBase, ? extends PyBase> m) {
		this.columns.putAll(m);
	}

	public PyBase remove(PyBase key) {
		return this.columns.remove(key);
	}

	public int size() {
		return this.columns.size();
	}

	@Override
	public String toString() {
		return this.columns.toString();
	}

	public Collection<PyBase> values() {
		return this.columns.values();
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
