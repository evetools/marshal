package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyBuffer extends PyBase {

	protected byte[] value;

	public PyBuffer(byte[] bytes) {
		super(types.BUFFER);
		this.value = bytes;
	}

	@Override
	public boolean equals(Object obj) {
		final PyString string = new PyString(new String(this.value));
		return string.equals(obj);
	}

	public byte[] getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		final PyString string = new PyString(new String(this.value));
		return string.hashCode();
	}

	@Override
	public String toString() {
		return new String(this.value);
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
