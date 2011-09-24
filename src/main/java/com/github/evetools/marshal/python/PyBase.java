package com.github.evetools.marshal.python;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public abstract class PyBase {

	public static enum types {
		BOOL, BUFFER, CONTAINER, DBROWDESCRIPTOR, DICT, DOUBLE, GLOBAL, INT16, INT32, INT64, INT8, LIST, MARKER, NONE, OBJECT, OBJECTEX, PACKEDROW, STRING, TUPLE, UNKNOWN, DBCOLUMN
	};

	public static long convertWindowsTime(long timeStamp) {
		 return (timeStamp/10000L) - 11644473600000L;
	}

	private types type = types.UNKNOWN;

	protected PyBase(types type) {
		this.type = type;
	}

    public boolean isObject() {
        return (this.type == types.OBJECT);
    }

    public PyObject asObject() {
        if (this.isObject()) {
            return (PyObject)this;
        } else {
            return null;
        }
    }

    public boolean isObjectEx() {
        return (this.type == types.OBJECTEX);
    }

    public PyObjectEx asObjectEx() {
        if (this.isObjectEx()) {
            return (PyObjectEx)this;
        } else {
            return null;
        }
    }

    public boolean isDBRowDescriptor() {
        return (this.type == types.DBROWDESCRIPTOR);
    }

    public PyDBRowDescriptor asDBRowDescriptor() {
        if (this.isDBRowDescriptor()) {
            return (PyDBRowDescriptor)this;
        } else {
            return null;
        }
    }

    public boolean isDBColumn() {
        return (this.type == types.DBCOLUMN);
    }

    public PyDBColumn asDBColumn() {
        if (this.isDBColumn()) {
            return (PyDBColumn)this;
        } else {
            return null;
        }
    }

    public boolean isPackedRow() {
        return (this.type == types.PACKEDROW);
    }

    public PyPackedRow asPackedRow() {
        if (this.isPackedRow()) {
            return (PyPackedRow)this;
        } else {
            return null;
        }
    }

    public boolean isMarker() {
        return (this.type == types.MARKER);
    }

    public PyMarker asMarker() {
        if (this.isMarker()) {
            return (PyMarker)this;
        } else {
            return null;
        }
    }

    public boolean isNone() {
        return (this.type == types.NONE);
    }

    public PyNone asNone() {
        if (this.isNone()) {
            return (PyNone)this;
        } else {
            return null;
        }
    }

    public boolean isLong() {
        return (this.type == types.INT64);
    }

    public PyLong asLong() {
        if (this.isLong()) {
            return (PyLong)this;
        } else {
            return null;
        }
    }

    public boolean isInt() {
        return (this.type == types.INT32);
    }

    public PyInt asInt() {
        if (this.isInt()) {
            return (PyInt)this;
        } else {
            return null;
        }
    }

    public boolean isShort() {
        return (this.type == types.INT16);
    }

    public PyShort asShort() {
        if (this.isShort()) {
            return (PyShort)this;
        } else {
            return null;
        }
    }

    public boolean isByte() {
        return (this.type == types.INT8);
    }

    public PyByte asByte() {
        if (this.isByte()) {
            return (PyByte)this;
        } else {
            return null;
        }
    }

    public boolean isList() {
        return (this.type == types.LIST);
    }

    public PyList asList() {
        if (this.isList()) {
            return (PyList)this;
        } else {
            return null;
        }
    }

    public boolean isDouble() {
        return (this.type == types.DOUBLE);
    }

    public PyDouble asDouble() {
        if (this.isDouble()) {
            return (PyDouble)this;
        } else {
            return null;
        }
    }

    public boolean isDict() {
        return (this.type == types.DICT);
    }

    public PyDict asDict() {
        if (this.isDict()) {
            return (PyDict)this;
        } else {
            return null;
        }
    }

	public boolean isBool() {
		return (this.type == types.BOOL);
	}

	public PyBool asBool() {
		if (this.isBool()) {
			return (PyBool)this;
		} else {
			return null;
		}
	}

	public boolean isGlobal() {
		return (this.type == types.GLOBAL);
	}

	public PyGlobal asGlobal() {
		if (this.isGlobal()) {
			return (PyGlobal)this;
		} else {
			return null;
		}
	}

	public boolean isTuple() {
		return (this.type == types.TUPLE);
	}

	public PyTuple asTuple() {
		if (this.isTuple()) {
			return (PyTuple)this;
		} else {
			return null;
		}
	}

	public boolean isBuffer() {
		return (this.type == types.BUFFER);
	}

	public PyBuffer asBuffer() {
		if (this.isBuffer()) {
			return (PyBuffer)this;
		} else {
			return null;
		}
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
		final PyBase other = (PyBase) obj;
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

	public final types getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	public boolean visit(PyVisitor visitor) {
		return (visitor.visit(this));
	}
}
