package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyLong extends PyBase {

	private long value;

	public PyLong(long value) {
		super(types.INT64);
		this.value = value;
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
		final PyLong other = (PyLong) obj;
		if (this.value != other.value) {
			return false;
		}
		return true;
	}

	public long getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result) + (int) (this.value ^ (this.value >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return Long.toString(this.value);
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
