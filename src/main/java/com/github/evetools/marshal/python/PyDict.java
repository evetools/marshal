package com.github.evetools.marshal.python;

import java.util.Collection;
import java.util.Hashtable;
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
public class PyDict extends PyBase {

	protected Map<PyBase, PyBase> map;

	public PyDict() {
		super(types.DICT);
		this.map = new Hashtable<PyBase, PyBase>();
	}

	public void clear() {
		this.map.clear();
	}

	public boolean containsKey(Object key) {
		return this.map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.map.containsValue(value);
	}

	public Set<Entry<PyBase, PyBase>> entrySet() {
		return this.map.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return this.map.equals(o);
	}

	public PyBase get(Object key) {
		return this.map.get(key);
	}

	@Override
	public int hashCode() {
		return this.map.hashCode();
	}

	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	public Set<PyBase> keySet() {
		return this.map.keySet();
	}

	public PyBase put(PyBase key, PyBase value) {
		return this.map.put(key, value);
	}

	public void putAll(Map<? extends PyBase, ? extends PyBase> m) {
		this.map.putAll(m);
	}

	public PyBase remove(Object key) {
		return this.map.remove(key);
	}

	public int size() {
		return this.map.size();
	}

	public Collection<PyBase> values() {
		return this.map.values();
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
