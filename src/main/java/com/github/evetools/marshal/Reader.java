package com.github.evetools.marshal;

import com.github.evetools.marshal.python.PyBase;
import com.github.evetools.marshal.python.PyBool;
import com.github.evetools.marshal.python.PyBuffer;
import com.github.evetools.marshal.python.PyByte;
import com.github.evetools.marshal.python.PyDBRowDescriptor;
import com.github.evetools.marshal.python.PyDBRowDescriptor.DBColumnTypes;
import com.github.evetools.marshal.python.PyDBColumn;
import com.github.evetools.marshal.python.PyDict;
import com.github.evetools.marshal.python.PyDouble;
import com.github.evetools.marshal.python.PyGlobal;
import com.github.evetools.marshal.python.PyInt;
import com.github.evetools.marshal.python.PyList;
import com.github.evetools.marshal.python.PyLong;
import com.github.evetools.marshal.python.PyMarker;
import com.github.evetools.marshal.python.PyNone;
import com.github.evetools.marshal.python.PyObject;
import com.github.evetools.marshal.python.PyObjectEx;
import com.github.evetools.marshal.python.PyDBRow;
import com.github.evetools.marshal.python.PyShort;
import com.github.evetools.marshal.python.PyTuple;
import com.github.evetools.marshal.Strings;
import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

/**
 * Copyright (C)2011 by Gregor Anders
 * All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the BSD license (see the file LICENSE.txt
 * included with the distribution).
 */
public class Reader {

	public static class Buffer {

		private final ByteBuffer buffer;

		Buffer(byte[] bytes) {
			this.buffer = ByteBuffer.wrap(bytes);
			this.buffer.order(ByteOrder.LITTLE_ENDIAN);
		}

		public final int length() {
			return this.buffer.array().length;
		}

		public final byte peekByte() {
			final byte b = this.buffer.get();
			this.buffer.position(this.buffer.position() - 1);
			return b;
		}

		public final byte[] peekBytes(int offset, int size) {
			byte[] bytes = null;
			final int position = this.buffer.position();
			this.buffer.position(offset);
			bytes = this.readBytes(size);
			this.buffer.position(position);
			return bytes;
		}

		public final int position() {
			return this.buffer.position();
		}

		public final byte readByte() {
			return this.buffer.get();
		}

		public final byte[] readBytes(int size) {
			final byte[] bytes = new byte[size];
			this.buffer.get(bytes);
			return bytes;
		}

		public final double readDouble() {
			return this.buffer.getDouble();
		}

		public final int readInt() {
			return this.buffer.getInt();
		}

		public final long readLong() {
			return this.buffer.getLong();
		}

		public final short readShort() {
			return this.buffer.getShort();
		}
	}

	interface IRead {
		PyBase read() throws IOException;
	}

	private static byte fromBitSet(BitSet bitSet) {
		byte b = 0;

		for (int i = 0; i < bitSet.length(); i++) {
			if (bitSet.get(i)) {
				b |= 1 << i;
			}
		}
		return b;
	}

	private final Buffer buffer;

	private final IRead[] loadMethods = new IRead[] {
	/* 0x00 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadError();
		}
	},
	/* 0x01 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNone();
		}
	},
	/* 0x02 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadGlobal();
		}
	},
	/* 0x03 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadLong();
		}
	},
	/* 0x04 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadInt();
		}
	},
	/* 0x05 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadShort();
		}
	},
	/* 0x06 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadByte();
		}
	},
	/* 0x07 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadIntMinus1();
		}
	},
	/* 0x08 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadInt0();
		}
	},
	/* 0x09 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadInt1();
		}
	},
	/* 0x0a */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadDouble();
		}
	},
	/* 0x0b */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadDouble0();
		}
	},
	/* 0x0c */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x0d */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x0e */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadString0();
		}
	},
	/* 0x0f */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadString1();
		}
	},
	/* 0x10 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadString();
		}
	},
	/* 0x11 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadStringRef();
		}
	},
	/* 0x12 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadUnicode();
		}
	},
	/* 0x13 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadBuffer();
		}
	},
	/* 0x14 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadTuple();
		}
	},
	/* 0x15 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadList();
		}
	},
	/* 0x16 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadDict();
		}
	},
	/* 0x17 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadInstance();
		}
	},
	/* 0x18 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x19 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x1a */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x1b */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadReference();
		}
	},
	/* 0x1c */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x1d */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x1e */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x1f */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadTrue();
		}
	},
	/* 0x20 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadFalse();
		}
	},
	/* 0x21 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x22 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadObjectReduce();
		}
	},
	/* 0x23 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadObjectEx();
		}
	},
	/* 0x24 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x25 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadTuple1();
		}
	},
	/* 0x26 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadList0();
		}
	},
	/* 0x27 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadList1();
		}
	},
	/* 0x28 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadUnicode0();
		}
	},
	/* 0x29 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadUnicode1();
		}
	},
	/* 0x2a */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadPacked();
		}
	},
	/* 0x2b */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadSubStream();
		}
	},
	/* 0x2c */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadTuple2();
		}
	},
	/* 0x2d */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadMarker();
		}
	},
	/* 0x2e */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadBuffer();
		}
	},
	/* 0x2f */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadVarInt();
		}
	},
	/* 0x30 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x31 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x32 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x33 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x34 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x35 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x36 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x37 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x38 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x39 */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	},
	/* 0x3a */new IRead() {
		@Override
		public PyBase read() throws IOException {
			return Reader.this.loadNotImplemented();
		}
	} };

	private int position;

	private Map<Integer, PyBase> shared;
	
	private Map<PyBase, PyDBRowDescriptor> descriptors;
	
	private Buffer sharedBuffer;

	private int type;
	
	private Stack<PyBase> objects;

	private Reader(Buffer buffer) throws IOException {
		this.buffer = buffer;
	}

	public Reader(File file) throws IOException {
		this(new FileInputStream(file));
	}

	public Reader(InputStream stream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		final byte[] bytes = new byte[4096];

		int read = -1;
		while (0 <= (read = stream.read(bytes))) {
			baos.write(bytes, 0, read);
		}

		stream.close();

		this.buffer = new Buffer(baos.toByteArray());
	}

	private PyDBRowDescriptor toDBRowDescriptor(PyBase base)
			throws IOException {

		if (!(base instanceof PyObjectEx)) {
			throw new IOException("Invalid Packed Row header: "
					+ base.getType());
		}

		final PyObjectEx object = (PyObjectEx) base;

		return new PyDBRowDescriptor(object);
	}

	private int length() {

		int length = 0;

		length = this.buffer.readByte() & 0xFF;

		if (length == 255) {
			length = this.buffer.readInt();
		}

		return length;
	}

	private PyBase loadBuffer() throws IOException {
		final int size = this.length();
		final byte[] bytes = this.buffer.readBytes(size);

		if (bytes[0] == 0x78) {

			final byte[] zlibbytes = new byte[bytes.length + 1];
			System.arraycopy(bytes, 0, zlibbytes, 0, bytes.length);
			zlibbytes[zlibbytes.length - 1] = 0;

			int zlen = zlibbytes.length * 2;
			byte[] zout;

			boolean success = false;
			final ZStream zstream = new ZStream();
			int res = 0;

			while (!success) {

				zout = new byte[zlen];

				zstream.next_in = zlibbytes;
				zstream.next_in_index = 0;
				zstream.next_out = zout;
				zstream.next_out_index = 0;

				if (zstream.inflateInit() != JZlib.Z_OK) {
					throw new IOException("Error uncompressing zlib buffer");
				}

				while ((zstream.total_out < zlen)
						&& (zstream.total_in < zlibbytes.length)) {
					zstream.avail_in = 1;
					zstream.avail_out = 1;
					res = zstream.inflate(JZlib.Z_NO_FLUSH);
					if (res == JZlib.Z_STREAM_END) {
						success = true;
						break;
					}

					if (res == JZlib.Z_DATA_ERROR) {
						return new PyBuffer(bytes);
					}
				}

				if (zstream.total_out < zlen) {
					break;
				}

				if (!success) {
					zout = null;
					zlen = zlen * 2;
				} else {
					zstream.inflateEnd();

					/*
					 * for debugging byte[] uncom = new byte[(int)
					 * zstream.total_out]; for (int loop = 0; loop <
					 * uncom.length; loop++) { uncom[loop] = zout[loop]; }
					 */

					final Buffer buf = new Buffer(zout);
					final Reader reader = new Reader(buf);

					return reader.read();
				}
			}
		}
		return new PyBuffer(bytes);
	}

	private PyBase loadByte() throws IOException {
		final byte valueByte = this.buffer.readByte();
		return new PyByte(valueByte);
	}

	private PyBase loadDict() throws IOException {
		final int size = this.length();

		PyBase key = null;
		PyBase value = null;

		final PyDict dict = new PyDict();

		for (int loop = 0; loop < size; loop++) {
			value = this.loadPy();
			key = this.loadPy();
			dict.put(key, value);
		}

		return dict;
	}

	private PyBase loadDouble() throws IOException {
		return new PyDouble(this.buffer.readDouble());
	}

	private PyBase loadDouble0() throws IOException {
		return new PyDouble(0);
	}

	private PyBase loadError() throws IOException {
		throw new IOException("ERROR");
	}

	private PyBase loadFalse() throws IOException {
		return new PyBool(false);
	}

	private PyBase loadGlobal() throws IOException {
		final byte[] bytes = this.buffer.readBytes(this.length());
		return new PyGlobal(bytes);
	}

	private PyBase loadInstance() throws IOException {
		PyObject object = new PyObject();
		this.objects.push(object);
		
		PyBase head = this.loadPy();
		object.setHead(head);
		PyBase content = this.loadPy();
		object.setContent(content);
		this.objects.pop();
		return object;
	}

	private PyBase loadInt() throws IOException {
		return new PyInt(this.buffer.readInt());
	}

	private PyBase loadInt0() throws IOException {
		return new PyInt(0);
	}

	private PyBase loadInt1() throws IOException {
		return new PyInt(1);
	}

	private PyBase loadIntMinus1() throws IOException {
		return new PyInt(-1);
	}

	private PyBase loadList() throws IOException {
		return this.loadList(this.length());
	}

	private PyBase loadList(int size) throws IOException {
		final PyList tuple = new PyList();
		PyBase base = null;
		int curSize = size;
		while (curSize > 0) {
			base = this.loadPy();
			if (base == null) {
				throw new IOException("null element in list found");
			}
			tuple.add(base);
			curSize--;
		}
		return tuple;
	}

	private PyBase loadList0() throws IOException {
		return this.loadList(0);
	}

	private PyBase loadList1() throws IOException {
		return this.loadList(1);
	}

	private PyBase loadLong() throws IOException {
		return new PyLong(this.buffer.readLong());
	}

	private PyBase loadNone() throws IOException {
		return new PyNone();
	}

	private PyBase loadMarker() throws IOException {
		return new PyMarker();
	}

	private PyBase loadNotImplemented() throws IOException {
		throw new IOException("Not implemented: "
				+ Integer.toHexString(this.type) + " at: " + this.position);
	}

	private PyBase loadObjectReduce() throws IOException {
		return loadObjectEx(true);
	}
	
	private PyBase loadObjectEx() throws IOException {
		return loadObjectEx(false);
	}
	
	private PyBase loadObjectEx(boolean reduce) throws IOException {

		final PyObjectEx objectex = new PyObjectEx(reduce);

		this.objects.push(objectex);

		objectex.setHead(this.loadPy());

		while (this.buffer.peekByte() != 0x2d) {
			objectex.getList().add(this.loadPy());
		}
		this.buffer.readByte();

		PyBase key = null;
		PyBase value = null;

		while (this.buffer.peekByte() != 0x2d) {
			value = this.loadPy();
			key = this.loadPy();
			objectex.getDict().put(key, value);
		}
		this.buffer.readByte();

		this.objects.pop();
		
		return objectex;
	}

	private PyBase loadPacked() throws IOException {

		final PyBase head = this.loadPy();
		int size = this.length();
		final byte[] bytes = this.buffer.readBytes(size);

		final PyDBRow base = new PyDBRow();

		if (!this.descriptors.containsKey(head)) {
			this.descriptors.put(head, this.toDBRowDescriptor(head));
		}
		
		PyDBRowDescriptor desc = this.descriptors.get(head);
		base.setHead(desc);
		
		size = desc.size();

		final byte[] out = this.zerouncompress(bytes, size);

		final Buffer outbuf = new Buffer(out);

		List<PyDBColumn> list = desc.getColumns();
		
		int boolcount = 0;
		int boolvalue = 0;
		
		for (PyDBColumn pyDBColumn : list) {

			if (pyDBColumn.getDBType() == DBColumnTypes.BOOL) {
				
				if (boolcount == 0) {
					boolvalue = outbuf.readByte();
				}

				final boolean val = ((boolvalue >> boolcount++) & 0x01) > 0 ? true
						: false;

				base.put(pyDBColumn.getName(), new PyBool(val));

				if (boolcount == 8) {
					boolcount = 0;
				}
				
			} else if (pyDBColumn.getDBType() == DBColumnTypes.STRING || pyDBColumn.getDBType() == DBColumnTypes.USTRING) {
				base.put(pyDBColumn.getName(), loadPy());
			} else {
				base.put(pyDBColumn.getName(), pyDBColumn.getDBType().read(outbuf));
			}
			
		}
		
		return base;
	}

	private PyBase loadPy() throws IOException {

		this.position = this.buffer.position();

		final byte magic = this.buffer.readByte();
		final boolean sharedPy = (magic & 0x40) != 0;
		this.type = magic;
		this.type = (this.type & 0x3f);

		final PyBase pyBase = this.loadMethods[this.type].read();
		PyBase pyShared = null;
		
		if (sharedPy) {
			
			if (pyBase.isGlobal()) {
				pyShared = this.objects.peek();
			} else {
				pyShared = pyBase;
			}
			
			this.shared.put(Integer.valueOf(this.sharedBuffer.readInt()), pyShared);
		}

		return pyBase;
	}

	private PyBase loadReference() throws IOException {
		PyBase pyBase = this.shared.get(Integer.valueOf(this.length()));
		
		return pyBase;
	}

	private PyBase loadShort() throws IOException {
		return new PyShort(this.buffer.readShort());
	}

	private PyBase loadString() throws IOException {
		return new PyBuffer(this.buffer.readBytes(this.length()));
	}

	private PyBase loadString0() throws IOException {
		byte[] b = {0};
		return new PyBuffer(b);
	}

	private PyBase loadString1() throws IOException {
		return new PyBuffer(this.buffer.readBytes(1));
	}

	private PyBase loadStringRef() throws IOException {
		return new PyBuffer(Strings.get(this.length()).getBytes());
	}

	private PyBase loadSubStream() throws IOException {
		final int size = this.length();
		final Buffer buf = new Buffer(this.buffer.readBytes(size));
		final Reader reader = new Reader(buf);
		return reader.read();
	}

	private PyBase loadTrue() throws IOException {
		return new PyBool(true);
	}

	private PyBase loadTuple() throws IOException {
		return this.loadTuple(this.length());
	}

	private PyBase loadTuple(int size) throws IOException {
		final PyTuple tuple = new PyTuple();
		PyBase base = null;
		int curSize = size;
		while (curSize > 0) {
			base = this.loadPy();
			if (base == null) {
				throw new IOException("null element in tuple found");
			}
			tuple.add(base);
			curSize--;
		}
		return tuple;
	}

	private PyBase loadTuple1() throws IOException {
		return this.loadTuple(1);
	}

	private PyBase loadTuple2() throws IOException {
		return this.loadTuple(2);
	}

	private PyBase loadUnicode() throws IOException {
		return new PyBuffer(this.buffer.readBytes(this.length() * 2));
	}

	private PyBase loadUnicode0() throws IOException {
		byte[] b = {0};
		return new PyBuffer(b);
	}

	private PyBase loadUnicode1() throws IOException {
		return new PyBuffer(this.buffer.readBytes(2));
	}

	private PyBase loadVarInt() throws IOException {

		final int size = this.length();

		switch (size) {
		case 0:
			return new PyLong(0);
		case 2:
			return this.loadShort();
		case 4:
			return this.loadInt();
		case 8:
			return this.loadLong();
		default:
			final byte[] bytes = this.buffer.readBytes(size);

			final BigInteger bi = new BigInteger(bytes);

			return new PyLong(bi.longValue());
		}
	}

	public PyBase read() throws IOException {

		this.buffer.readByte();
		int size = this.buffer.readInt();

		this.shared = new HashMap<Integer, PyBase>(size);
		this.objects = new Stack<PyBase>();
		this.descriptors = new HashMap<PyBase, PyDBRowDescriptor>(size);

		size  = size * (Integer.SIZE / Byte.SIZE);
		final int offset = this.buffer.length() - (size);

		this.sharedBuffer = new Buffer(
				this.buffer.peekBytes(offset, (size)));

		PyBase base = null;

		base = this.loadPy();

		return base;
	}

	private byte[] zerouncompress(byte[] bytes, int size) throws IOException {

		final byte[] out = new byte[size + 16];
		int outpos = 0;
		byte current = 0;
		int length = 0;
		int pos = 0;

		for (int loop = 0; loop < out.length; loop++) {
			out[loop] = 0;
		}

		while (pos < bytes.length) {

			current = bytes[pos++];

			final BitSet bitSet = new BitSet(8);
			for (int i = 0; i < 8; i++) {
				if ((current & (1 << i)) > 0) {
					bitSet.set(i);
				}
			}

			if (bitSet.get(3)) {
				length = Reader.fromBitSet(bitSet.get(0, 3)) + 1;
				for (int i = 0; i < length; i++) {
					out[outpos++] = 0;
				}
			} else {
				length = 8 - Reader.fromBitSet(bitSet.get(0, 3));
				for (int i = 0; i < length; i++) {
					out[outpos++] = bytes[pos++];
				}
			}

			if (bitSet.get(7)) {
				length = Reader.fromBitSet(bitSet.get(4, 7)) + 1;
				for (int i = 0; i < length; i++) {
					out[outpos++] = 0;
				}
			} else {
				length = 8 - Reader.fromBitSet(bitSet.get(4, 7));
				for (int i = 0; (i < length) && (pos < bytes.length); i++) {
					out[outpos++] = bytes[pos++];
				}
			}
		}

		return out;
	}
}
