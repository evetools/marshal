package com.github.evetools.marshal.python;

import com.google.common.base.Objects;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyString extends PyBase {

	protected String string;

	public PyString(String string) {
		super(types.STRING);
		this.string = string;
	}

	protected PyString(types type, String string) {
		super(type);
		this.string = string;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final PyString other = (PyString) obj;
		if (this.string == null) {
			if (other.string != null) {
				return false;
			}
		} else if (!this.string.equals(other.string)) {
			return false;
		}
		return true;
	}

	public String getValue() {
		return this.string;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(string);
	}

	@Override
	public String toString() {
		return this.string;
	}

	@Override
	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}

}
