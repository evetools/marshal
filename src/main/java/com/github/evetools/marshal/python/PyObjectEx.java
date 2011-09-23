package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyObjectEx extends PyBase {

	private PyDict dict;

	private PyBase head;

	private PyList list;

	public PyObjectEx() {
		super(types.OBJECTEX);
		this.head = null;
		this.list = new PyList();
		this.dict = new PyDict();
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
		final PyObjectEx other = (PyObjectEx) obj;
		if (this.dict == null) {
			if (other.dict != null) {
				return false;
			}
		} else if (!this.dict.equals(other.dict)) {
			return false;
		}
		if (this.head == null) {
			if (other.head != null) {
				return false;
			}
		} else if (!this.head.equals(other.head)) {
			return false;
		}
		if (this.list == null) {
			if (other.list != null) {
				return false;
			}
		} else if (!this.list.equals(other.list)) {
			return false;
		}
		return true;
	}

	public PyDict getDict() {
		return this.dict;
	}

	public PyBase getHead() {
		return this.head;
	}

	public PyList getList() {
		return this.list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result)
				+ ((this.dict == null) ? 0 : this.dict.hashCode());
		result = (prime * result)
				+ ((this.head == null) ? 0 : this.head.hashCode());
		result = (prime * result)
				+ ((this.list == null) ? 0 : this.list.hashCode());
		return result;
	}

	public void setHead(PyBase head) {
		this.head = head;
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
