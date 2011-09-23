package com.github.evetools.marshal.python;

import java.util.Iterator;
import java.util.Map;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyDumpVisitor implements PyVisitor {

	private int indent = 0;

	protected void popIndent() {
		this.indent--;
	}

	protected void print(PyBase base) {
		this.print(base.toString());
	}

	protected void print(String string) {
		for (int loop = 0; loop < this.indent; loop++) {
			System.out.print(" ");
		}
		System.out.println(string);
	}

	protected void pushIndent() {
		this.indent++;
	}

	@Override
	public boolean visit(PyBase base) {

		if (base != null) {
			this.print(base.getType().toString());
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyBool base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyBuffer buffer) {

		if (buffer != null) {
			this.print(buffer);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyByte base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyContainer container) {

		if (container != null) {

			this.print(container.getType().toString() + " [" + container.size()
					+ "]");
			this.pushIndent();
			for (final Iterator<PyBase> iterator = container.iterator(); iterator
					.hasNext();) {
				final PyBase type = iterator.next();
				if (type != null) {
					type.visit(this);
				}
			}
			this.popIndent();

			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyDict container) {

		if (container != null) {

			this.print(container.getType().toString() + " [" + container.size()
					+ "]");
			this.pushIndent();
			for (final Map.Entry<PyBase, PyBase> entry : container.entrySet()) {
				entry.getKey().visit(this);
				this.pushIndent();
				entry.getValue().visit(this);
				this.popIndent();
			}
			this.popIndent();

			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyDouble base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyGlobal base1) {
		if (base1 != null) {
			this.print(base1.getType().toString());
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyInt base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyLong base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyMarker base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyNone base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyObject base1) {

		if (base1 != null) {
			this.print(base1.getType().toString());
			this.pushIndent();
			this.print(base1.getType().toString() + "-HEADER");
			this.pushIndent();
			base1.getHead().visit(this);
			this.popIndent();
			this.print(base1.getType().toString() + "-CONTENT");
			this.pushIndent();
			base1.getContent().visit(this);
			this.popIndent();
			this.popIndent();
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyObjectEx base1) {

		if (base1 != null) {

			this.print(base1.getType().toString());
			this.pushIndent();

			this.print(base1.getType().toString() + "-HEADER");
			this.pushIndent();
			base1.getHead().visit(this);
			this.popIndent();

			this.print(base1.getType().toString() + "-LIST");
			this.pushIndent();
			base1.getList().visit(this);
			this.popIndent();

			this.print(base1.getType().toString() + "-DICT");
			this.pushIndent();
			base1.getDict().visit(this);
			this.popIndent();

			this.popIndent();

			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyPackedRow base1) {
		if (base1 != null) {
			this.print("DBRow");
			this.pushIndent();
			base1.getColumns().visit(this);
			this.popIndent();
			return true;
		}

		return false;
	}

	@Override
	public boolean visit(PyShort base1) {
		if (base1 != null) {
			this.print(base1);
			return true;
		}

		return false;
	}
}
