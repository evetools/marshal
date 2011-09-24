package com.github.evetools.marshal.python;

import com.github.evetools.marshal.python.PyDBRowDescriptor.DBColumnTypes;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class PyDBColumn extends PyBase {
	
	private PyBase name;
	
	private PyBase value;
	
	final private DBColumnTypes dbtype;

	public PyDBColumn(DBColumnTypes type) {
		super(types.DBCOLUMN);
		this.dbtype = type;
	}
	
	public PyDBColumn(DBColumnTypes type, PyBase name) {
		super(types.DBCOLUMN);
		this.dbtype = type;
		this.name = name;
	}
	
	public PyBase getName() {
		return name;
	}

	public PyBase getValue() {
		return value;
	}

	public void setName(PyBase name) {
		this.name = name;
	}

	public void setValue(PyBase value) {
		this.value = value;
	}

	public DBColumnTypes getDBType() {
		return dbtype;
	}
	
	
};