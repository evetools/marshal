package com.github.evetools.marshal.python;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public abstract class PyContainer extends PyBase {

	private List<PyBase> container = new ArrayList<PyBase>();

	protected PyContainer(types type) {
		super(type);
	}

	public void add(int index, PyBase element) {
		this.container.add(index, element);
	}

	public boolean add(PyBase e) {
		return this.container.add(e);
	}

	public boolean addAll(Collection<? extends PyBase> c) {
		return this.container.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends PyBase> c) {
		return this.container.addAll(index, c);
	}

	public void clear() {
		this.container.clear();
	}

	public boolean contains(PyBase o) {
		return this.container.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.container.containsAll(c);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final PyContainer other = (PyContainer) obj;
		if (this.container == null) {
			if (other.container != null) {
				return false;
			}
		} else if (!this.container.equals(other.container)) {
			return false;
		}
		return true;
	}

	public PyBase get(int index) {
		return this.container.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result)
				+ ((this.container == null) ? 0 : this.container.hashCode());
		return result;
	}

	public int indexOf(Object o) {
		return this.container.indexOf(o);
	}

	public boolean isEmpty() {
		return this.container.isEmpty();
	}

	public Iterator<PyBase> iterator() {
		return this.container.iterator();
	}

	public int lastIndexOf(Object o) {
		return this.container.lastIndexOf(o);
	}

	public ListIterator<PyBase> listIterator() {
		return this.container.listIterator();
	}

	public ListIterator<PyBase> listIterator(int index) {
		return this.container.listIterator(index);
	}

	public PyBase remove(int index) {
		return this.container.remove(index);
	}

	public boolean remove(PyBase o) {
		return this.container.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return this.container.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return this.container.retainAll(c);
	}

	public PyBase set(int index, PyBase element) {
		return this.container.set(index, element);
	}

	public int size() {
		return this.container.size();
	}

	public List<PyBase> subList(int fromIndex, int toIndex) {
		return this.container.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return this.container.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return this.container.toArray(a);
	}

	@Override
	public String toString() {
		return this.container.toString();
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
