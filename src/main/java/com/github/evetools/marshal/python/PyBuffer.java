package com.github.evetools.marshal.python;

import java.util.Arrays;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyBuffer extends PyBase {

	private byte[] value;

	public PyBuffer(String string) {
		super(PyType.BUFFER);
		byte[] b = string.getBytes();
		this.value = new byte[b.length];
		System.arraycopy(b, 0, this.value, 0, b.length);
	}
	
	public PyBuffer(byte[] bytes) {
		super(PyType.BUFFER);
		this.value = new byte[bytes.length];
		System.arraycopy(bytes, 0, this.value, 0, bytes.length);
	}
	
	protected PyBuffer(PyType type, byte[] bytes) {
		super(type);
		this.value = new byte[bytes.length];
		System.arraycopy(bytes, 0, this.value, 0, bytes.length);
	}

	public byte[] getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PyBuffer other = (PyBuffer) obj;
		if (!Arrays.equals(value, other.value))
			return false;
		return true;
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
