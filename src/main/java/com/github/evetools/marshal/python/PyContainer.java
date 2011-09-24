package com.github.evetools.marshal.python;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	protected PyContainer(PyType type) {
		super(type);
	}

	public void add(int index, PyBase element) {
		this.container.add(index, element);
	}

	public boolean add(PyBase e) {
		return this.container.add(e);
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

	public Iterator<PyBase> iterator() {
		return this.container.iterator();
	}

	public int size() {
		return this.container.size();
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
